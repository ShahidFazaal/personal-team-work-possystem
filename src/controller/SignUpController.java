package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public PasswordField txtReEnterPassword;
    public JFXButton btnSignUp;
    public ImageView imgTick;
    public ImageView imgTick2;
    public AnchorPane signUp;

    public void btnSignUp_OnAction(ActionEvent actionEvent) {
    }

    public void txtLoginHere_OnMouseClick(MouseEvent mouseEvent) throws IOException {
        Parent signUp = FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"));
        Scene subScene = new Scene(signUp);
        Stage primaryStage = (Stage) this.signUp.getScene().getWindow();
        primaryStage.setScene(subScene);
//        Image icon = new Image(getClass().getResourceAsStream("/resource/icons8-add-user-male-96.png"));
//        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("SignUp");
        primaryStage.centerOnScreen();
    }
}
