package com.grady.business.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Grady
 * @Date 2020/5/24 14:06
 * @Version 1.0
 */
@RestController
public class ChapterController {

    @RequestMapping("/test")
    public String test() {
        return "testService.list()";
    }
}
