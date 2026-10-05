package com.ecommerce.repository;

import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.mapper.VendorMapper;
import com.ecommerce.model.Vendor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendorRepository {
    private final JdbcTemplate jdbcTemplate;
    private final VendorMapper vendorMapper;

    public VendorRepository(JdbcTemplate jdbcTemplate, VendorMapper vendorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.vendorMapper = vendorMapper;
    }

    public List<Vendor> getVendorByID(int id) {
        String sql="select * from vendor where id = ?";
        return jdbcTemplate.query(sql,vendorMapper,id);
    }

    public void insertVendor(Vendor vendor) {
        String sql="insert into vendor values(?,?,?)";
        Object[] values=new Object[]{vendor.getId(),vendor.getName(),vendor.getEmail()};
        jdbcTemplate.update(sql,values);
    }

}
