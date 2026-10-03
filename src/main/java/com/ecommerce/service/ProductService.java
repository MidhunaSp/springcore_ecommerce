package com.ecommerce.service;

import com.ecommerce.dto.ProductDetailsDto;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Task 1 -- insert / add product
    public void addProduct(Product product){
        if(product.getCategory()==null || product.getVendor()==null)
            throw new IllegalArgumentException("Product must have a category and vendor");
        productRepository.save(product);
    }

    // Task 2 -- find product by id
    public ProductDetailsDto getProductById(Long id){
        ProductDetailsDto product = productRepository.findById(id);
        if (product == null)
            throw new ProductNotFoundException("Product not found with id " + id);
        return product;
    }

    // Task - 3 -- update stock quantity
    public void updateStock(Long id,int newQuantity){
        if(newQuantity<0)
            throw new IllegalArgumentException("stock quantity must be positive");
        productRepository.updateStock(id,newQuantity);
    }

    // Task - 4 -- count products by vendor
    public Map<String,Integer> countProductsByVendor(){
        return productRepository.countProductsByVendor();
    }
}

