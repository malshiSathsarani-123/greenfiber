package lk.ijse.greenfiber.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.DistributeBO;
import lk.ijse.greenfiber.bo.custom.impl.DistributeBOImpl;
import lk.ijse.greenfiber.dto.OrdersDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DistributeFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbDriverId;

    @FXML
    private ComboBox<String> cmbVehicleNo;

    @FXML
    private Label lblDeleveryId;

    @FXML
    private Label lblDriverName;

    @FXML
    private Label lblOrderId;

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblVehicleType;

    DistributeBO distributeBO = (DistributeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DISTRIBUTE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrderId();
        lordDeliveryId();
        lordVehicleId();
        lordDriversId();
    }

    private void lordDriversId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String>id = distributeBO.genarateDriversId();
            for (String ids : id){
                obList.add(ids);
            }
            cmbDriverId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void lordVehicleId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String>id = distributeBO.genarateVehicleId();

            for (String ids : id){
                obList.add(ids);
            }
            cmbVehicleNo.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void lordDeliveryId() {
        try {
            String deliveryId = distributeBO.getNextDeliveryId();
            lblDeleveryId.setText(deliveryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setOrderId() {
        try {
            String orderId = distributeBO.getOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbDriverIdOnAction(ActionEvent event) {
        try {
            String name = distributeBO.getDriverName(cmbDriverId.getSelectionModel().getSelectedItem());
            lblDriverName.setText(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbVehicleNoOnAction(ActionEvent event) {
        try {
            String type = distributeBO.getVehicleType(cmbVehicleNo.getSelectionModel().getSelectedItem());
            lblVehicleType.setText(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnConformOnAction(ActionEvent event) {
        String deliveryId = lblDeleveryId.getText();
        String driverId = cmbDriverId.getSelectionModel().getSelectedItem();
        String vehicleId = cmbVehicleNo.getSelectionModel().getSelectedItem();
        String orderId = lblOrderId.getText();

        try {
            boolean isUpdate = distributeBO.updateOrder(new OrdersDTO(deliveryId,driverId,vehicleId,orderId));
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"DELIVERY CONFORM!!!").show();
                clean();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"DELIVERY NOT CONFORM!!!").show();
        }
    }

    private void clean() {
        lblOrderId.setText("");
        lblDriverName.setText("");
        lblDeleveryId.setText("");
        lblVehicleType.setText("");
        cmbVehicleNo.setValue("");
        cmbDriverId.setValue("");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/place_order_form.fxml"));
        Parent load= loader.load();
        DistributeFormController controller = loader.getController();
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}