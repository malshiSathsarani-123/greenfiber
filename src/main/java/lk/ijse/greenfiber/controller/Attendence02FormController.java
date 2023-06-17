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
import lk.ijse.greenfiber.tm.AttendenceTm01;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Attendence02FormController implements Initializable {

    @FXML
    private ComboBox<String> cmbEmployeeId;

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
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colWorkingHours;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<AttendenceTm01> tblAtttendenceCart;

    private ObservableList<AttendenceTm01> obList1 = FXCollections.observableArrayList();

    AttendenceBO attendenceBO = (AttendenceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadEmployeeIds();
        loadEmployeeDetails();
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

    private void loadEmployeeDetails() {
        setCellValueFactory();

        try {
            List<AttendanceDTO> attendanceList = attendenceBO.getAll(LocalDate.now());
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
    void btnFinishOnAction(ActionEvent event) {

        List<AttendanceDTO>dtoList = new ArrayList<>();

        for (int i = 0; i < tblAtttendenceCart.getItems().size(); i++) {
            AttendenceTm01 tm = obList1.get(i);
            AttendanceDTO attendanceDTO = new AttendanceDTO(tm.getId(),tm.getOutTime(),tm.getHours());

            dtoList.add(attendanceDTO);
        }
        try {
            boolean isSave = attendenceBO.update(dtoList);
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"updated").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnOkOnAction(ActionEvent event) {
        String id = cmbEmployeeId.getSelectionModel().getSelectedItem();
        Time outTime = Time.valueOf(LocalTime.now());

            for (int i = 0; i < tblAtttendenceCart.getItems().size(); i++) {
                if (colEmployeeId.getCellData(i).equals(id)) {
                    obList1.get(i).setOutTime(outTime);
                    tblAtttendenceCart.refresh();

                    Time inTime = (Time) colEmployeeInTime.getCellData(i);
                    Time time1 = inTime;
                    Time time2 = outTime;
                    long diffInMillis = time2.getTime() - time1.getTime();

                    int hours = (int) (diffInMillis/1000/60/60);
                    obList1.get(i).setHours(hours);
                    return;
                }
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

    @FXML
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/attendence01_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
