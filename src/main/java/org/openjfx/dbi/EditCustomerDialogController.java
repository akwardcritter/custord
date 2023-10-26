package org.openjfx.dbi;

import org.openjfx.dbi.model.Customer;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditCustomerDialogController {

    Scene scene;

    @FXML
    private Button btCancel;

    @FXML
    private Button btSave;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfContactFirstName;

    @FXML
    private TextField tfContactLastName;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfCustomerName;

    @FXML
    private TextField tfCustomerNumber;

    @FXML
    private TextField tfPhone;

    public void selectCustomer(Customer customer) {

        tfCustomerNumber.setText(Integer.toString(customer.getId()));
        tfCustomerName.setText(customer.getCustomerName());
        tfContactLastName.setText(customer.getLastName());
        tfContactFirstName.setText(customer.getFirstName());
        tfPhone.setText(customer.getPhone());
        tfAddress.setText(customer.getAddressLine1());
        tfCity.setText(customer.getCity());
        tfCountry.setText(customer.getCountry());
    }

}
