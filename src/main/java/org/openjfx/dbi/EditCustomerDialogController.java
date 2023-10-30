package org.openjfx.dbi;

import java.sql.SQLException;

import org.openjfx.dbi.model.Customer;
import org.openjfx.dbi.model.CustomerDAO;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditCustomerDialogController {

    @FXML
    private GridPane gpUpdateCustomer;

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

    @FXML
    void saveUpdatedCustomer(ActionEvent event) throws SQLException {

        Stage stage = (Stage) btSave.getScene().getWindow();

        // create list of all text fields in the grid pane
        FilteredList<Node> tfFields = gpUpdateCustomer.getChildren()
                .filtered(node -> node instanceof TextField);

        // pass the list to a utility function to determine whether all necessary data
        // was filled in
        if (inputIsValid(tfFields)) {
            Customer updatedCustomer = new Customer(Integer.parseInt(tfCustomerNumber.getText()),
                    tfCustomerName.getText(), tfContactLastName.getText(),
                    tfContactFirstName.getText(), tfPhone.getText(), tfAddress.getText(), tfCity.getText(),
                    tfCountry.getText());

            CustomerDAO.updateCustomer(updatedCustomer);
            stage.close();
        }

    }

    private Boolean inputIsValid(FilteredList<Node> tfFields) {
        for (Node tfField : tfFields) {
            if (((TextField) tfField).getText().isEmpty()) {
                HomePageController.showAlert(Alert.AlertType.ERROR, "Form Error!",
                        "All text fields need to be filled.");
                return false;
            }
        }

        return true;
    }

    @FXML
    void cancelUpdatingCustomer(ActionEvent event) {
        Stage stage = (Stage) btCancel.getScene().getWindow();

        stage.close();
    }

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
