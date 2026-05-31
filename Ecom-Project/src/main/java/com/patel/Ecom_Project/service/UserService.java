package com.patel.Ecom_Project.service;

import com.patel.Ecom_Project.model.Users;
import com.patel.Ecom_Project.repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    //user hi admin banyega database se but database ka access ahr kisi k pass nhi hoga
    public Users registerrUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
       user.setRole("USER");

        return repo.save(user);
    }

    public Users verifyUser(String username, String password) {

        Users user = repo.findByUsername(username).orElse(null);

        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }
}