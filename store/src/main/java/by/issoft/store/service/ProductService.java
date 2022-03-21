package by.issoft.store.service;

import by.issoft.domain.Product;
import by.issoft.domain.ProductPrice;
import by.issoft.store.dao.ProductDao;
import by.issoft.store.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends ConnectionUtil implements ProductDao {

    Connection connection = getConnection();

    @Override
    public void add(Product product) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO products (name,price,rate,category_id) VALUES (?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getRate());
            preparedStatement.setInt(4,product.getCategory_id());
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
    public List<Product> getAll() throws SQLException {
        List<Product> productList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM products";

        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Product product = Product.newBuilder()
                        .setName(resultSet.getString("name"))
                        .setPrice(ProductPrice.of(resultSet.getDouble("price")))
                        .setRate(resultSet.getDouble("rate"))
                        .setId(resultSet.getInt("id"))
                        .setCategory_id(resultSet.getInt("category_id"))
                        .build();

                productList.add(product);
                preparedStatement.executeUpdate();
            }

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
        return productList;
    }

    @Override
    public Product getById(Integer id) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM products WHERE id =?";
        Product product = new Product();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            product = Product.newBuilder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setPrice(ProductPrice.of(resultSet.getDouble("price")))
                    .setRate(resultSet.getDouble("rate"))
                    .setCategory_id(resultSet.getInt("category_id"))
                    .build();

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
        return product;
    }

    @Override
    public void delete(Product product) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM products WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
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
    public void addListProduct(List<Product> productList) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO products (name,price,rate,category_id) VALUES (?,?,?,?)";

        for(Product product : productList) {
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setDouble(2, product.getPrice());
                preparedStatement.setDouble(3, product.getRate());
                preparedStatement.setInt(4, product.getCategory_id());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            finally {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            }
        }
    }
}
