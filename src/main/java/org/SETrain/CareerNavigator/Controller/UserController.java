package org.SETrain.CareerNavigator.Controller;

import org.SETrain.CareerNavigator.Entity.Personalinfo;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Entity.User;
import org.SETrain.CareerNavigator.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Map;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
  @Autowired
  private UserService userService;

  @Operation(summary = "根据ID查询用户")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "查询成功"),
      @ApiResponse(responseCode = "400", description = "用户不存在")
  })
  @GetMapping("/{id}")
  public Result findById(@Parameter(description = "用户ID") @PathVariable Integer id) {
    User user = userService.findById(id);
    if (user == null) {
      return Result.error("用户不存在");
    }
    return Result.success(user);
  }

  @Operation(summary = "根据用户名查询用户")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "查询成功"),
      @ApiResponse(responseCode = "400", description = "用户不存在")
  })
  @GetMapping("/username/{username}")
  public Result findByUsername(@Parameter(description = "用户名") @PathVariable String username) {
    User user = userService.findByUsername(username);
    if (user == null) {
      return Result.error("用户不存在");
    }
    return Result.success(user);
  }

  @Operation(summary = "查询所有用户")
  @GetMapping("/list")
  public Result findAll() {
    List<User> users = userService.findAll();
    return Result.success(users);
  }

  @Operation(summary = "添加用户")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "添加成功"),
      @ApiResponse(responseCode = "400", description = "添加失败")
  })
  @PostMapping("/add")
  public Result insert(@Parameter(description = "用户信息") @RequestBody User user) {
    int result = userService.insert(user);
    if (result > 0) {
      return Result.success();
    }
    return Result.error("添加用户失败");
  }

  @PutMapping("/update")
  public ResponseEntity<Result> updateUser(@RequestBody User user) {
    user.setId(user.getId()); // 确保ID一致

    int updateResult = userService.update(user);
    if (updateResult > 0) {
      return ResponseEntity.ok(Result.success(user)); // 返回成功并附带更新后的用户数据
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Result.error("更新用户失败"));
    }
  }

  @Operation(summary = "删除用户")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "删除成功"),
      @ApiResponse(responseCode = "400", description = "删除失败")
  })
  @DeleteMapping("/{id}")
  public Result deleteById(@Parameter(description = "用户ID") @PathVariable Integer id) {
    int result = userService.deleteById(id);
    if (result > 0) {
      return Result.success();
    }
    return Result.error("删除用户失败");
  }

}