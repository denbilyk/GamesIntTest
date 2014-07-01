package com.homenet.refactor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressDao {
    private static final String DRIVER_CLASS = "oracle.jdbc.ThinDriver";
    private static final String CONNECTION_URL = "jdbc:oracle:thin:@prod";
    private static final String CONNECTION_USER = "admin";
    private static final String CONNECTION_PASS = "beefhead";
    private static final String SQL_INSERT_ADDRESS = "insert into AddressEntry values (?, ?, ?)";
    private static final String SQL_SELECT_PERSON = "select * from AddressEntry where name = ?";
    private static final String SQL_SELECT_ALL = "select * from AddressEntry";


    public AddressDao() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASS);
    }


    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(Statement stm) {
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
    }


    public void addPerson(Person person) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_INSERT_ADDRESS);
            statement.setLong(1, System.currentTimeMillis());
            statement.setString(2, person.getName());
            statement.setString(3, person.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            // to log
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * Looks up the given person, null if not found.
     */
    public Person findPerson(String name) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(SQL_SELECT_PERSON);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String foundName = resultSet.getString("name");
                String phone = resultSet.getString("phoneNumber");
                if (foundName != null && phone != null) {
                    return new Person(foundName, phone);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // to log
        } finally {
            close(statement);
            close(conn);
        }
        return null;
    }

    public List<Person> getAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Person> list = new ArrayList<Person>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phoneNumber");
                if (name != null && phone != null) {
                    list.add(new Person(name, phone));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //to log
        } finally {
            close(statement);
            close(connection);
        }
        return Collections.unmodifiableList(list);
    }
}
