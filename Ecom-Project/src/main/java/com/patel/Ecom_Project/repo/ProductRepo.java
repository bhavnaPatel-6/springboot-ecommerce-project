package com.patel.Ecom_Project.repo;

import com.patel.Ecom_Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo  extends JpaRepository<Product,Integer> {

}
