package org.openjfx.dbi;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.openjfx.dbi.model.Customer;
import org.openjfx.dbi.model.CustomerDAO;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomePageController implements Initializable {

    private ObservableList<Customer> customerList;

    private Customer selectedCustomer;

    @FXML
    private MenuItem miAddCustomer;

    @FXML
    private MenuItem miDeleteCustomer;

    @FXML
    private MenuItem miEditCustomer;

    @FXML
    private TableColumn<Customer, String> tcAddressLine1;

    @FXML
    private TableColumn<Customer, String> tcCity;

    @FXML
    private TableColumn<Customer, String> tcContactFirstName;

    @FXML
    private TableColumn<Customer, String> tcContactLastName;

    @FXML
    private TableColumn<Customer, String> tcCountry;

    @FXML
    private TableColumn<Customer, String> tcCustomerName;

    @FXML
    private TableColumn<Customer, Integer> tcCustomerNumber;

    @FXML
    private TableColumn<Customer, String> tcPhone;

    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private void handleTvMouseClicked(MouseEvent event) {
        System.out.println("clicked");
        selectedCustomer = tvCustomers.getSelectionModel().getSelectedItem();

        System.out.println(selectedCustomer);

    }

    @FXML
    void openAddCustomerDialog(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addCustomerDialog.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Add customer");
        stage.showAndWait();
    }

    @FXML
    void openDeleteCustomerDialog(ActionEvent event) throws SQLException {

        if (selectedCustomer != null) {
            // Load the FXML file for the edit dialog
            List<String> choices = Arrays.asList("NO", "YES");

            String userChoice = showChoice("Delete Customer", null,
                    "Do you really want to delete this customer? This action cannot be undone.", choices, "NO");

            if (userChoice.equals("YES")) {
                CustomerDAO.deleteCustomer(selectedCustomer);
            }

        } else {
            // If no customer is selected, show an error alert
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Please select a customer to delete");
        }

    }

    @FXML
    void openEditCustomerDialog(ActionEvent event) throws IOException {
        // Check if a customer is selected
        if (selectedCustomer != null) {
            // Load the FXML file for the edit dialog
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editCustomerDialog.fxml"));
            Parent parent = fxmlLoader.load();

            // Access the controller of the loaded FXML
            EditCustomerDialogController controller = fxmlLoader.getController();

            // Set the selected customer in the controller
            controller.selectCustomer(selectedCustomer);

            // Create a new stage for the dialog
            Stage stage = new Stage();

            // Set modality and scene
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(parent);
            stage.setScene(scene);

            // Set title and show the stage
            stage.setTitle("Edit customer");
            stage.showAndWait();
        } else {
            // If no customer is selected, show an error alert
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Please select a customer to edit");
        }
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static String showChoice(String title, String header, String content, List<String> choices,
            String selected) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(selected, choices);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerList = CustomerDAO.getCustomers();

        tvCustomers.setItems(customerList);

        tcCustomerNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tcContactLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcContactFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcAddressLine1.setCellValueFactory(new PropertyValueFactory<>("addressLine1"));
        tcCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

    }

}
