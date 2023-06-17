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
import lk.ijse.greenfiber.bo.custom.ManufacturingBO;
import lk.ijse.greenfiber.bo.custom.impl.ManufacturingBOImpl;
import lk.ijse.greenfiber.dto.*;
import lk.ijse.greenfiber.tm.ManufacturingTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManufacturingFormController implements Initializable {

    @FXML
    private Button btnOk;

    @FXML
    private ComboBox<String> cmbProductCode;

    @FXML
    private TableColumn<?, ?> colMattirialCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotalQty;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblDescription;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ManufacturingTM> tblManufacturingCart;

    @FXML
    private TextField txtQty;

    private ObservableList<ManufacturingTM> obList = FXCollections.observableArrayList();

    ManufacturingBO manufacturingBO = (ManufacturingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANUFACTUERING);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductCode();
        setCellValueFactory();
    }

    private void loadProductCode() {
        try {
            List<String>codes= manufacturingBO.getProductCodes();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String code : codes){
                obList.add(code);
            }
            cmbProductCode.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void btnBackOnAction(ActionEvent event) {

        String pCode = cmbProductCode.getSelectionModel().getSelectedItem();
        String pQty = txtQty.getText();
        List<ProductionDetailDTO>cartMaterialDtoList=new ArrayList<>();

        for (int i = 0; i < tblManufacturingCart.getItems().size(); i++) {

            ManufacturingTM tm = obList.get(i);

            ProductionDetailDTO productionDetailDTO = new ProductionDetailDTO(tm.getMattirialCode(), tm.getTotalQty());

            cartMaterialDtoList.add(productionDetailDTO);
        }
        try {
            boolean isUpdate = manufacturingBO.updateManufacturing(pCode,pQty,cartMaterialDtoList);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "UPDATED!!!").show();
                newManufactuaringForm();
            }if (!isUpdate){
                new Alert(Alert.AlertType.ERROR, "MATERIAL IS OVER!!! ").show();
                newManufactuaringForm();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.WARNING, "SOMETHING HAPPENED!!! ").show();
        }
    }

    private void newManufactuaringForm() throws IOException {
        URL resource = getClass().getResource("/view/manufactuaring_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnOkOAction(ActionEvent event) {

        String code = cmbProductCode.getSelectionModel().getSelectedItem();
        try {
            List<ProductionDetailDTO> productionDetailDTOS = manufacturingBO.getMaterialDetails(code);

            for (ProductionDetailDTO p : productionDetailDTOS) {

                Integer total = p.getQty() * Integer.valueOf(txtQty.getText());
                ManufacturingTM tm = new ManufacturingTM(p.getMaterialCode(), p.getQty(),total);

                obList.add(tm);
            }
            tblManufacturingCart.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void setCellValueFactory() {
        colMattirialCode.setCellValueFactory(new PropertyValueFactory<>("MattirialCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colTotalQty.setCellValueFactory(new PropertyValueFactory<>("TotalQty"));
    }

    @FXML
    void cmbProductCodeOnAction(ActionEvent event) {
        String code = cmbProductCode.getSelectionModel().getSelectedItem();

        try {
            ProductDTO product =  manufacturingBO.getDescriptionAndCost(code);
            if (product != null){
                lblDescription.setText(product.getDescription());
                lblCost.setText(String.valueOf(product.getCost()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void btnNewOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/product_form.fxml"));
        Parent load= loader.load();
        ProductFormController controller = loader.getController();
        controller.addBackButton();
        root.getChildren().clear();
        root.getChildren().add(load);
    }
    public void setProductCode(String text) {
        cmbProductCode.setValue(text);
        try {
            ProductDTO product =  manufacturingBO.getDescriptionAndCost(text);
            if (product != null){
                lblDescription.setText(product.getDescription());
                lblCost.setText(String.valueOf(product.getCost()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
