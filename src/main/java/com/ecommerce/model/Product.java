package com.ecommerce.model;

import com.ecommerce.enums.ProductStatus;

import java.math.BigDecimal;

public class Product {

    private int id;
    private String productName;
    private Double price;
    private int stockQuantity;

    private Category category; // FK category_id -> Category
    private Vendor vendor;  // FK vendor_id   -> Vendor
    private ProductStatus productStatus;

    public Product() {
    }

    public Product(int id, String productName, Double price, int stockQuantity, Category category, Vendor vendor,ProductStatus productStatus) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.vendor = vendor;
        this.productStatus = productStatus;
    }

    public Product(int id, String productName, Double price, int stockQuantity, ProductStatus status, Category category, Vendor vendor) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.vendor = vendor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category=" + category +
                ", vendor=" + vendor +
                ", productStatus=" + productStatus +
                '}';
    }
}
