package com.kyle.springboot.security.domain.controller;

import com.kyle.springboot.security.domain.dto.CommonResult;
import com.kyle.springboot.security.domain.dto.UserDto;
import com.kyle.springboot.security.domain.service.UserSevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "UserController", description = "后台用户管理")
@RequestMapping()
public class UserController {
  @Autowired private UserSevice userSevice;

  @Value("${jwt.tokenHeader}")
  private String tokenHeader;

  @Value("${jwt.tokenHead}")
  private String tokenHead;

  @ApiOperation(value = "用户注册")
  @PostMapping(value = "/register")
  public CommonResult<UserDetails> register(@RequestBody UserDto userDto, BindingResult result) {
    UserDetails umsAdmin = userSevice.register(userDto);
    if (umsAdmin == null) {
      CommonResult.failed();
    }
    return CommonResult.success(umsAdmin);
  }

  @ApiOperation(value = "登录以后返回token")
  @PostMapping(value = "/login")
  public CommonResult login(@RequestBody UserDto userDto, BindingResult result) {
    String token = userSevice.login(userDto.getUsername(), userDto.getPassword());
    if (token == null) {
      return CommonResult.validateFailed("用户名或密码错误");
    }
    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put("token", token);
    tokenMap.put("tokenHead", tokenHead);
    return CommonResult.success(tokenMap);
  }

  @ApiOperation("获取用户所有权限（包括+-权限）")
  @GetMapping(value = "/permission/{userId}")
  public CommonResult<List<String>> getPermissionList(@PathVariable String userId) {
    List<String> permissionList = userSevice.getPermissionList(userId);
    return CommonResult.success(permissionList);
  }
}
