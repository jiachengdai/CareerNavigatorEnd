package org.SETrain.CareerNavigator.Util;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * 语音合成流式 WebAPI 接口调用示例 接口文档（必看）：https://www.xfyun.cn/doc/tts/online_tts/API.html
 * 发音人使用方式：登陆开放平台https://www.xfyun.cn/后，到控制台-我的应用-语音合成-添加试用或购买发音人，添加后即显示该发音人参数值
 * 错误码链接：https://www.xfyun.cn/document/error-code （code返回错误码时必看）
 * 小语种需要传输小语种文本、使用小语种发音人vcn、tte=unicode以及修改文本编码方式
 *
 * @author xlliu24
 */
public class WebTtsWs {
    // 地址与鉴权信息
    public static final String hostUrl = "https://tts-api.xfyun.cn/v2/tts";
    // 均到控制台-语音合成页面获取
    public static final String APPID = "008999e5";
    public static final String APISecret = "ZDcxYjc3NWU2YjYwMWE1NWU0ZWUxZWZh";
    public static final String APIKey = "07e74836b6d4b904f5698c85a4099718";
    // 合成文本
    public static String TEXT = "你好呀，我是代佳诚，欢迎你";
    // 合成文本编码格式
    public static final String TTE = "UTF8"; // 小语种必须使用UNICODE编码作为值
    // 发音人参数。到控制台-我的应用-语音合成-添加试用或购买发音人，添加后即显示该发音人参数值，若试用未添加的发音人会报错11200
    public static final String VCN = "x4_xiaogu";
    // 合成文件存储地址以及名称
    public static final String OUTPUT_FILE_PATH = "src/main/resources/static/" + System.currentTimeMillis() + ".mp3";
    // json
    public static final Gson gson = new Gson();
    public static boolean wsCloseFlag = false;
    public  static  String path;

    public  String TTS (String text)  {
        try {
            String wsUrl = getAuthUrl(hostUrl, APIKey, APISecret).replace("https://", "wss://");
            OutputStream outputStream = new FileOutputStream(OUTPUT_FILE_PATH);

            CountDownLatch latch = new CountDownLatch(1);
            websocketWork(wsUrl, outputStream, text, latch);
            latch.await(); // 等待 websocketWork 完成

            return path;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Websocket方法
    public static void websocketWork(String wsUrl, OutputStream outputStream, String text, CountDownLatch latch) {
        try {
            URI uri = new URI(wsUrl);
            TEXT = text;
            WebSocketClient webSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("ws建立连接成功...");
                    // 在连接建立后直接发送请求
                    sendRequest(this);
                }

                @Override
                public void onMessage(String text) {
                    JsonParse myJsonParse = gson.fromJson(text, JsonParse.class);
                    if (myJsonParse.code != 0) {
                        System.out.println("发生错误，错误码为：" + myJsonParse.code);
                        System.out.println("本次请求的sid为：" + myJsonParse.sid);
                    }
                    if (myJsonParse.data != null) {
                        try {
                            byte[] textBase64Decode = Base64.getDecoder().decode(myJsonParse.data.audio);
                            outputStream.write(textBase64Decode);
                            outputStream.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (myJsonParse.data.status == 2) {
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("本次请求的sid==>" + myJsonParse.sid);
                            System.out.println("合成成功，文件保存路径为==>" + OUTPUT_FILE_PATH);
                            path = OUTPUT_FILE_PATH;
                            wsCloseFlag = true;
                            latch.countDown();
                        }
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("ws链接已关闭，本次请求完成...");
                }

                @Override
                public void onError(Exception e) {
                    System.out.println("发生错误 " + e.getMessage());
                }
            };

            // 建立连接
            webSocketClient.connectBlocking(); // 阻塞直到连接建立
            while (!wsCloseFlag) {
                Thread.sleep(200); // 等待响应完成
            }
//            webSocketClient.close();
//            System.out.println("ws链接已关闭");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sendRequest(WebSocketClient webSocketClient) {
        String requestJson = "{\n" +
                "  \"common\": {\n" +
                "    \"app_id\": \"" + APPID + "\"\n" +
                "  },\n" +
                "  \"business\": {\n" +
                "    \"aue\": \"lame\",\n" +
                "\"sfl\": 1,\n" +
                "    \"tte\": \"" + TTE + "\",\n" +
                "    \"ent\": \"intp65\",\n" +
                "    \"vcn\": \"" + VCN + "\",\n" +
                "    \"pitch\": 50,\n" +
                "    \"speed\": 50\n" +
                "  },\n" +
                "  \"data\": {\n" +
                "    \"status\": 2,\n" +
                "    \"text\": \"" + Base64.getEncoder().encodeToString(TEXT.getBytes(StandardCharsets.UTF_8)) + "\"\n" +
                "  }\n" +
                "}";
        webSocketClient.send(requestJson);
    }
    // 鉴权方法
    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
        URL url = new URL(hostUrl);
        // 时间
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        // 拼接
        String preStr = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";
        //System.out.println(preStr);
        // SHA256加密
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
        // Base64加密
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        // 拼接
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        // 拼接地址
// 拼接地址
        String queryParams = String.format(
                "authorization=%s&date=%s&host=%s",
                Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8)),
                URLEncoder.encode(date, StandardCharsets.UTF_8),
                URLEncoder.encode(url.getHost(), StandardCharsets.UTF_8)
        );
        String authUrl = "https://" + url.getHost() + url.getPath() + "?" + queryParams;

        return authUrl;
    }
    //返回的json结果拆解
    class JsonParse {
        int code;
        String sid;
        Data data;
    }

    class Data {
        int status;
        String audio;
    }
}
