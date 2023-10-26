package org.openjfx.dbi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openjfx.dbi.util.DBConnector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDAO {
    public static ObservableList<Customer> getCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        Connection con;
        String sql;

        try {
            con = DBConnector.connect(); // DB Verbindung holen (das 1.mal neu)
            sql = "SELECT * FROM customers";
            ResultSet rs = con.createStatement().executeQuery(sql);

            while (rs.next()) {
                customers.add(new Customer(rs.getInt("customerNumber"), rs.getString("customerName"),
                        rs.getString("contactLastName"),
                        rs.getString("contactFirstName"), rs.getString("phone"), rs.getString("addressLine1"),
                        rs.getString("city"), rs.getString("country")));

            }

        } catch (SQLException err) {
            System.err.println(err.getMessage());
        }

        return customers;
    }

    public static void updateCustomer(Customer customer) throws SQLException {
        Connection con;
        String updateString = "update customers set customerName = ?, contactLastName = ?, contactFirstName = ?, ";
        updateString += "phone = ?, addressLine1 = ?, city = ?, country = ? WHERE customerNumber = ?";
        try {
            con = DBConnector.connect();
            PreparedStatement updateCustomer = con.prepareStatement(updateString);

            updateCustomer.setString(1, customer.getCustomerName());
            updateCustomer.setString(2, customer.getLastName());
            updateCustomer.setString(3, customer.getFirstName());
            updateCustomer.setString(4, customer.getPhone());
            updateCustomer.setString(5, customer.getAddressLine1());
            updateCustomer.setString(6, customer.getCity());
            updateCustomer.setString(7, customer.getCountry());
            updateCustomer.setInt(8, customer.getId());

            updateCustomer.executeUpdate();

        } catch (SQLException err) {
            System.err.println(err.getMessage());
        }
    }

}
