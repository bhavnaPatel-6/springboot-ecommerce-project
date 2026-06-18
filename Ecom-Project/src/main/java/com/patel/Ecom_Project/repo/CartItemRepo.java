package com.patel.Ecom_Project.repo;

import com.patel.Ecom_Project.model.Cart;
import com.patel.Ecom_Project.model.CartItem;
import com.patel.Ecom_Project.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCart(Cart cart);

Optional<CartItem>findByCartAndProduct(Cart cart, Product product);


}
