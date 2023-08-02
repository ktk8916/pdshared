package com.playdata.pdshared.config.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/login/oauth/code/google")
public class OauthController {
    @GetMapping
    public void tokens(
            @RequestParam("code") String code
    ){
        System.out.println(code);
    }



}
