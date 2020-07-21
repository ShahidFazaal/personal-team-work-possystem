package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.CustomerTM;
import util.DBConnection;

import java.sql.*;
import java.util.Optional;
import java.util.regex.Pattern;

public class DashBoardController {
    public JFXTextField txt_Customer_Name;
    public JFXTextField txt_Customer_Address;
    public JFXTextField txt_Customer_Contact;
    public JFXButton btn_Add_New_Customer;
    public JFXButton btn_Save_Customer;
    public JFXButton btn_Delete_Customer;
    public TableView<CustomerTM>tbl_Customer_details;
    public JFXTextField txt_Customer_ID;

    public void initialize(){

        tbl_Customer_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tbl_Customer_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tbl_Customer_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        tbl_Customer_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerContact"));

        tbl_Customer_details.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM newValue) {
                CustomerTM selectCustomer = tbl_Customer_details.getSelectionModel().getSelectedItem();
                if (newValue == null) {
                    btn_Save_Customer.setText("SAVE");
                } else {
                    txt_Customer_ID.setText(String.valueOf(selectCustomer.getCustomerId()));
                    txt_Customer_Name.setText(selectCustomer.getCustomerName());
                    txt_Customer_Address.setText(selectCustomer.getCustomerAddress());
                    txt_Customer_Contact.setText(String.valueOf(selectCustomer.getCustomerContact()));

                    btn_Save_Customer.setText("UPDATE");
                }
            }
        });

        LoadCustomerInDatabase();
    }
    public void btn_ADDnew_Customer(ActionEvent actionEvent) {
        txt_Customer_ID.clear();
        txt_Customer_Name.clear();
        txt_Customer_Address.clear();
        txt_Customer_Contact.clear();
        txt_Customer_ID.requestFocus();
    }


    public void btn_Save_onAction_Customer(ActionEvent actionEvent) throws SQLException {

        if (txt_Customer_ID.getText().trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter customerID..", ButtonType.OK).show();
            txt_Customer_ID.requestFocus();
            return;
        }
        Pattern IDPattern = Pattern.compile("^[0-9]{3}");
        if (!txt_Customer_ID.getText().trim().matches(String.valueOf(IDPattern))) {
            new Alert(Alert.AlertType.ERROR, "Please check the CustomerID", ButtonType.OK).show();
            txt_Customer_ID.requestFocus();
            return;
        }
        if (txt_Customer_Name.getText().trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter customer name..", ButtonType.OK).show();
            txt_Customer_Name.requestFocus();
            return;
        }
        Pattern namePattern = Pattern.compile("^[A-Za-z ]+");
        if (!txt_Customer_Name.getText().trim().matches(String.valueOf(namePattern))) {
            new Alert(Alert.AlertType.ERROR, "Please check the name", ButtonType.OK).show();
            txt_Customer_Name.requestFocus();
            return;
        }
        if (txt_Customer_Address.getText().trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter customer address..", ButtonType.OK).show();
            txt_Customer_Address.requestFocus();
            return;
        }
        Pattern addressPattern = Pattern.compile("^[A-Za-z0-9 ,/]+");
        if (!txt_Customer_Address.getText().trim().matches(String.valueOf(addressPattern))) {
            new Alert(Alert.AlertType.ERROR, "Please check the address", ButtonType.OK).show();
            txt_Customer_Address.requestFocus();
            return;
        }
        if (txt_Customer_Contact.getText().trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter customer contact No..", ButtonType.OK).show();
            txt_Customer_Contact.requestFocus();
            return;
        }
        Pattern contactPattern = Pattern.compile("^[0-9]{3}[0-9]{7}+");
        if (!txt_Customer_Contact.getText().trim().matches(String.valueOf(contactPattern))) {
            new Alert(Alert.AlertType.ERROR, "Please check the Contact No", ButtonType.OK).show();
            txt_Customer_Contact.requestFocus();
            return;
        }
        int customerId = Integer.parseInt(txt_Customer_ID.getText());
        String customerName = txt_Customer_Name.getText();
        String customerAddress = txt_Customer_Address.getText();
        int customerContact = Integer.parseInt(txt_Customer_Contact.getText());

        if (btn_Save_Customer.getText().equals("SAVE")) {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?)");
            preparedStatement.setObject(1, customerId);
            preparedStatement.setObject(2, customerName);
            preparedStatement.setObject(3, customerAddress);
            preparedStatement.setObject(4, customerContact);
            preparedStatement.executeUpdate();
            new Alert(Alert.AlertType.INFORMATION, "Successfully Added", ButtonType.OK).showAndWait();
            LoadCustomerInDatabase();
        } else {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update customer details ?", ButtonType.YES, ButtonType.NO);
            Optional confirmType = confirm.showAndWait();
            if (confirmType.get() == ButtonType.YES) {

                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("update customers set customerName=?, customerAddress=?, contactNo=? where customerId=?");
                preparedStatement.setObject(1, customerName);
                preparedStatement.setObject(2, customerAddress);
                preparedStatement.setObject(3, customerContact);
                preparedStatement.setObject(4, customerId);
                preparedStatement.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "Successfully ", ButtonType.OK).showAndWait();
                LoadCustomerInDatabase();
            }
        }
        txt_Customer_ID.clear();
        txt_Customer_Name.clear();
        txt_Customer_Address.clear();
        txt_Customer_Contact.clear();
    }

    public void btn_Delete_OnAction_Customer(ActionEvent actionEvent) {
    }
    private void LoadCustomerInDatabase() {
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customers");
            tbl_Customer_details.getItems().clear();
            while (resultSet.next()) {
               int customerId = resultSet.getInt(1);
                String customerName = resultSet.getString(2);
                String customerAddress = resultSet.getString(3);
                int customerContact = resultSet.getInt(4);

                CustomerTM customer = new CustomerTM(customerId, customerName, customerAddress, customerContact);
                tbl_Customer_details.getItems().add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
