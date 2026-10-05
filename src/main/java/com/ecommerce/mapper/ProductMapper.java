package com.ecommerce.mapper;

import com.ecommerce.dto.ProductDetailsDto;
import com.ecommerce.enums.ProductStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<ProductDetailsDto> {

    @Override
    public ProductDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProductDetailsDto(
                rs.getLong("product_id"),
                rs.getString("product_name"),
                rs.getDouble("price"),
                rs.getInt("stock_quantity"),
                ProductStatus.valueOf(rs.getString("status")),
                rs.getString("category_name"),
                rs.getString("vendor_name"));

    }
}
