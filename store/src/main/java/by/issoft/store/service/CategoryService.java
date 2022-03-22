package by.issoft.store.service;

import by.issoft.domain.Category;
import by.issoft.domain.categories.BikeCategory;
import by.issoft.domain.categories.MilkCategory;
import by.issoft.domain.categories.PhoneCategory;
import by.issoft.store.dao.CategoryDao;
import by.issoft.store.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService extends ConnectionUtil implements CategoryDao {

    Connection connection = getConnection();

    @Override
    public void add(Category category) throws SQLException {

        String sql = "INSERT INTO category (id,categoryName) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getNameCategory());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Category> getAll() throws SQLException {

        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM category";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category;
                if (resultSet.getString("categoryName").equals("Bike")) {
                    category = new BikeCategory();
                } else if (resultSet.getString("categoryName").equals("Phone")) {
                    category = new PhoneCategory();
                } else {
                    category = new MilkCategory();
                }

                category.setNameCategory(resultSet.getString("categoryName"));
                categoryList.add(category);
            }


        }
        return categoryList;
    }

    @Override
    public Category getById(Integer id) throws SQLException {

        String sql = "SELECT * FROM category WHERE id = ?";
        Category category = new Category();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            category.setNameCategory(resultSet.getString("categoryName"));
            preparedStatement.executeUpdate();
        }
        return category;
    }

    @Override
    public void delete(Category category) {

        String sql = "DELETE * FROM category WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addListCategory(List<Category> categoryList) throws SQLException {

        createTables();
        String sql = "INSERT INTO category (id,categoryName) VALUES(?,?)";

        for (Category category : categoryList) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, category.getId());
                preparedStatement.setString(2, category.getNameCategory());
                preparedStatement.executeUpdate();
            }

        }
    }
}
