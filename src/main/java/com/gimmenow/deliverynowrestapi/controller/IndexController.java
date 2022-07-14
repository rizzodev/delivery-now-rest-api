package com.gimmenow.deliverynowrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class IndexController {

    @GetMapping()
    public String index() {
        return "Welcome To Delivery Now Rest Api Application !";
    }

}
