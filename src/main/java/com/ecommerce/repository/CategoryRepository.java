package com.ecommerce.repository;

import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CategoryMapper categoryMapper;

    public CategoryRepository(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryMapper = categoryMapper;
    }

    public List<Category> getCategoryByID(Long id) {
        String sql="select * from category where id = ?";
        return jdbcTemplate.query(sql,categoryMapper,id);
    }

    public void insertCategory(Category category) {
        String sql="insert into category values(?,?,?)";
        Object[] values=new Object[]{category.getId(),category.getName(),category.getDescription()};
        jdbcTemplate.update(sql,values);
    }
}
