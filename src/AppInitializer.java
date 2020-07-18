import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.DBConnection;

import java.io.IOException;
import java.sql.SQLException;

public class AppInitializer extends Application {

    public static void main(String[] args) throws SQLException {
        launch(args);
        DBConnection.getInstance().getConnection().close();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("view/DashBoard.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
    }
}
