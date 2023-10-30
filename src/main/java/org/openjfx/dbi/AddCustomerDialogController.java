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

public class AddCustomerDialogController {

    @FXML
    private GridPane gpAddCustomer;

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
    void saveNewCustomer(ActionEvent event) throws SQLException {
        Stage stage = (Stage) btSave.getScene().getWindow();

        // create list of all text fields in the grid pane
        FilteredList<Node> tfFields = gpAddCustomer.getChildren()
                .filtered(node -> node instanceof TextField);

        // pass the list to a utility function to determine whether all necessary data
        // was filled in
        if (inputIsValid(tfFields)) {
            Customer newCustomer = new Customer(tfCustomerName.getText(), tfContactLastName.getText(),
                    tfContactFirstName.getText(), tfPhone.getText(), tfAddress.getText(), tfCity.getText(),
                    tfCountry.getText());
            CustomerDAO.addCustomer(newCustomer);
            stage.close();
        }

    }

    private Boolean inputIsValid(FilteredList<Node> tfFields) {
        for (Node tfField : tfFields) {
            if (!(((TextField) tfField).getId().equals("tfCustomerNumber"))
                    && ((TextField) tfField).getText().isEmpty()) {
                HomePageController.showAlert(Alert.AlertType.ERROR, "Form Error!",
                        "All text fields need to be filled.");
                return false;
            }
        }

        return true;
    }

    @FXML
    void cancelAddingNewCustomer(ActionEvent event) {
        Stage stage = (Stage) btCancel.getScene().getWindow();

        stage.close();
    }

}
