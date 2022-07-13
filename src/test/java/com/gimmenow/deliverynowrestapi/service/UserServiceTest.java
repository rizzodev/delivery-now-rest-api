package com.gimmenow.deliverynowrestapi.service;

import com.gimmenow.deliverynowrestapi.dto.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void isUserExistTest() {
        String username = "rizzodevs";

        boolean expected = true;
        boolean result = userService.isUserExist(username);

        System.out.println(result);
        assertEquals(expected, result);
    }

    @Test
    public void createUserTest() {
        boolean expected = false;
        User u = User.builder()
                .username("rizzodevs")
                .name("rizzo name")
                .email("mzo.jobs@gmail.com")
                .password("@123Abc@")
                .gender("male")
                .build();

        boolean result = userService.createUser(u);
        System.out.println("user created : " + result);
        assertEquals(expected, result);
    }

}
