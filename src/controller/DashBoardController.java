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

public class DashBoardController {
    public JFXTextField txt_Customer_Name;
    public JFXTextField txt_Customer_Address;
    public JFXTextField txt_Customer_Contact;
    public Label lbl_Customer_ID;
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

        LoadCustomerInDatabase();
    }
    public void btn_ADDnew_Customer(ActionEvent actionEvent) {

    }


    public void btn_Save_onAction_Customer(ActionEvent actionEvent) throws SQLException {
        int customerId = Integer.parseInt(txt_Customer_ID.getText());
        String customerName = txt_Customer_Name.getText();
        String customerAddress = txt_Customer_Address.getText();
        int customerContact = Integer.parseInt(txt_Customer_Contact.getText());

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?)");
            preparedStatement.setObject(1, customerId);
            preparedStatement.setObject(2, customerName);
            preparedStatement.setObject(3, customerAddress);
            preparedStatement.setObject(4, customerContact);
            preparedStatement.executeUpdate();
            new Alert(Alert.AlertType.INFORMATION, "Successfully Added", ButtonType.OK).showAndWait();
            LoadCustomerInDatabase();
        LoadCustomerInDatabase();
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
