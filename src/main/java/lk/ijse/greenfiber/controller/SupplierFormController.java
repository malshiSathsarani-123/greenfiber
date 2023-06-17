package lk.ijse.greenfiber.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.SupplierBO;
import lk.ijse.greenfiber.bo.custom.impl.SupplierBOImpl;
import lk.ijse.greenfiber.dto.SupplierDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtCompanyName;

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
    private JFXButton btnBack;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblId;

    @FXML
    private Label lblNic;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id= txtId.getText();

        try {
            boolean isDelete = supplierBO.deleteSupplier(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "SUPPLIER DELETED!!!").show();
                clear();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "CON NOT FIND SUPPLIER FOR DELETED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SUPPLIER NOT DELETED!!!").show();
        }
    }

    private boolean conformId() {
        if (txtId.getText().matches("^[S0-9]{4}$")) {
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
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        if (conformId()) {
            if (conformContact()){
                if (conformNic()){
                    String id = txtId.getText();
                    String fName = txtFirstName.getText();
                    String lName = txtLastName.getText();
                    String comName = txtCompanyName.getText();
                    String nic = txtNic.getText();
                    int contact = Integer.parseInt(txtContact.getText());

                    SupplierDTO supplier = new SupplierDTO(id, fName, lName, comName, nic, contact);
                    try {
                        boolean isSave = supplierBO.saveSupplier(supplier);
                        if (isSave) {
                            new Alert(Alert.AlertType.CONFIRMATION, "SUPPLIER SAVED!!!").show();
                            clear();
                        } else if (!isSave) {
                            new Alert(Alert.AlertType.WARNING, "SUPPLIER IS ALREADY SAVED!!!").show();
                            clear();
                        }
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "SUPPLIER NOT SAVED!!!").show();
                        clear();
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
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtCompanyName.setText("");
        txtNic.setText("");
        txtContact.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        String comName = txtCompanyName.getText();
        String nic = txtNic.getText();
        int contact = Integer.parseInt(txtContact.getText());

        SupplierDTO supplier = new SupplierDTO(id, fName, lName, comName, nic, contact);

        try {
            boolean isUpdate = supplierBO.updateSupplier(supplier);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "SUPPLIER UPDATED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SUPPLIER NOT UPDATED!!!").show();
            clear();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        if(txtId.getText() != null){
            String id = txtId.getText();

            try {
                SupplierDTO supplier = supplierBO.searchSupplier(id);
                if(supplier != null) {
                    txtId.setText(supplier.getSupplier_Id());
                    txtFirstName.setText(supplier.getFirst_Name());
                    txtLastName.setText(supplier.getLast_Name());
                    txtCompanyName.setText(supplier.getCompany_Name());
                    txtNic.setText(supplier.getNic());
                    txtContact.setText(String.valueOf(supplier.getContact()));
                }
                if(supplier == null){
                    new Alert(Alert.AlertType.ERROR,"CUSTOMER NOT FOUND!!!").show();
                    clear();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "SUPPLIER NOT FOUND!!").show();
                clear();
            }
        }
        if(txtNic.getText() != null){
            String nic = txtNic.getText();

            try {
                SupplierDTO supplier = supplierBO.searchNic(nic);
                if(supplier != null) {
                    txtId.setText(supplier.getSupplier_Id());
                    txtFirstName.setText(supplier.getFirst_Name());
                    txtLastName.setText(supplier.getLast_Name());
                    txtCompanyName.setText(supplier.getCompany_Name());
                    txtNic.setText(supplier.getNic());
                    txtContact.setText(String.valueOf(supplier.getContact()));
                }
                if(supplier == null){
                    new Alert(Alert.AlertType.ERROR,"SUPPLIER NOT FOUND!!!").show();
                    clear();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "SUPPLIER NOT FOUND!!").show();
                clear();
            }
        }
    }

    private void clear() {
        txtId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtCompanyName.setText("");
        txtNic.setText("");
        txtContact.setText("");
    }

    public void setButton() {
        btnBack.setText("BACK");
        btnBack.setStyle("-fx-background-color: #6055DB;");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        String id = null;
        String name = null;
        try {
           id = supplierBO.getLastSupplirId();
           if (id != null){
               name = supplierBO.getName(id);

           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/place_supplies_form.fxml"));
        Parent load= loader.load();
        PlaceSuppliesFormController controller = loader.getController();
        controller.set(id,name);
        root.getChildren().clear();
        root.getChildren().add(load);

    }

    public void txtComNameOnAction(ActionEvent actionEvent) {
        actionCursor(txtNic);
    }

    public void txtLastOnAction(ActionEvent actionEvent) {
        actionCursor(txtCompanyName);
    }

    public void txtFirstNameOnAction(ActionEvent actionEvent) {actionCursor(txtLastName);}

    @FXML
    void txtIdOnAction(ActionEvent event) {
        if (txtId.getText().matches("^[S0-9]{4}$")) {
            actionCursor(txtFirstName);
        }else {
            lblId.setText("id is wrong");
        }
    }

    @FXML
    void txtNicOnAction(ActionEvent event) {actionCursor(txtContact);}

    void actionCursor(TextField txt){txt.requestFocus();}

    public void btnDetailsOnAction(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier_details_form.fxml"));
//        Parent load= loader.load();
//        SupplierDetailsFormController controller = loader.getController();
//        controller.setAction1();
//        root.getChildren().clear();
//        root.getChildren().add(load);
    }
}
