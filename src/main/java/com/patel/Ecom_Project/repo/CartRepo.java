package com.patel.Ecom_Project.repo;

import com.patel.Ecom_Project.model.Cart;
import com.patel.Ecom_Project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    Optional<Cart>findByUser(Users user);


}
