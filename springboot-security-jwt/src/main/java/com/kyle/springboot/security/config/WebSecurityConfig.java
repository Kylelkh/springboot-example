package com.kyle.springboot.security.config;

import com.kyle.springboot.security.domain.enums.UserRoleEnum;
import com.kyle.springboot.security.domain.security.JwtAuthenticationTokenFilter;
import com.kyle.springboot.security.domain.security.RestAuthenticationEntryPoint;
import com.kyle.springboot.security.domain.security.RestfulAccessDeniedHandler;
import com.kyle.springboot.security.domain.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] staticResources = {
    "/",
    "/*.html",
    "/favicon.ico",
    "/**/*.html",
    "/**/*.css",
    "/**/*.js",
    "/swagger-resources/**",
    "/v2/api-docs/**"
  };
  private static final String userResources = "/user/**";
  private static final String adminResources = "/admin/**";
  private static final String[] whiteList = {"/", "/login", "/register", "/actuator/**"};

  @Autowired private UserDetailService userDetailService;
  @Autowired private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
  @Autowired private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement() // login by JWT token，close session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, staticResources)
        .permitAll()
        .antMatchers(whiteList)
        .permitAll()
        .antMatchers(userResources)
        .hasAnyAuthority(UserRoleEnum.USER.getRole())
        .antMatchers(adminResources)
        .hasAnyAuthority(UserRoleEnum.ADMIN.getRole())
        .antMatchers(HttpMethod.OPTIONS) // permit all options request for cross website
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable()
        .logout()
        .permitAll();
    // disable cache
    http.headers().cacheControl();
    // add JWT filter
    http.addFilterBefore(
        jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    http.exceptionHandling()
        .accessDeniedHandler(restfulAccessDeniedHandler) // for api access
        .authenticationEntryPoint(restAuthenticationEntryPoint); // for no login check
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return username -> {
      UserDetails admin = userDetailService.loadUserByUsername(username);
      if (admin != null) {
        return admin;
      }
      throw new UsernameNotFoundException("User name not found ^_^");
    };
  }

  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter();
  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
