package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button bConn;

    @FXML
    private TextField tfLogin;

    @FXML
    void initialize() {
        bConn.setOnAction(event->{
            String login = tfLogin.getText().trim();
            String pass = tfPassword.getText().trim();
             if (!login.equals("") && !pass.equals(""))
                 conn(login, pass);
             else
                 System.out.println("not login and pass");
        });

    }

    private void conn(String login, String pass) {

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
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("DB app");
        stage.showAndWait();


    }
}
