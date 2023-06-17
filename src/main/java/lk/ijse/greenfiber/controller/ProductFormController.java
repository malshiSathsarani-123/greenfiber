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
import lk.ijse.greenfiber.bo.custom.ProductBO;
import lk.ijse.greenfiber.bo.custom.impl.ProductBOImpl;
import lk.ijse.greenfiber.dto.ProductionDetailDTO;
import lk.ijse.greenfiber.dto.ProductDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtRate;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private JFXButton backButton;

    @FXML
    private Label lblId;

    private ArrayList<ProductionDetailDTO> manufacturingDto1;

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtCost.setText("");
        txtQtyOnHand.setText("");
        txtRate.setText("");
    }
    void  clear(){
        txtId.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtCost.setText("");
        txtQtyOnHand.setText("");
        txtRate.setText("");
    }
    @FXML
    void btnCostOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/manufactiaring_cost_form.fxml"));
        Parent load= loader.load();
        ManufacturingCostFormController controller = loader.getController();
        controller.setLabel(txtId.getText());
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDelete = productBO.deleteProduct(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "PRODUCT DELETE!!!").show();
                clear();
            }else {
                new Alert(Alert.AlertType.ERROR, "SORRY CAN NOT DELETE!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,"SOMETHING HAPPENED!!!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (txtId.getText().matches("^[P0-9]{4}$")) {
            String code = txtId.getText();
            String description = txtDescription.getText();
            Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());
            Double cost = Double.valueOf(txtCost.getText());
            Double unitPrice = Double.valueOf(txtUnitPrice.getText());

            ProductDTO product = new ProductDTO(code, description, qtyOnHand, cost, unitPrice);

            try {
                boolean isSave = productBO.saveProductionDetail(product,code,manufacturingDto1);
                if (isSave){
                    new Alert(Alert.AlertType.CONFIRMATION,"PRODUCT SAVED!!!").show();
                    clear();
                }else if(!isSave){
                    new Alert(Alert.AlertType.WARNING, "PRODUCT IS ALREADY SAVED!!!").show();
                    clear();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "SOMETHING HAPPENED!!!").show();
                clear();
            }
        }else {
            lblId.setText("Id is wrong!!!");
            clear();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtId.getText();
        String description = txtDescription.getText();
        Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());
        Double cost = Double.valueOf(txtCost.getText());
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());

        ProductDTO product = new ProductDTO(code, description, qtyOnHand, cost, unitPrice);

        try {
            boolean isUpdate = productBO.updateProduct(product);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"PRODUCT DETAILS UPDATED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,"SOMETHING HAPPENED!!!").show();
        }
    }

    @FXML
    void txtCostOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/manufactiaring_cost_form.fxml"));
        Parent load= loader.load();
        ManufacturingCostFormController controller = loader.getController();
        controller.setLabel(txtId.getText());
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void txtRateOnAction(ActionEvent event) {
        Double unitPrice = Double.parseDouble(txtCost.getText()) * Double.parseDouble(txtRate.getText());
        txtUnitPrice.setText(String.valueOf(unitPrice));
    }

    public void setCost(String text) {
        txtCost.setText(text);
    }

    public void setArraylist(String pCode, List<ProductionDetailDTO> manufacturingDto) {
        manufacturingDto1= (ArrayList<ProductionDetailDTO>) manufacturingDto;
        txtId.setText(pCode);
    }

    public void txtUnitPriceOnAction(ActionEvent actionEvent) {
        Double unitPrice = Double.parseDouble(txtCost.getText()) * Double.parseDouble(txtRate.getText());
        txtUnitPrice.setText(String.valueOf(unitPrice));
    }

    public void lblUnitPriceOnAction(ActionEvent actionEvent) {
        Double unitPrice = Double.parseDouble(txtCost.getText()) * Double.parseDouble(txtRate.getText());
        txtUnitPrice.setText(String.valueOf(unitPrice));
    }

    public void addBackButton() {
       backButton.setText("BACK");
       backButton.setStyle("-fx-background-color: #6055DB;");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/manufactuaring_form.fxml"));
//        Parent load= loader.load();
//        ManufacturingFormController controller = loader.getController();
//        controller.setProductCode(txtId.getText());
//        root.getChildren().clear();
//        root.getChildren().add(load);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            ProductDTO product = productBO.searchProduct(id);
            if(product != null){
                txtId.setText(product.getProductCode());
                txtDescription.setText(product.getDescription());
                txtQtyOnHand.setText(String.valueOf(product.getQtyOnHand()));
                txtCost.setText(String.valueOf(product.getCost()));
                txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
            }else {
                new Alert(Alert.AlertType.ERROR, "SORRY CAN NOT FIND PRODUCT!!!").show();
                clear();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "SOMETHING HAPPENED!!!").show();
            clear();
        }
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        if (txtId.getText().matches("^[P0-9]{4}$")) {
            actionCursor(txtCost);
        }else {
            lblId.setText("Id is wrong!!!");
            clear();
        }
    }
    @FXML
    void txtIdClickedOnAction(MouseEvent event) {
        lblId.setText("");
    }
    public void txtQtyOnAction(ActionEvent actionEvent) {
        actionCursor(txtRate);
    }

    public void txtDescriprtionOnAction(ActionEvent actionEvent) {
        actionCursor(txtQtyOnHand);
    }

    void actionCursor(TextField txt){
        txt.requestFocus();
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/product_details_form.fxml"));
//        Parent load= loader.load();
//        ProductDetailsFormController controller = loader.getController();
//        controller.setAction1();
//        root.getChildren().clear();
//        root.getChildren().add(load);
    }
}
