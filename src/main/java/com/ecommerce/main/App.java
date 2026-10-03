package com.ecommerce.main;

import com.ecommerce.dto.ProductDetailsDto;
import com.ecommerce.enums.ProductStatus;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.Vendor;
import com.ecommerce.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.ecommerce.config.AppConfig;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = (ProductService) context.getBean(ProductService.class);

        // Task 1 -- insert / add product
        Category category = new Category();
        category.setId(1L);

        Vendor vendor = new Vendor();
        vendor.setId(1L);

        Product product = new Product();
        product.setProductName("USB Cable");
        product.setPrice(199.00);
        product.setStockQuantity(100);
        product.setProductStatus(ProductStatus.ACTIVE);
        product.setCategory(category);
        product.setVendor(vendor);

        Category category2 = new Category();
        category2.setId(2L);

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);

        Product product2 = new Product();
        product2.setProductName("Wireless Mouse");
        product2.setPrice(799.00);
        product2.setStockQuantity(50);
        product2.setProductStatus(ProductStatus.ACTIVE);
        product2.setCategory(category2);
        product2.setVendor(vendor2);

        productService.addProduct(product2);

        productService.addProduct(product);
        System.out.println("Task 1: product inserted");

        // Task 2 -- find product by id
        try {
            ProductDetailsDto found = productService.getProductById(1L);
            System.out.println("Task 2: " + found);

            // Task - 3 -- update stock quantity
            productService.updateStock(1L, 75);
            System.out.println("Task 3: stock updated -> " + productService.getProductById(1L).stockQuantity());
        }
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Task - 4 -- count products by vendor
        System.out.println("Task 4: " + productService.countProductsByVendor());
    }
}
