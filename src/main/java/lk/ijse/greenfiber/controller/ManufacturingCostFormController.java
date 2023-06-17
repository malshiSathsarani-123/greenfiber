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
import lk.ijse.greenfiber.bo.custom.ManufacturingCostBO;
import lk.ijse.greenfiber.bo.custom.impl.ManufacturingCostBOImpl;
import lk.ijse.greenfiber.dto.ProductionDetailDTO;
import lk.ijse.greenfiber.tm.ManufacturingCostTm;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManufacturingCostFormController implements Initializable {

    ManufacturingCostBO manufacturingCostBO = (ManufacturingCostBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANUFACTUERINGCOST);
    @FXML
    private ComboBox<String> cmbMattrialCode;

    @FXML
    private TableColumn<?, ?> colMattirialCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblMattirialName;

    @FXML
    private Label lblProdctCode;

    @FXML
    private TableView<ManufacturingCostTm> tblManufacturingCart;

    @FXML
    private TextField txtQty;

    @FXML
    private AnchorPane root;


    private ObservableList<ManufacturingCostTm> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMatirialCode();
        setCellValueFactory();
    }

    private void loadMatirialCode() {
        try {
            List<String>code = manufacturingCostBO.generateCode();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String codes : code) {
                obList.add(codes);
            }
            cmbMattrialCode.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnOkOnAction(ActionEvent event) throws SQLException {
        String mCode = cmbMattrialCode.getSelectionModel().getSelectedItem();
        Integer qty = Integer.valueOf(txtQty.getText());
        Double unitPrice = manufacturingCostBO.getUnitPrice(mCode);
        Double total = unitPrice * qty;

        if (!obList.isEmpty()){
            for (int i = 0; i < tblManufacturingCart.getItems().size(); i++) {
                if (colMattirialCode.getCellData(i).equals(mCode)) {
                    qty += (int)colQty.getCellData(i);
                    total=qty*unitPrice;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    tblManufacturingCart.refresh();
                    getNetTotal();
                    return;
                }
            }
        }

        ManufacturingCostTm tm = new ManufacturingCostTm(mCode, qty, unitPrice, total);

        obList.add(tm);
        tblManufacturingCart.setItems(obList);

        getNetTotal();
    }

    private void getNetTotal() {

        double netTotal=0.0;
        for (int i = 0; i < tblManufacturingCart.getItems().size(); i++) {
            netTotal += (double) colTotal.getCellData(i);
         //   netTotal += total;
        }
        lblCost.setText(String.valueOf(netTotal));
    }

    void setCellValueFactory() {
        colMattirialCode.setCellValueFactory(new PropertyValueFactory<>("mattirialCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @FXML
    void cmbMattrialCodeOnAction(ActionEvent event) {
        String code = cmbMattrialCode.getSelectionModel().getSelectedItem();

        try {
            String name = manufacturingCostBO.getName(code);
            lblMattirialName.setText(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLabel(String txt){
        lblProdctCode.setText(txt);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException, SQLException {

        String pCode = lblProdctCode.getText();
        List<ProductionDetailDTO> manufacturingDto =new ArrayList<>();

        ManufacturingCostTm tm = null;
        for (int i = 0; i < tblManufacturingCart.getItems().size(); i++) {
             tm = obList.get(i);

            ProductionDetailDTO cartManufactuaringDto = new ProductionDetailDTO(tm.getMattirialCode(), tm.getQty(), tm.getTotal());
            manufacturingDto.add(cartManufactuaringDto);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/product_form.fxml"));
        Parent load= loader.load();
        ProductFormController controller = loader.getController();
        controller.setCost(lblCost.getText());
        controller.addBackButton();
        root.getChildren().clear();
        root.getChildren().add(load);
        controller.setArraylist(pCode,manufacturingDto);

    }


}
