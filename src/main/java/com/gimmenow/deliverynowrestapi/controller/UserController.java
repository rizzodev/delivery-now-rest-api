package com.gimmenow.deliverynowrestapi.controller;

import com.gimmenow.deliverynowrestapi.aws.AwsCognitoHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    @GetMapping
    public ResponseEntity<String> getUsernameList() {

        AwsCognitoHelper.listUsers();

        return ResponseEntity.ok("ok");
    }

}
