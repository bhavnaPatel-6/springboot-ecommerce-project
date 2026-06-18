package com.patel.Ecom_Project.repo;

import com.patel.Ecom_Project.model.Order;
import com.patel.Ecom_Project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository <Order,Integer> {

    List<Order> findByUser(Users user);
}
