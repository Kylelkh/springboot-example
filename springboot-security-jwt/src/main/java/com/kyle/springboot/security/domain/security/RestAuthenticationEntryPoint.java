package com.kyle.springboot.security.domain.security;

import cn.hutool.json.JSONUtil;
import com.kyle.springboot.security.domain.dto.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kyle.springboot.security.domain.CommonConst.APPLICATION_JSON;
import static com.kyle.springboot.security.domain.CommonConst.UTF_8;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    response.setCharacterEncoding(UTF_8);
    response.setContentType(APPLICATION_JSON);
    response
        .getWriter()
        .println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
    response.getWriter().flush();
  }
}
