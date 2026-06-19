package com.patel.Ecom_Project.controller;

import com.patel.Ecom_Project.model.Product;
import com.patel.Ecom_Project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "hello world";
    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        return  service.getProductById(id);
    }
/*
@PostMapping("/product")
public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    System.out.println(product);
    Product savedProduct = service.addProduct(product);
    return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
}
*/
@PutMapping("/product/{id}")
public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
    Product updated = service.updateProduct(id, product);
    if (updated != null)
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    else
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
}

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = service.addProduct(product);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
@DeleteMapping("/product/{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable int id){
        Product product=service.getProductById(id);
        if(product!=null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product not found ",HttpStatus.NOT_FOUND);
        }
}
    @PostMapping("/product/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            File folder = new File("uploads/");
            if (!folder.exists()) folder.mkdir();
            String path = System.getProperty("user.dir") + "/uploads/" + fileName;
            file.transferTo(new File(path));

            return ResponseEntity.ok(fileName);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed");
        }
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @PathVariable String category){
        return ResponseEntity.ok(service.getProductsByCategory(category));
    }
}
