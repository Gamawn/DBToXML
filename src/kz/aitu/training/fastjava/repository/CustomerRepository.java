package kz.aitu.training.fastjava.repository;

import kz.aitu.training.fastjava.data.PostgreSQL;
import kz.aitu.training.fastjava.models.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private final Connection connection;

    public CustomerRepository(Connection db) {
        connection = db;
    }
    public List<Customer> getAllCustomers() {
        String query = "select * from customer order by id;";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("phone")
                );
                customerList.add(customer);
            }
            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}