package org.SETrain.CareerNavigator.Controller;

import com.github.pagehelper.PageInfo;
import org.SETrain.CareerNavigator.Entity.Personalinfo;
import org.SETrain.CareerNavigator.Entity.Result;
import org.SETrain.CareerNavigator.Entity.User;
import org.SETrain.CareerNavigator.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @GetMapping("/{id}")
  public Result findById(@Parameter(description = "用户ID") @PathVariable Integer id) {
    User user = userService.findById(id);
    if (user == null) {
      return Result.error("用户不存在");
    }
    return Result.success(user);
  }

  @GetMapping("/username/{username}")
  public Result findByUsername(@Parameter(description = "用户名") @PathVariable String username) {
    User user = userService.findByUsername(username);
    if (user == null) {
      return Result.error("用户不存在");
    }
    return Result.success(user);
  }

    @GetMapping("/list")
    public Result getUsers(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            // 分页查询
            PageInfo<User> pageInfo = userService.findPage(pageNum, pageSize);
            return Result.success(pageInfo);
        } else {
            // 如果没有提供分页参数，则返回所有用户
            List<User> users = userService.findAll();
            return Result.success(users);
        }
    }

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

  @DeleteMapping("/{id}")
  public Result deleteById(@Parameter(description = "用户ID") @PathVariable Integer id) {
    int result = userService.deleteById(id);
    if (result > 0) {
      return Result.success();
    }
    return Result.error("删除用户失败");
  }

}