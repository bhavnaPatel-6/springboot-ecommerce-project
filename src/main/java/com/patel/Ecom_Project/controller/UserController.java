package com.patel.Ecom_Project.controller;

import com.patel.Ecom_Project.config.JwtUtil;
import com.patel.Ecom_Project.model.Users;
import com.patel.Ecom_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")

public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.registerrUser(user);
    }

    @PostMapping("/login")
    public Map<String,Object> login(
            @RequestBody Users user){

               Users validUser =
                service.verifyUser(
                        user.getUsername(),
                        user.getPassword()
                );

        if(validUser != null){

            String token =
                    jwtUtil.generateToken(
                            validUser.getUsername()
                    );

            Map<String,Object> response =
                    new HashMap<>();

            response.put(
                    "token",
                    token
            );

            response.put(
                    "role",
                    validUser.getRole()
            );

            return response;
        }

        throw new RuntimeException(
                "Invalid Credentials"
        );
    }
}
