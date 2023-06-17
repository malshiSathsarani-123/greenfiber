package lk.ijse.greenfiber.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.greenfiber.tm.AttendenceTm01;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AttendenceDetailsFormController implements Initializable {

    @FXML
    private JFXButton btnOk;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeInTime;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colEmployeeOutTime;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colWorkingHours;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private TableView<AttendenceTm01> tblAtttendenceCart;


    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField txtDate;

    @FXML
    private AnchorPane root;

    @FXML
    private DatePicker dateFifer;


    AttendenceBO attendenceBO = (AttendenceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadEmployeeIds();
    }
    private void loadEmployeeIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> id = attendenceBO.genarateEmployeeIds();

            for (String ids : id){
                obList.add(ids);
            }
            cmbEmployeeId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/report_form.fxml"));
        Parent load= loader.load();
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnDateOkOnAction(ActionEvent event) {

        System.out.println(datepicker.getValue());
        String value = datepicker.getValue().toString();


        String date = value;
        ObservableList<AttendenceTm01> obList1 = FXCollections.observableArrayList();
        setCellValueFactory();

        try {
            List<AttendanceDTO> attendanceList = attendenceBO.getAllAttendanceDetails(date);
            for (AttendanceDTO attendance : attendanceList){
                obList1.add(new AttendenceTm01(attendance.getId(),
                        attendance.getName(),
                        attendance.getInTime(),
                        attendance.getOutTime(),
                        attendance.getHours(),
                        attendance.getType(),
                        attendance.getDate()));
            }
            tblAtttendenceCart.setItems(obList1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeInTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
        colEmployeeOutTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
        colWorkingHours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }


    @FXML
    void btnOkOnAction(ActionEvent event) {
        String id = cmbEmployeeId.getSelectionModel().getSelectedItem();
        ObservableList<AttendenceTm01> obList1 = FXCollections.observableArrayList();
        setCellValueFactory();

        try {
            List<AttendanceDTO> attendanceList = attendenceBO.getAllDetails(id);
            for (AttendanceDTO attendance : attendanceList){
                obList1.add(new AttendenceTm01(attendance.getId(),
                        attendance.getName(),
                        attendance.getInTime(),
                        attendance.getOutTime(),
                        attendance.getHours(),
                        attendance.getType(),
                        attendance.getDate()));
            }
            tblAtttendenceCart.setItems(obList1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {
        try {
            String name = attendenceBO.getEmployeeName(cmbEmployeeId.getSelectionModel().getSelectedItem());
            lblEmployeeName.setText(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
