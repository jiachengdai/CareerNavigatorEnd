package org.SETrain.CareerNavigator.Controller;

import org.SETrain.CareerNavigator.Entity.Account;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Service.AccountService;
import org.SETrain.CareerNavigator.Service.UserService;
import org.SETrain.CareerNavigator.Util.JwtUtil;
import org.SETrain.CareerNavigator.Util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Tag(name = "账户管理")
@RestController
@RequestMapping("/account")
@Validated
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(
            @Parameter(description = "用户名") @RequestParam String username,
            @Parameter(description = "密码") @RequestParam String password,
            @Parameter(description = "用户类型") @RequestParam Integer type) {
        if (accountService.checkUsername(username)) {
            return Result.error("用户名已存在");
        }

        String MD5password = Md5Util.getMD5String(password);
        accountService.register(username, MD5password, type);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(
            @Parameter(description = "用户名") @RequestParam String username,
            @Parameter(description = "密码") @RequestParam String password) {
        Account account = accountService.findByUsername(username);
        if (account == null) {
            return Result.error("用户不存在");
        }

        String MD5password = account.getPassword();
        if (MD5password.equals(Md5Util.getMD5String(password))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", account.getUsername());
            claims.put("type", account.getType());
            String token = JwtUtil.genToken(claims);

            // 将JWT存储至Redis
            ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
            stringStringValueOperations.set(token, token, 24000, TimeUnit.HOURS);

            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
    }

    @GetMapping("/info")
    public Result getInfo(@Parameter(description = "用户名") @RequestParam String username) {
        Account account = accountService.findByUsername(username);
        if (account == null) {
            return Result.error("用户不存在");
        }
        // 不返回敏感信息
        account.setPassword(null);
        return Result.success(account);
    }

    @PostMapping("/changepassword")
    public Result changePassword(
            @Parameter(description = "用户名") @RequestParam String username,
            @Parameter(description = "原密码") @RequestParam String oldPassword,
            @Parameter(description = "新密码") @RequestParam String newPassword) {
        Account account = accountService.findByUsername(username);
        if (account == null) {
            return Result.error("用户不存在");
        }

        String MD5oldPassword = Md5Util.getMD5String(oldPassword);
        if (!account.getPassword().equals(MD5oldPassword)) {
            return Result.error("原密码错误");
        }

        String MD5newPassword = Md5Util.getMD5String(newPassword);
        accountService.updatePassword(username, MD5newPassword);
        return Result.success();
    }

    @PostMapping("/updateUsername")
    public Result updateUsername(
            @Parameter(description = "旧用户名") @RequestParam String oldUsername,
            @Parameter(description = "新用户名") @RequestParam String newUsername,
            @Parameter(description = "密码") @RequestParam String password) {
        Account account = accountService.findByUsername(oldUsername);
        if (account == null) {
            return Result.error("用户不存在");
        }

        if (accountService.checkUsername(newUsername)) {
            return Result.error("新用户名已存在");
        }

        String MD5password = Md5Util.getMD5String(password);
        String password1 = account.getPassword();
        if (!password1.equals(MD5password)) {
            return Result.error("原密码错误");
        }

        accountService.updateUsername(oldUsername, newUsername);
        // userService.updateUsername(oldUsername, newUsername);

        return Result.success();
    }


    @PostMapping("/logout")
    public Result logout(@Parameter(description = "认证token") @RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            stringRedisTemplate.delete(token);
        }
        return Result.success();
    }
}
