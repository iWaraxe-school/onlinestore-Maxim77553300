package by.issoft.store.service;

import by.issoft.domain.Category;
import by.issoft.store.dao.CategoryDao;
import by.issoft.store.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService extends ConnectionUtil implements CategoryDao {

    Connection connection = getConnection();

    @Override
    public void add(Category category) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO category (id,categoryName) VALUES (?,?)";


        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getNameCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Category> getAll() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM category";

        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setNameCategory(resultSet.getString("categoryName"));
                categoryList.add(category);
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return categoryList;
    }

    @Override
    public Category getById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM category WHERE id = ?";
        Category category = new Category();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            category.setNameCategory(resultSet.getString("categoryName"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return category;
    }

    @Override
    public void delete(Category category) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "DELETE * FROM category WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void addListCategory(List<Category> categoryList) throws SQLException {
        createTables();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO category (id,categoryName) VALUES(?,?)";

        for (Category category : categoryList) {
            try {

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, category.getId());
                preparedStatement.setString(2, category.getNameCategory());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

//        finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }


    }
}
