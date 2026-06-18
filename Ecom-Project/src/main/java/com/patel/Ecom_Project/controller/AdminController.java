package com.patel.Ecom_Project.controller;

import com.patel.Ecom_Project.model.Order;
import com.patel.Ecom_Project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>>getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
