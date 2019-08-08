package com.amqp.controller;

import com.amqp.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-07-25 15:04
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @Autowired
    private TestServiceImpl testService;

    @GetMapping("/send")
    public String sendMessage(String msg){
        return testService.send(msg);
    }




}
