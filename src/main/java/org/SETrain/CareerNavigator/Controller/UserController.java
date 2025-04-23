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

  @Operation(summary = "更新用户昵称")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateNickname/{id}")
  public ResponseEntity<Result> updateNickname(@Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "新昵称") @RequestParam String nickname) {
    int result = userService.updateNickname(id, nickname); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新昵称失败")); // 使用 ResponseEntity 包装错误结果
  }

  // 更新性别
  @Operation(summary = "更新用户性别")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateSex/{id}")
  public ResponseEntity<Result> updateSex(
      @Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "性别") @RequestParam String sex) {
    int result = userService.updateSex(id, sex); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新性别失败")); // 使用 ResponseEntity 包装错误结果
  }

  // 更新年龄
  @Operation(summary = "更新用户年龄")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateAge/{id}")
  public ResponseEntity<Result> updateAge(
      @Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "年龄") @RequestParam Integer age) {
    int result = userService.updateAge(id, age); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新年龄失败")); // 使用 ResponseEntity 包装错误结果
  }

  // 更新教育背景
  @Operation(summary = "更新用户教育背景")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateEducation/{id}")
  public ResponseEntity<Result> updateEducation(
      @Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "教育背景") @RequestParam String education) {
    int result = userService.updateEducation(id, education); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新教育背景失败")); // 使用 ResponseEntity 包装错误结果
  }

  // 更新专业
  @Operation(summary = "更新用户专业")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateMajor/{id}")
  public ResponseEntity<Result> updateMajor(
      @Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "专业") @RequestParam String major) {
    int result = userService.updateMajor(id, major); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新专业失败")); // 使用 ResponseEntity 包装错误结果
  }

  // 更新电话
  @Operation(summary = "更新用户电话")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateTel/{id}")
  public ResponseEntity<Result> updateTel(
      @Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "电话") @RequestParam String tel) {
    int result = userService.updateTel(id, tel); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新电话失败")); // 使用 ResponseEntity 包装错误结果
  }

  // 更新邮箱
  @Operation(summary = "更新用户邮箱")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateEmail/{id}")
  public ResponseEntity<Result> updateEmail(
      @Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "邮箱") @RequestParam String email) {
    int result = userService.updateEmail(id, email); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新邮箱失败")); // 使用 ResponseEntity 包装错误结果
  }

  // 更新期望职位
  @Operation(summary = "更新用户期望职位")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateExpectedJob/{id}")
  public ResponseEntity<Result> updateExpectedJob(
      @Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "期望职位") @RequestParam String expectedJob) {
    int result = userService.updateExpectedJob(id, expectedJob); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新期望职位失败")); // 使用 ResponseEntity 包装错误结果
  }

  // 更新毕业时间
  @Operation(summary = "更新用户毕业时间")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "更新失败")
  })
  @PutMapping("/updateGraduationTime/{id}")
  public ResponseEntity<Result> updateGraduationTime(
      @Parameter(description = "用户ID") @PathVariable Integer id,
      @Parameter(description = "毕业时间") @RequestParam String graduationTime) {
    int result = userService.updateGraduationTime(id, graduationTime); // 获取更新结果
    if (result > 0) {
      return ResponseEntity.ok(Result.success()); // 使用 ResponseEntity 包装成功结果
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error("更新毕业时间失败")); // 使用 ResponseEntity 包装错误结果
  }

}