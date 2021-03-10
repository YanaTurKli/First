package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bInsert;

    @FXML
    private Button bUpdate;

    @FXML
    private Button bDelet;

    @FXML
    private ComboBox<?> cmb;

    @FXML
    private TableView<?> table;

    @FXML
    private Button bExt;

    @FXML
    void initialize() {

    }
}
