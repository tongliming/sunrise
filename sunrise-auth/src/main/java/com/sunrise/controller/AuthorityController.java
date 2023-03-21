package com.sunrise.controller;

import com.sunrise.annotation.IgnoreResponseAdvice;
import com.sunrise.service.IJwtService;
import com.sunrise.model.vo.JwtToken;
import com.sunrise.model.vo.UsernameAndPassword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sunrise
 * @description: 对外暴漏授权服务接口
 * @author: T.LM
 * @date: 2023-03-02 20:46
 **/
@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@IgnoreResponseAdvice
public class AuthorityController {
    private final IJwtService jwtService;

    @PostMapping("/token")
    public JwtToken token(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        String token = jwtService.generateToken(
                usernameAndPassword.getUsername(),
                usernameAndPassword.getPassword()
        );
        log.info("token: {}", token);
        return new JwtToken(token);
    }

    @PostMapping("/register")
    public JwtToken register(@RequestBody UsernameAndPassword usernameAndPassword) throws Exception {
        return new JwtToken(jwtService.registerAndGenerateToken(
                usernameAndPassword
        ));
    }
}
