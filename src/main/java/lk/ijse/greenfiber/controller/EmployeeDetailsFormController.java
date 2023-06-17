package lk.ijse.greenfiber.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.EmployeeBO;
import lk.ijse.greenfiber.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.greenfiber.dto.EmployeeDTO;
import lk.ijse.greenfiber.tm.EmployeeTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDetailsFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableView<EmployeeTM> tblCustomer;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnBackReport;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadEmployeeDetails();
    }

    private void loadEmployeeDetails() {
        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
        try {
            List<EmployeeDTO> employeeList = employeeBO.getAllEmployee();

            for (EmployeeDTO employee : employeeList){
                obList.add(new EmployeeTM(
                        employee.getEmployee_Id(),
                        employee.getFirst_Name(),
                        employee.getLast_Name(),
                        employee.getAddress(),
                        employee.getNic(),
                        employee.getContact(),
                        employee.getRole(),
                        employee.getGender()
                ));
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("employee_Id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/employee_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void setAction() {
        btnBack.setText("");
        btnBack.setStyle("-fx-background-color: null");
    }

    public void btnBackReportOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/report_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void setAction1() {
        btnBackReport.setText("");
        btnBackReport.setStyle("-fx-background-color: null");
    }
}
