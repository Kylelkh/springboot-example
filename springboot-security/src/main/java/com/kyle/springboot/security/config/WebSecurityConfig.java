package com.kyle.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/home", "/security/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .antMatchers("/users/**")
        .hasRole("USER")
        .anyRequest()
        .authenticated()
        .and()
        .cors()
        .disable()
        .formLogin()
        .loginProcessingUrl("/security/login")
        //        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //  @Bean
  //  @Override
  //  public UserDetailsService userDetailsService() {
  //    // TODO： get user detail from db rather than memory
  //    UserDetails user =
  //        User.withDefaultPasswordEncoder()
  //            .username("user")
  //            .password("$2a$10$fozesoRW5lQytW8Boy0zAeX7Yi.J4OMLpysJA7G5zSrIc.EzTGKYO")
  //            //                        .password(bCryptPasswordEncoder.encode("password"))
  //            .roles("USER")
  //            .build();
  //    UserDetails admin =
  //        User.withDefaultPasswordEncoder()
  //            .username("admin")
  //            .password("password")
  //            .roles("ADMIN")
  //            .build();
  //
  //    return new InMemoryUserDetailsManager(user, admin);
  //  }
}
