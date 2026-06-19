package com.patel.Ecom_Project.service;


import com.patel.Ecom_Project.dto.CheckOutRequest;
import com.patel.Ecom_Project.model.Cart;
import com.patel.Ecom_Project.model.Order;
import com.patel.Ecom_Project.model.Product;
import com.patel.Ecom_Project.model.Users;
import com.patel.Ecom_Project.repo.CartItemRepo;
import com.patel.Ecom_Project.repo.OrderRepo;
import com.patel.Ecom_Project.repo.ProductRepo;
import com.patel.Ecom_Project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    public Order placeOrder(CheckOutRequest request) {

        Cart cart = cartService.getCart();

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();

        order.setUser(cart.getUser());

        order.setCustomerName(request.getCustomerName());
        order.setPhone(request.getPhone());
        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setState(request.getState());
        order.setPincode(request.getPincode());
        order.setPaymentMethod(request.getPaymentMethod());

        Product product = cart.getCartItems()
                .get(0)
                .getProduct();

        order.setProduct(product);

        double total = cart.getCartItems()
                .stream()
                .mapToDouble(item ->
                        item.getProduct().getPrice()
                                * item.getQuantity())
                .sum();

        order.setTotalAmount(total);

        order.setStatus("PLACED");

        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepo.save(order);

        // clear cart properly
        cartService.clearCart();

        return savedOrder;
    }































    
    public  Order buyNow(Integer productId, CheckOutRequest request){

        Authentication auth= SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username=auth.getName();

        Users user=userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("user not found !"));

        Product product=productRepo.findById(productId)
                .orElseThrow(()->new RuntimeException("Product not found "));

        Order order=new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
        order.setCustomerName(request.getCustomerName());
        order.setPhone(request.getPhone());
        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setState(request.getState());
        order.setPincode(request.getPincode());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setTotalAmount((double) product.getPrice());

        return orderRepo.save(order);
    }

    public List<Order> getMyOrders() {

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = auth.getName();

        Users user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return orderRepo.findByUser(user);
    }
    public Order cancelOrder(Integer orderId){
        Order order=orderRepo.findById(orderId)
                .orElseThrow(()->new RuntimeException("Order Not Found"));
        order.setStatus("CANCELLED");
        return orderRepo.save(order);
    }
    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }
}
