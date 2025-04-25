package org.SETrain.CareerNavigator.Controller;

import org.SETrain.CareerNavigator.Entity.*;
import org.SETrain.CareerNavigator.Service.UserProfileService;
import org.SETrain.CareerNavigator.DTO.UserProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Map;

@Tag(name = "用户画像管理")
@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Operation(summary = "获取用户画像数据")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "400", description = "获取失败")
    })
    @GetMapping("/{username}")
    public Map<String, Object> getUserProfile(@Parameter(description = "用户名") @PathVariable String username) {
        return userProfileService.getUserProfile(username);
    }

}