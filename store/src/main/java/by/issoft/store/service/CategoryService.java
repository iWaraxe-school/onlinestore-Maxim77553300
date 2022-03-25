package by.issoft.store.service;

import by.issoft.domain.Category;
import by.issoft.store.dao.CategoryDao;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService extends CategoryDao {

    void add(Category category) throws SQLException;

    Category getById(Integer id) throws SQLException;

    void delete(Category category);

    void addListCategory(List<Category> categoryList) throws SQLException;
}
