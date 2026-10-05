package com.ecommerce.main;

import com.ecommerce.config.AppConfig;
import com.ecommerce.dto.ProductDetailsDto;
import com.ecommerce.enums.ProductStatus;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.Vendor;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.VendorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean(ProductService.class);
        CategoryService categoryService = context.getBean(CategoryService.class);
        VendorService vendorService = context.getBean(VendorService.class);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("------ECOMMERCE APP------");
            System.out.println("1. Add Product");
            System.out.println("2. Find Product by ID");
            System.out.println("3. Update stock quantity");
            System.out.println("4. Find Product count by Vendor");
            System.out.println("0. to Exit");
            int choice = sc.nextInt();
            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }
            switch (choice) {
                case 1 -> {
                    Product product = new Product();
                    sc.nextLine();
                    System.out.println("Enter Product Name: ");
                    product.setProductName(sc.nextLine());
                    System.out.println("Enter Product Price: ");
                    product.setPrice(sc.nextDouble());
                    System.out.println("Enter Product Stock Quantity: ");
                    product.setStockQuantity(sc.nextInt());
                    product.setProductStatus(ProductStatus.ACTIVE);

                    // setting category for product else creating a new category
                    System.out.println("Enter Product Category ID: ");
                    int cid = sc.nextInt();
                    List<Category> categories = productService.getCategoryByID(cid);
                    Category category;
                    if (categories.isEmpty()) {
                        category = new Category();
                        category.setId(cid);
                        sc.nextLine();
                        System.out.println("Enter Category Name: ");
                        category.setName(sc.nextLine());
                        System.out.println("Enter Category Description: ");
                        category.setDescription(sc.nextLine());
                        categoryService.insertCategory(category);
                        System.out.println("New Category inserted successfully");
                    } else {
                        category = categories.getFirst();
                    }
                    product.setCategory(category);


                    // setting vendor for product else creating a new vendor
                    System.out.println("Enter Product Vendor ID: ");
                    int vid = sc.nextInt();
                    List<Vendor> vendors = productService.getVendorByID(vid);
                    Vendor vendor;
                    if (vendors.isEmpty()) {
                        vendor = new Vendor();
                        vendor.setId(vid);
                        sc.nextLine();
                        System.out.println("Enter Vendor Name: ");
                        vendor.setName(sc.nextLine());
                        System.out.println("Enter Vendor Email: ");
                        vendor.setEmail(sc.nextLine());
                        vendorService.insertVendor(vendor);
                        System.out.println("New Vendor inserted successfully");
                    } else {
                        vendor = vendors.getFirst();
                    }
                    product.setVendor(vendor);

                    productService.addProduct(product);
                    System.out.println("New Product Added Successfully");
                }
                case 2 -> {
                    System.out.println("Enter Product ID: ");
                    int pid = sc.nextInt();
                    try {
                        ProductDetailsDto product = productService.getProductById(pid);
                        System.out.println("Result: ");
                        System.out.println(product);
                    } catch (ProductNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Enter Product ID: ");
                    int pid = sc.nextInt();
                    System.out.println("Enter New Product Quantity: ");
                    int newQuantity = sc.nextInt();
                    try {
                        productService.updateStock(pid, newQuantity);
                        System.out.println("Product Stock Quantity has changed successfully");
                    } catch (ProductNotFoundException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("Product Count for Each Vendor");
                    System.out.println(productService.countProductsByVendor());
                }
                default -> System.out.println("Invalid option..");
            }
        }
        sc.close();
        context.close();
    }
}