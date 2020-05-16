package com.grady.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConteoller {

    @RequestMapping("/test")
    public String test(){
        return "success";
    }
}
