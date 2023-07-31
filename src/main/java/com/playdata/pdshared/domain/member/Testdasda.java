package com.playdata.pdshared.domain.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Testdasda {

    @GetMapping
    public String dasdas (){
        return "aaa";
    }
}
