package com.patel.Ecom_Project.controller;

import com.patel.Ecom_Project.dto.CheckOutRequest;
import com.patel.Ecom_Project.model.Order;
import com.patel.Ecom_Project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(
            @RequestBody CheckOutRequest request){

        return ResponseEntity.ok(
                orderService.placeOrder(request)
        );
    }

    @PostMapping("/buy-now/{productId}")
    public ResponseEntity<Order> buyNow(
            @PathVariable Integer productId,
            @RequestBody CheckOutRequest request){

        return ResponseEntity.ok(
                orderService.buyNow(productId, request)
        );
    }
    @PutMapping("/cancel/{orderId}")
    public  ResponseEntity<Order>cancelOrder(@PathVariable Integer orderId){

        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }


    @GetMapping("/my-orders")
    public  ResponseEntity<List<Order>>getMyOrders(){

        return ResponseEntity.ok(orderService.getMyOrders());
    }
}
