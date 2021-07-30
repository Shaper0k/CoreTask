package jm.task.core.jdbc.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE `users` (\n" +
                "  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `LastName` varchar(45) NOT NULL,\n" +
                "  `age` TINYINT NOT NULL ,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ")ENGINE = InnoDB AUTO_INCREMENT =1 DEFAULT CHARSET = utf8mb3";

        try {
            preparedStatement = connectionDB().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connectionDB() != null) {
                    connectionDB().close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {

        PreparedStatement preparedStatement = null;
        String sql = " DROP TABLE users";

        try {
            preparedStatement = connectionDB().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connectionDB() != null) {
                    connectionDB().close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {


        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = connectionDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    resultSet.getLong(1);

                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String sql = "delete from `users` where `id` = ?";

        try {

            preparedStatement = connectionDB().prepareStatement(sql);
            preparedStatement.setLong( 1, id);

            preparedStatement.executeUpdate();
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{

                try {
                    if (preparedStatement != null) {
                    preparedStatement.close();
                    }
                    if (connectionDB() != null) {
                        connectionDB().close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM users";

        Statement statement = null;

        try {
            statement = connectionDB().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connectionDB() != null) {
                    connectionDB().close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connectionDB().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connectionDB() != null) {
                    connectionDB().close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
