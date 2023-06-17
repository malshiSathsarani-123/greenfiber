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
import lk.ijse.greenfiber.bo.custom.ProductBO;
import lk.ijse.greenfiber.bo.custom.impl.ProductBOImpl;
import lk.ijse.greenfiber.dto.ProductDTO;
import lk.ijse.greenfiber.tm.ProductTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProductDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ProductTM> tblProduct;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnBackReport;

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductDetails();
        setCellValueFactory();
    }

    private void loadProductDetails() {
        ObservableList<ProductTM> obList = FXCollections.observableArrayList();

        try {
            List<ProductDTO> productList = productBO.getAllProduct();

            for (ProductDTO product : productList){
                obList.add(new ProductTM(
                        product.getProductCode(),
                        product.getDescription(),
                        product.getQtyOnHand(),
                        product.getCost(),
                        product.getUnitPrice()
                ));
            }
            tblProduct.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/product_form.fxml");
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
