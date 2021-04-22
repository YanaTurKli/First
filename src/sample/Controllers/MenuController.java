package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.AddListener;
import sample.Database;
import sample.Visit;

public class MenuController implements AddListener {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bInsert;

    @FXML
    private TableView<Visit> table;

    @FXML
    private Button bExt;

    @FXML
    private TableColumn<Visit, String> pacColumn;

    @FXML
    private TableColumn<Visit, String> docColumn;

    @FXML
    private TableColumn<Visit, String> diagColumn;

    @FXML
    private TableColumn<Visit, Date> dataColumn;

    @FXML
    private TableColumn<Visit, String> pervColumn;

    @FXML
    private TableColumn<Visit, String> vtorColumn;

    @FXML
    private TableColumn<Visit, String> infoColumn;

    @FXML
    private Button bFind;

    @FXML
    private TextField teFind;



    private ArrayList<Visit> currentVisit = new ArrayList<>();

    @FXML
    void initialize() {
        setItemsToTable();
        pacColumn.setCellValueFactory(new PropertyValueFactory<Visit, String>("pac"));
        docColumn.setCellValueFactory(new PropertyValueFactory<Visit, String>("doc"));
        diagColumn.setCellValueFactory(new PropertyValueFactory<Visit, String>("diag"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<Visit, Date>("data"));
        pervColumn.setCellValueFactory(new PropertyValueFactory<Visit, String>("perv"));
        vtorColumn.setCellValueFactory(new PropertyValueFactory<Visit, String>("vtor"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<Visit, String>("info"));
        teFind.textProperty().addListener(observable -> checkFindButton());


    }
    private void setItemsToTable() {
        ArrayList<Visit> currentVisit = new ArrayList<>(Database.getVisit().
                stream().collect(Collectors.toList())); //Получить лист посещений
        table.setItems(javafx.collections.FXCollections.observableList(currentVisit));
        table.setDisable(false);

    }
    @FXML
    void btnAddPressed() {
        System.out.println("Добавить");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/sample/Fxmls/add.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddController ctrl = fxmlLoader.getController();
        ctrl.addListener(this);

        Parent root = fxmlLoader.getRoot();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Добавить посещение");

        stage.showAndWait();

    }

    @FXML
    void btnFindPressed() {
        ArrayList<Visit> currentVisit = Database.getVisit().stream()
                .filter(visit -> visit.getPac().equals(teFind.getText()))
                .collect(Collectors.toCollection(ArrayList::new));
        //Получаем список посещений с определенной фамилией пациента
        table.getItems().clear();
       table.setItems(javafx.collections.FXCollections.observableList(currentVisit));
       table.setDisable(false);
    }

    private void checkFindButton() {
        bFind.setDisable(teFind.getText().length() <= 0);
    }
    @FXML
    void btnExtPressed() {
           table.getItems().clear();
            setItemsToTable();
    }


    @Override
    public void visitAdded() {
        teFind.clear();
        setItemsToTable();
    }
}