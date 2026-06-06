package com.patel.Ecom_Project.service;


import com.patel.Ecom_Project.model.Cart;
import com.patel.Ecom_Project.model.CartItem;
import com.patel.Ecom_Project.model.Product;
import com.patel.Ecom_Project.model.Users;
import com.patel.Ecom_Project.repo.CartItemRepo;
import com.patel.Ecom_Project.repo.CartRepo;
import com.patel.Ecom_Project.repo.ProductRepo;
import com.patel.Ecom_Project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    public void addToCart(Integer productId){

        Authentication auth= SecurityContextHolder
                .getContext()
                .getAuthentication();

        System.out.println("AUTH = " + auth);
        System.out.println("USERNAME = " + auth.getName());

        String username=auth.getName();
        Users user=userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("user not found"));
        Cart cart=cartRepo.findByUser(user)
                .orElseGet(()->{
                    Cart newCart=new Cart();
                    newCart.setUser(user);
                    return cartRepo.save(newCart);

                });

        Product product=productRepo.findById(productId)
                .orElseThrow(()->
        new RuntimeException("Product not found "));
        Optional<CartItem> existingItem =
                cartItemRepo.findByCartAndProduct(cart, product);

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);

            cartItemRepo.save(item);

        } else {

            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(1);

            cartItemRepo.save(item);
        }
    }

    public  Cart getCart() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String username = auth.getName();
        Users user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("user not found"));
        return cartRepo.findByUser(user)
                .orElseThrow(()->
                        new RuntimeException("cart is empty"));

    }


}
