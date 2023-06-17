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
import lk.ijse.greenfiber.bo.custom.MaterialBO;
import lk.ijse.greenfiber.bo.custom.impl.MaterialBOImpl;
import lk.ijse.greenfiber.dto.MaterialDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MatirialFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private JFXButton btnBack;

    @FXML
    private Label lblId;

    MaterialBO materialBO = (MaterialBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MATERIAL);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String description = txtDescription.getText();
        Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());

        MaterialDTO materialDTO = new MaterialDTO(id, description, qtyOnHand, unitPrice);

        try {
            boolean isUpdate = materialBO.updateMaterial(materialDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "MATERIAL UPDATED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.CONFIRMATION, "MATERIAL NOT UPDATED!!!").show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDelete = materialBO.deleteMaterial(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "DELETED MATTIRIAL").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, " SORRY!! CAN NOT DELETE MATTIRIAL").show();
            clear();
        }
    }

    @FXML
    void txtIdClickedOnAction(MouseEvent event) {
        lblId.setText("");
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (txtId.getText().matches("^[M0-9]{4}$")) {
            String id = txtId.getText();
            String description = txtDescription.getText();
            Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());
            Double unitPrice = Double.valueOf(txtUnitPrice.getText());


            MaterialDTO materialDTO = new MaterialDTO(id, description, qtyOnHand, unitPrice);
            try {
                boolean isSave = materialBO.saveMaterial(materialDTO);
                if (isSave){
                    new Alert(Alert.AlertType.CONFIRMATION, "MATERIAL SAVED!!!").show();
                    clear();
                }else if(!isSave){
                    new Alert(Alert.AlertType.WARNING, "MATERIAL IS ALREADY SAVED!!!").show();
                    clear();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "OPPPS!!! MATERIAL NOT SAVED!!!").show();
                clear();
            }
        }else {
            lblId.setText("Id is wrong!!!");
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            MaterialDTO matirial = materialBO.searchMaterial(id);
            if (matirial != null){
                txtId.setText(matirial.getMattirial_Id());
                txtDescription.setText(matirial.getDescription());
                txtQtyOnHand.setText(String.valueOf(matirial.getQty_on_hand()));
                txtUnitPrice.setText(String.valueOf(matirial.getUnit_price()));
            }
            if (matirial == null){
                new Alert(Alert.AlertType.ERROR, "SORRY CAN NOT FOUND MATERIAL").show();
                clear();
            }else {
                new Alert(Alert.AlertType.ERROR).show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SOMETHING HAPPENED").show();
            clear();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {

        try {
            MaterialDTO  matirial = materialBO.getLastMattirial();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/place_supplies_form.fxml"));
            Parent load= loader.load();
            PlaceSuppliesFormController controller = loader.getController();
            controller.setMattirial(matirial);
            root.getChildren().clear();
            root.getChildren().add(load);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setButton() {
        btnBack.setText("BACK");
        btnBack.setStyle("-fx-background-color: #6055DB;");
    }


    public void txtIdOnAction(ActionEvent actionEvent) {
        if (txtId.getText().matches("^[M0-9]{4}$")) {
            actionCursor(txtDescription);
        }else {
            lblId.setText("MATERIAL ID NOT VALIDATED!!!");
        }
    }

    public void txtDescriptioOnAction(ActionEvent actionEvent) {
        actionCursor(txtUnitPrice);
    }

    public void txtUnitPriceOnAction(ActionEvent actionEvent) {
        actionCursor(txtQtyOnHand);
    }

    void actionCursor(TextField txt){
        txt.requestFocus();
    }
    void clear(){
        txtId.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mattirial_details_form.fxml"));
//        Parent load= loader.load();
//        MaterialDetailsFormController controller = loader.getController();
//        controller.setAction1();
//        root.getChildren().clear();
//        root.getChildren().add(load);
    }

}
