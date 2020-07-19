package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.DBConnectionWorking;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public PasswordField txtReEnterPassword;
    public JFXButton btnSignUp;
    public ImageView imgTick;
    public ImageView imgTick2;
    public AnchorPane signUp;

    public void initialize(){
        btnSignUp.setDisable(true);
       txtReEnterPassword.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               String p1 = txtPassword.getText();
               String p2 = txtReEnterPassword.getText();
               if (txtReEnterPassword.getText().trim().equals("")){
                   txtReEnterPassword.setStyle("-fx-border-color: null");
                   txtPassword.setStyle("-fx-border-color: null");
                   imgTick.setVisible(false);
                   imgTick2.setVisible(false);
                   btnSignUp.setDisable(true);
                   return;
               }


               if (p1.equals(p2)){
                   Image image = new Image("/resource/icons8-checked-240.png");
                   imgTick.setImage(image);
                   imgTick2.setImage(image);
                   imgTick.setVisible(true);
                   imgTick2.setVisible(true);
                   txtReEnterPassword.setStyle("-fx-border-color: null");
                   txtPassword.setStyle("-fx-border-color: null");
                   btnSignUp.setDisable(false);
               }else {
                   Image image = new Image("/resource/icons8-cross-mark-96.png");
                   imgTick.setImage(image);
                   imgTick2.setImage(image);
                   imgTick.setVisible(true);
                   imgTick2.setVisible(true);
                   txtReEnterPassword.setStyle("-fx-border-color: red");
                   txtPassword.setStyle("-fx-border-color: red");
                   btnSignUp.setDisable(true);
               }
           }
       });
    }



    public void btnSignUp_OnAction(ActionEvent actionEvent) throws SQLException {
        if (txtUserName.getText().trim().equals(null) || txtPassword.getText().trim().equals(null)){
            new Alert(Alert.AlertType.ERROR,"UserName and Password can't be empty", ButtonType.OK).show();
        }else {
            String userName = txtUserName.getText();
            String password = txtPassword.getText();
            PreparedStatement preparedStatement = DBConnectionWorking.getInstance().getConnection().prepareStatement("INSERT INTO users VALUES (0,?,?)");
            preparedStatement.setObject(1, userName);
            preparedStatement.setObject(2, password);
            preparedStatement.executeUpdate();
            new Alert(Alert.AlertType.INFORMATION,"Successfully created the account", ButtonType.OK).show();
        }
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
