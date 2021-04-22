package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button bConn;

    @FXML
    void initialize() {
        bConn.setOnAction(event->{
            String login = tfLogin.getText().trim();
            String pass = tfPassword.getText().trim();
             if (!login.equals("") && !pass.equals(""))
                 conn(login, pass);
             else
                 System.out.println("no login or pass");
        });

    }

    private void conn(String login, String pass) {

        Database.connect(login, pass);
        if (Database.isConnected()) {
            bConn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Fxmls/menu.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Меню");
            stage.setResizable(false);
            stage.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Не удалось подключиться к Базе данных.");
            alert.showAndWait();
        }
    }
}
