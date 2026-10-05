package com.ecommerce.repository;

import com.ecommerce.dto.ProductDetailsDto;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProductMapper productMapper;

    public ProductRepository(JdbcTemplate jdbcTemplate, ProductMapper productMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productMapper = productMapper;
    }

    public void save(Product product) {
        String sql = "insert into product (name, price, stock_quantity, category_id, vendor_id, status) values (?,?,?,?,?,?)";
        Object[] values = new Object[]{product.getProductName(),product.getPrice(),product.getStockQuantity(),product.getCategory().getId(),product.getVendor().getId(),product.getProductStatus().toString()};
        jdbcTemplate.update(sql,values);
}

    public ProductDetailsDto findById(int id) {
        String sql = """
                select p.id as product_id, p.name as product_name, p.price, p.stock_quantity, p.status,
                c.name as category_name, v.name as vendor_name
                from product p
                JOIN category c ON p.category_id = c.id
                JOIN vendor v ON p.vendor_id = v.id
                where p.id = ?
                """;
        List<ProductDetailsDto> list = jdbcTemplate.query(sql, productMapper, id);
        return list.isEmpty() ? null : list.getFirst();
    }

    public void updateStock(int id, int newQuantity) {
        String sql = "update product set stock_quantity = ? where id = ?";
        jdbcTemplate.update(sql,newQuantity,id);
    }

    public Map<String, Integer> countProductsByVendor() {
        String sql = """
                    select v.name as vendor_name, count(p.id) as product_count
                    from product p
                    JOIN vendor v ON p.vendor_id = v.id
                    group by v.name
                    """;
        Map<String, Integer> result = new HashMap<>();
            jdbcTemplate.query(sql, (rs, rowNum) -> {
                result.put(rs.getString(1), rs.getInt(2));
                return null;
            });
        return result;
    }
}
