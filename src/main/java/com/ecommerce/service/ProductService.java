package com.ecommerce.service;

import com.ecommerce.dto.ProductDetailsDto;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.Vendor;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<Category> getCategoryByID(int id) {
        return categoryRepository.getCategoryByID(id);
    }

    public List<Vendor> getVendorByID(int id) {
        return vendorRepository.getVendorByID(id);
    }

    // Task 1 -- insert / add product
    public void addProduct(Product product){
        if(product.getCategory()==null || product.getVendor()==null)
            throw new IllegalArgumentException("Product must have a category and vendor");
        productRepository.save(product);
    }

    // Task 2 -- find product by id
    public ProductDetailsDto getProductById(int id){
        ProductDetailsDto product = productRepository.findById(id);
        if (product == null)
            throw new ProductNotFoundException("Product not found with id " + id);
        return product;
    }

    // Task - 3 -- update stock quantity
    public void updateStock(int id,int newQuantity){
        if(newQuantity<0)
            throw new IllegalArgumentException("stock quantity must be positive");
        productRepository.updateStock(id,newQuantity);
    }

    // Task - 4 -- count products by vendor
    public Map<String,Integer> countProductsByVendor(){
        return productRepository.countProductsByVendor();
    }
}

