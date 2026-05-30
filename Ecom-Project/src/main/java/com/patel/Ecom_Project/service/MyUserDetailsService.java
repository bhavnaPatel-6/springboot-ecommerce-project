package com.patel.Ecom_Project.service;

import com.patel.Ecom_Project.model.Users;
import com.patel.Ecom_Project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user=repo.findByUsername(username).orElse(null);
    if(user == null){
        throw new UsernameNotFoundException("User Not Found");
    }
    return new User(
            user.getUsername(),
            user.getPassword(),
            List.of( new SimpleGrantedAuthority("USER"))
    );

    }
}