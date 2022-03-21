package by.issoft.store.dao;

import by.issoft.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {

    void add(Category category) throws SQLException;

    List<Category> getAll() throws SQLException;

    Category getById(Integer id) throws SQLException;

    void delete(Category category) throws SQLException;

    void addListCategory(List<Category> categoryList) throws SQLException;
}
