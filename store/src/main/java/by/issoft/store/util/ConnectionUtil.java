package by.issoft.store.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/test1?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String NAME = "root";
    private static final String PASSWORD = "77553300";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("Connection exist");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createTables() throws SQLException {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS category (id INT NOT NULL , categoryName VARCHAR(30) NOT NULL, PRIMARY KEY (id)) ;");
        preparedStatement.executeUpdate();
        PreparedStatement preparedStatement2 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `products` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `price` DOUBLE NOT NULL,\n" +
                "  `rate` DOUBLE NOT NULL,\n" +
                "  `category_id` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  INDEX `fk_category_id_idx` (`category_id` ASC) VISIBLE,\n" +
                "  CONSTRAINT `fk_category_id`\n" +
                "    FOREIGN KEY (`category_id`)\n" +
                "    REFERENCES `category` (`id`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION);");

        preparedStatement2.executeUpdate();

    }

}
