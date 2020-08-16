package com.sky.jsoncompare.controller;

import com.sky.jsoncompare.service.JCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JCController {

    private JCService jcService;

    @Autowired
    public JCController(JCService jcService) {
        this.jcService = jcService;
    }

    @PostMapping(value = "/compare")
    public String compare(@RequestParam String s1, @RequestParam String s2) {
        System.out.println(s1+"-------nn------------"+s2);
        return jcService.compareJson(s1, s2);
    }
}
