package com.patel.Ecom_Project.controller;

import com.patel.Ecom_Project.model.Cart;
import com.patel.Ecom_Project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<String>addToCart(@PathVariable Integer productId){

        System.out.println("Controller Hit");
        cartService.addToCart(productId);
        return ResponseEntity.ok("Product added to cart...");
    }
    @GetMapping
    public ResponseEntity<Cart>viewCart(){
        return  ResponseEntity.ok(cartService.getCart());
    }


}
