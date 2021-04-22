package sample.Controllers;

        import java.net.URL;
        import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.ResourceBundle;
        import java.util.stream.Collectors;

        import javafx.collections.FXCollections;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.util.Pair;
        import sample.AddListener;
        import sample.Database;
        import sample.Visit;

        import javax.xml.crypto.Data;

public class AddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bAddVisit;

    @FXML
    private DatePicker btnData;

    @FXML
    private CheckBox cbPerv;

    @FXML
    private CheckBox cbVtor;

    @FXML
    private ComboBox cmbStud;

    @FXML
    private ComboBox cmbDoc;

    @FXML
    private TextField teInfo;

    @FXML
    private ComboBox cmbDiag;
    ArrayList<AddListener> listeners = new ArrayList<>();
    private ArrayList<Pair<Integer, String>> dict_docs = new ArrayList<>();
    private ArrayList<Pair<Integer, String>> dict_pac = new ArrayList<>();
    private ArrayList<Pair<Integer, String>> dict_diag = new ArrayList<>();
    Pair<Integer, String> currentDiag;
    Pair<Integer, String> currentDoc;
    Pair<Integer, String> currentPac;
    @FXML
    void initialize() {
        bAddVisit.setDisable(true);

        teInfo.textProperty().addListener(observable -> checkAddButton());

        dict_diag = Database.getdictDiag();
        List<String> groupDiag = new ArrayList<>();
        dict_diag.forEach(pair -> groupDiag.add(pair.getValue()));
        cmbDiag.setItems(FXCollections.observableArrayList(groupDiag));

        dict_docs = Database.getdictDoc();
        List<String> groupDocs = new ArrayList<>();
        dict_docs.forEach(pair -> groupDocs.add(pair.getValue()));
        cmbDoc.setItems(FXCollections.observableArrayList(groupDocs));

        dict_pac = Database.getdictPac();
        List<String> groupPac = new ArrayList<>();
        dict_pac.forEach(pair -> groupPac.add(pair.getValue()));
        cmbStud.setItems(FXCollections.observableArrayList(groupPac));

    }

    @FXML
    void setAddButton() {
        Visit visit = new Visit(0,currentPac.getValue().toString(),currentDoc.getValue().toString(),
                currentDiag.getValue().toString(),
                 btnData.getValue(),
                cbPerv.isSelected(),cbVtor.isSelected(),teInfo.getText(),
                currentPac.getKey(), currentDoc.getKey(), currentDiag.getKey());
        if(!Database.addVisit(visit)) {
            bAddVisit.getScene().getWindow().hide();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Посещение не добавлено.");
            alert.showAndWait();
        } else {
            bAddVisit.getScene().getWindow().hide();
            for (AddListener listener:
                    listeners) {
                listener.visitAdded();
            }
        }
    }

    @FXML
    void cmbDiagPresed() {
        currentDiag = dict_diag.stream()
                .filter(dict_diag -> dict_diag.getValue() == cmbDiag.getValue().toString())
                .collect(Collectors.toList()).get(0); //Получить диагноз из комбобокса, найти соответствующую запись
        // в словаре и записать ее в currentDiag
    }

    @FXML
    void cmbStudPresed() {
        currentPac = dict_pac.stream()
                .filter(dict_pac -> dict_pac.getValue() == cmbStud.getValue().toString())
                .collect(Collectors.toList()).get(0);
        //Получить фамилию студента из комбобокса, найти соответствующую запись в словаре групп
        // и записать ее в currentPac
    }

    @FXML
    void cmbDocPresed() {
        currentDoc = dict_docs.stream()
                .filter(dict_docs -> dict_docs.getValue() == cmbDoc.getValue().toString())
                .collect(Collectors.toList()).get(0);
        //Получить фамилию доктора из комбобокса, найти соответствующую запись в словаре групп
        // и записать ее в currentDoc
    }
    public void addListener(AddListener addListener) {
        listeners.add(addListener);
    }

    private void checkAddButton() {
            if ((currentDiag.getValue().length() > 0) &&(currentDoc.getValue().length() > 0)&&
                    (currentPac.getValue().length() > 0) && (teInfo.getText().length() > 0 ) &&
                    ( btnData.getValue()!=null)){
            bAddVisit.setDisable(false);
        }
        else {
            bAddVisit.setDisable(true);
        }
    }

}
