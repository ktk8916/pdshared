package com.playdata.pdshared.config.controller;


import com.playdata.pdshared.config.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerAutoConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@RestController
@RequestMapping("/login/oauth/code/google")
@RequiredArgsConstructor
public class OauthController {
    private final OauthService oauthService;

    @GetMapping
    public String signupAndLogin(@RequestParam("code") String code){
        String accessToken = oauthService.getGoogleAccessToken(code);
        Map googleUserInfo = oauthService.getGoogleUserInfo(accessToken);
        return oauthService.Signin(googleUserInfo);
    }


}
