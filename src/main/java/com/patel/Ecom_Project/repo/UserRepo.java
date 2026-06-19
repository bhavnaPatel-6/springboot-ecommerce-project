package com.patel.Ecom_Project.repo;

import com.patel.Ecom_Project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Integer> {
     Optional<Users> findByUsername(String username);

    //Users findByUsername(String username);
}
