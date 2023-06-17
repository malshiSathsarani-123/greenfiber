package lk.ijse.greenfiber.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.EmployeeBO;
import lk.ijse.greenfiber.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.greenfiber.dto.EmployeeDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private ToggleGroup group;

    @FXML
    private JFXRadioButton radioBtnFemail;

    @FXML
    private JFXRadioButton radioBtnMail;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblId;

    @FXML
    private Label lblNic;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRole();
    }

    private void setRole() {
        List<String> role = new ArrayList<>();
        ObservableList<String> obList = FXCollections.observableArrayList();

        role.add("Drivers");
        role.add("Factory");

        for (String roles : role){
            obList.add(roles);
        }
        cmbRole.setItems(obList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        cmbRole.setValue("");
        txtContact.setText("");
    }
    void clear(){
        txtId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtContact.setText("");
        cmbRole.setValue("");
    }
    private boolean conformId() {
        if (txtId.getText().matches("^[E0-9]{4}$")) {
            return true;
        }else {
            lblId.setText("Incorrect Id!!");
        }
        return false;
    }

    private boolean conformNic() {
        if (txtNic.getText().matches("^([0-9x|X|v|V]{9})$")){
            return true;
        }if (txtNic.getText().matches("^([0-9]{12})$")){
        return true;
        }else {
            lblNic.setText("Incorrect Nic");
        }
        return false;
    }

    private boolean conformContact() {
        if (txtContact.getText().matches("^[0-9]{10}$")){
            return true;
        }else {
            lblContact.setText("Incorrect Contact");
        }
        return false;
    }

    @FXML
    void txtIdClickedOnAction(MouseEvent event) {
        lblId.setText("");
    }

    @FXML
    void txtContactClickedOnAction(MouseEvent event) {
        lblContact.setText("");
    }

    @FXML
    void txtNicClickedOnAction(MouseEvent event) {
        lblNic.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDelete = employeeBO.deleteEmployee(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"EMPLOYEE DELETED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"EMPLOYEE NOT DELETED!!!").show();
            clear();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (conformId()) {
            if (conformContact()){
                if (conformNic()){
                    String id = txtId.getText();
                    String fName = txtFirstName.getText();
                    String lName = txtLastName.getText();
                    String address = txtAddress.getText();
                    String nic = txtNic.getText();
                    int contact = Integer.parseInt(txtContact.getText());
                    String role = cmbRole.getSelectionModel().getSelectedItem();
                    String gender = radioBtnGenderOnAction(event);

                    try {
                        boolean isSave = employeeBO.saveEmployee(new EmployeeDTO(id, fName, lName, address, nic, contact,role, gender));
                        if (isSave) {
                            new Alert(Alert.AlertType.CONFIRMATION, "EMPLOYEE SAVES!!!").show();
                            clear();
                        } else if (!isSave) {
                            new Alert(Alert.AlertType.WARNING, "EMPLOYEE ALREADY SAVES!!!").show();
                            clear();
                        }
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.WARNING, "SOMETHING HAPPENED!!!").show();
                    }
                }
            }else {
                conformNic();
            }
        }else {
            conformContact();
            conformNic();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        if(txtId.getText() != null ){
            String id = txtId.getText();

            try {
                EmployeeDTO employee = employeeBO.searchEmployee(id);
                if (employee != null){
                    txtId.setText(employee.getEmployee_Id());
                    txtFirstName.setText(employee.getFirst_Name());
                    txtLastName.setText(employee.getLast_Name());
                    txtAddress.setText(employee.getAddress());
                    txtNic.setText(employee.getNic());
                    txtContact.setText(String.valueOf(employee.getContact()));
                    cmbRole.setValue(employee.getRole());
                    String gender = employee.getGender();

                    if (gender.equals("Male")){
                        radioBtnMail.setSelected(true);
                    }else {
                        radioBtnFemail.setSelected(true);
                    }
                }
                if(employee == null && txtNic.getText() == null){
                    new Alert(Alert.AlertType.ERROR,"EMPLOYEE NOT FOUND!!!").show();
                    clear();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"SOMETHINGS HAPPENED!!! ").show();
            }
        }
       if(txtNic.getText() != null){
            String nic = txtNic.getText();

            try {
                EmployeeDTO employee1 = employeeBO.searchNic(nic);
                if (employee1 != null){
                    txtId.setText(employee1.getEmployee_Id());
                    txtFirstName.setText(employee1.getFirst_Name());
                    txtLastName.setText(employee1.getLast_Name());
                    txtAddress.setText(employee1.getAddress());
                    txtNic.setText(employee1.getNic());
                    txtContact.setText(String.valueOf(employee1.getContact()));
                    cmbRole.setValue(employee1.getRole());
                    String gender = employee1.getGender();

                    if (gender.equals("Male")){
                        radioBtnMail.setSelected(true);
                    }else {
                        radioBtnFemail.setSelected(true);
                    }
                }
                if(employee1 == null){
                    new Alert(Alert.AlertType.ERROR,"EMPLOYEE NOT FOUND!!!").show();
                    clear();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"SOMETHINGS HAPPENED!!!").show();
            }
        }
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String role = cmbRole.getSelectionModel().getSelectedItem();
        String gender=radioBtnGenderOnAction(event);

        try {
            boolean isUpdate = employeeBO.updateEmployee(new EmployeeDTO(id, fName, lName, address, nic, contact,role, gender));
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"EMPLOYEE UPDATED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"SOMETHING HAPPENED!!!").show();
            e.printStackTrace();
        }
    }

    @FXML
    String radioBtnGenderOnAction(ActionEvent event) {
        if(radioBtnMail.isSelected()){
            return "Male";
        } else if(radioBtnFemail.isSelected()){
            return "female";
        }
        return null;
    }

    @FXML
    void btnDetailsOnAction(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee_details_form.fxml"));
//        Parent load= loader.load();
//        EmployeeDetailsFormController controller = loader.getController();
//        controller.setAction1();
//        root.getChildren().clear();
//        root.getChildren().add(load);
    }

    public void txtFirstNameOnAction(ActionEvent actionEvent) {actionCursor(txtLastName);}

    public void txtAddressOnAction(ActionEvent actionEvent) {
        actionCursor(txtNic);
    }

    public void txtLastNameOnAction(ActionEvent actionEvent) {
        actionCursor(txtAddress);
    }

    public void txtNicOnAction(ActionEvent actionEvent) {
        actionCursor(txtContact);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        if (txtId.getText().matches("^[E0-9]{4}$")) {
            actionCursor(txtFirstName);
        }else {
            new Alert(Alert.AlertType.ERROR,"EMPLOYEE ID NOT VALIDATED!!!").show();
            clear();
        }
    }

    void actionCursor(TextField txt){
        txt.requestFocus();
    }

}
