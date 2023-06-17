package lk.ijse.greenfiber.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.VehicleBO;
import lk.ijse.greenfiber.bo.custom.impl.VehicleBOImpl;
import lk.ijse.greenfiber.dto.VehicleDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VehicleFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbColor;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtVehicleNo;

    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadVehicleColours();
        loadVehicleTypes();
    }

    private void loadVehicleTypes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String [] type = {"car","lorry","truck","bike","van","cab"};
        for (String types : type){
            obList.add(types);
        }
        cmbType.setItems(obList);
    }

    private void loadVehicleColours() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        String [] color = {"black","white","red","green","pink","blue"};
        for (String colors : color){
            obList.add(colors);
        }
        cmbColor.setItems(obList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtVehicleNo.setText("");
        txtFirstName.setText("");
        cmbType.setValue("");
        cmbColor.setValue("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDelete = vehicleBO.deleteVehicle(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"VEHICLE IS DELETED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"VEHICLE IS NOT DELETED!!!").show();
            clear();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (txtId.getText().matches("^[V0-9]{4}$")){
            String id = txtId.getText();
            String no = txtVehicleNo.getText();
            String type = cmbType.getSelectionModel().getSelectedItem();
            String color = cmbColor.getSelectionModel().getSelectedItem();
            String name = txtFirstName.getText();

            VehicleDTO vehicle = new VehicleDTO(id, no, type, color, name);

            try {
                boolean isSave = vehicleBO.saveVehicle(vehicle);
                if (isSave){
                    new Alert(Alert.AlertType.CONFIRMATION,"VEHICLE IS SAVED!!!").show();
                    clear();
                }
                if (!isSave){
                    new Alert(Alert.AlertType.ERROR,"THIS VEHICLE IS ALREADY SAVED!!!").show();
                    clear();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.WARNING,"VEHICLE IS NOT SAVED!!!").show();
                clear();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"VEHICLE ID NOT VALIDATED!!!").show();
            clear();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            VehicleDTO vehicle = vehicleBO.searchVehicle(id);
            if (vehicle != null){
                txtId.setText(vehicle.getId());
                txtVehicleNo.setText(vehicle.getNo());
                txtFirstName.setText(vehicle.getName());
                cmbType.setValue(vehicle.getType());
                cmbColor.setValue(vehicle.getColor());
            }
            if ((vehicle == null)){
                new Alert(Alert.AlertType.ERROR,"VEHICLE NOT FOUND!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,"SOMETHING HAPPENED!!!").show();
            clear();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String no = txtVehicleNo.getText();
        String type = cmbType.getSelectionModel().getSelectedItem();
        String color = cmbColor.getSelectionModel().getSelectedItem();
        String name = txtFirstName.getText();

        VehicleDTO vehicle = new VehicleDTO(id, no, type, color, name);

        try {
            boolean isUpdate = vehicleBO.updateVehicle(vehicle);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"VEHICLE DETAILS UPDATED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"VEHICLE DETAILS NOT UPDATED!!!").show();
            clear();
        }
    }

    void clear(){
        txtId.setText("");
        txtVehicleNo.setText("");
        txtFirstName.setText("");
        cmbType.setValue("");
        cmbColor.setValue("");
    }
    @FXML
    void cmbColorOnAction(ActionEvent event) {}

    @FXML
    void cmbTypeOnAction(ActionEvent event) {}

    @FXML
    void txtFirstNameOnAction(ActionEvent event) {cmbColor.requestFocus();}

    @FXML
    void txtIdOnAction(ActionEvent event) {
        if (txtId.getText().matches("^[V0-9]{4}$")){
            actionCursor(txtVehicleNo);
        }else {
            new Alert(Alert.AlertType.ERROR,"VEHICLE ID NOT VALIDATED!!!").show();
            clear();
        }
    }

    @FXML
    void txtVehicleNoOnAction(ActionEvent event) {actionCursor(txtFirstName);}

    void actionCursor(TextField txt){
        txt.requestFocus();
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vehicleDetail_form.fxml"));
//        Parent load= loader.load();
////        VehicleDetailsFormController controller = loader.getController();
////        controller.setAction1();
//        root.getChildren().clear();
//        root.getChildren().add(load);
    }

}
