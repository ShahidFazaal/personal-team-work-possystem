package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.CustomerTM;
import util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashBoardController {
    public JFXTextField txt_Customer_Name;
    public JFXTextField txt_Customer_Address;
    public JFXTextField txt_Customer_Contact;
    public Label lbl_Customer_ID;
    public JFXButton btn_Add_New_Customer;
    public JFXButton btn_Save_Customer;
    public JFXButton btn_Delete_Customer;
    public TableView<CustomerTM>tbl_Customer_details;

    public void btn_ADDnew_Customer(ActionEvent actionEvent) {
        tbl_Customer_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tbl_Customer_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tbl_Customer_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        tbl_Customer_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerContact"));


                }


    public void btn_Save_onAction_Customer(ActionEvent actionEvent) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("select customerId from customers order by customerId desc limit 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                lbl_Customer_ID.setText("0001");
            } else {
                String lastID = resultSet.getString(1);
                String substring = lastID.substring(1, 4);
                int newID = Integer.parseInt(substring) + 1;
                if (newID < 10) {
                    lbl_Customer_ID.setText("000" + newID);
                } else if (newID < 100) {
                    lbl_Customer_ID.setText("00" + newID);
                } else {
                    lbl_Customer_ID.setText("0" + newID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btn_Delete_OnAction_Customer(ActionEvent actionEvent) {
    }
}
