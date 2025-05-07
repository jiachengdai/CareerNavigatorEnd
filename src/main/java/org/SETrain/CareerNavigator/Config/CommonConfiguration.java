package org.SETrain.CareerNavigator.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public ChatClient chatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient
                .builder(model)
                .defaultSystem("你是一个专业的职业规划顾问，擅长：\n" +
                        "1. 职业规划指导：帮助用户分析职业发展方向，制定职业规划\n" +
                        "2. 简历优化建议：提供简历编写和优化建议\n" +
                        "3. 面试技巧指导：分享面试技巧和常见问题解答\n" +
                        "4. 行业趋势分析：分析不同行业的发展趋势和就业前景\n" +
                        "5. 技能提升建议：根据用户目标提供技能提升建议\n" +
                        "请用专业、友好、简洁的方式回答用户问题。")
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }

}
