package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.DBConnectionWorking;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public JFXButton btnSignIn;
    public AnchorPane login;

    public void btnSignIn_OnAction(ActionEvent actionEvent) throws SQLException, IOException {
        if (txtUserName.getText().trim().equals("") || txtPassword.getText().trim().equals("")){
            new Alert(Alert.AlertType.INFORMATION,"UseName and Password can't be empty!",ButtonType.OK).show();
            return;
        }
        String userName = txtUserName.getText();
        String passwordText = txtPassword.getText();
        PreparedStatement preparedStatement = DBConnectionWorking.getInstance().getConnection().prepareStatement("SELECT userName, password FROM users WHERE userName=? AND password=?");
        preparedStatement.setObject(1,userName);
        preparedStatement.setObject(2,passwordText);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            Parent dashBoard = FXMLLoader.load(this.getClass().getResource("/view/DashBoard.fxml"));
            Scene subScene = new Scene(dashBoard);
            Stage primaryStage = (Stage) this.login.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.setTitle("DashBoard");
            primaryStage.centerOnScreen();
        }else{
            new Alert(Alert.AlertType.ERROR,"UserName or Password is incorrect", ButtonType.OK).show();
        }

    }

    public void txtSignUp_OnMouseClick(MouseEvent mouseEvent) throws IOException {
        Parent signUp = FXMLLoader.load(this.getClass().getResource("/view/SignUp.fxml"));
        Scene subScene = new Scene(signUp);
        Stage primaryStage = (Stage) this.login.getScene().getWindow();
        primaryStage.setScene(subScene);
//        Image icon = new Image(getClass().getResourceAsStream("/resource/icons8-add-user-male-96.png"));
//        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("SignUp");
        primaryStage.centerOnScreen();
    }
}
