package lk.ijse.greenfiber.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.AttendenceBO;
import lk.ijse.greenfiber.bo.custom.impl.AttendenceBOImpl;
import lk.ijse.greenfiber.dto.AttendanceDTO;
import lk.ijse.greenfiber.tm.AttendenceTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Attendence01FormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    public TableColumn colDate;

    @FXML
    private ComboBox<String> cmbEmployeAttent;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private TableView<AttendenceTm> tblAtttendenceCart;

    private ObservableList<AttendenceTm> obList = FXCollections.observableArrayList();

    AttendenceBO attendenceBO = (AttendenceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAttend();
        loadEmployeeIds();
    }

    private void loadEmployeeIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String>id = attendenceBO.genarateEmployeeIds();

            for (String ids : id){
                obList.add(ids);
            }
            cmbEmployeeId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAttend() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        String [] attend = {"Present","Absent"};

        for (String attends : attend){
            obList.add(attends);
        }
        cmbEmployeAttent.setItems(obList);
    }

    @FXML
    void cmbEmployeeAttendOnAction(ActionEvent event) {}


    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {
        try {
            String name = attendenceBO.getEmployeeName(cmbEmployeeId.getSelectionModel().getSelectedItem());
            lblEmployeeName.setText(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnOkOnAction(ActionEvent event) {
        String id = cmbEmployeeId.getSelectionModel().getSelectedItem();
        String name = lblEmployeeName.getText();
        Time inTime = Time.valueOf(LocalTime.now());
        String type = cmbEmployeAttent.getSelectionModel().getSelectedItem();
        String date = String.valueOf(LocalDate.now());

        setCellValueFactory();
        try {
            boolean isAttend = attendenceBO.searchAttend(id,date);
            if (isAttend){
                new Alert(Alert.AlertType.ERROR,"SORRY THIS EMPLOYEE ALREADY ATTENDED!!!").show();
            }
            if (!isAttend){
                AttendenceTm tm = new AttendenceTm(id, name, inTime,type, date);
                obList.add(tm);
                tblAtttendenceCart.setItems(obList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        List<AttendanceDTO>dtoList = new ArrayList<>();

        for (int i = 0; i < tblAtttendenceCart.getItems().size(); i++) {
            AttendenceTm tm = obList.get(i);
            AttendanceDTO attendanceDTO = new AttendanceDTO(tm.getId(),tm.getName(),tm.getInTime(),tm.getType(),tm.getDate());

            dtoList.add(attendanceDTO);
        }
        try {
            boolean isSave = attendenceBO.save(dtoList);
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"OK").show();
                getNewAttendenceForm();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getNewAttendenceForm() throws IOException {
        URL resource = getClass().getResource("/view/attendence01_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnoUTOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/attendence02_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
