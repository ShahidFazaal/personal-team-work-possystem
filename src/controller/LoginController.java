package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public JFXButton btnSignIn;
    public AnchorPane login;

    public void btnSignIn_OnAction(ActionEvent actionEvent) {
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
