package com.gimmenow.deliverynowrestapi.controller;

import com.gimmenow.deliverynowrestapi.dto.User;
import com.gimmenow.deliverynowrestapi.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/isUserExist")
    public Map<String, Boolean> isUserExist(@RequestParam String username) {
        Map<String, Boolean> result = new HashMap();
        result.put("status", userService.isUserExist(username));
        return result;
    }

    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody User user) {
        if (userService.createUser(user)) {
            return ResponseEntity.ok("Succesfull Created !");
        }
        return ResponseEntity.internalServerError().body("failed to create user : " + user);
    }

}
