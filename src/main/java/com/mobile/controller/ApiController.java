package com.mobile.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping(value = "/test")
    public String test(String phoneNum) {
        return "Hello I Love Car phoneNum is "+ phoneNum;
    }

}

