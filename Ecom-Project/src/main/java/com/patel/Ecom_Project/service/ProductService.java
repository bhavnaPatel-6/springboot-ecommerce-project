package com.patel.Ecom_Project.service;

import com.patel.Ecom_Project.model.Product;
import com.patel.Ecom_Project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
@Autowired
ProductRepo repo;
    public List<Product>getAllProducts(){
     return  repo.findAll();
    }

    public Product getProductById(int id){
      return repo.findById(id).orElse(new Product());
        //or return repo.findById(id).get();
    }

    public Product addProduct(Product product) {

        return repo.save(product);
    }
    public Product updateProduct(int id, Product product) {

            Product existing = repo.findById(id).orElse(null);

            if (existing != null) {

                // keep old image if new not provided
                if (product.getImageUrl() == null) {
                    product.setImageUrl(existing.getImageUrl());
                }

                product.setId(id);
                return repo.save(product);
            }
            return null;
        }
    public void deleteProduct(int id){
        repo.deleteById(id);
    }

}
