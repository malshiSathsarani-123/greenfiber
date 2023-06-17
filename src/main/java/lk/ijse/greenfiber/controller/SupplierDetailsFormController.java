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
import lk.ijse.greenfiber.bo.custom.SupplierBO;
import lk.ijse.greenfiber.bo.custom.impl.SupplierBOImpl;
import lk.ijse.greenfiber.dto.SupplierDTO;
import lk.ijse.greenfiber.tm.SupplierTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colComName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTM> tblCustomer;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnBackReport;


    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSuppliers();
        setCellValueFactory();
    }

    private void loadSuppliers() {
        ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDTO> supplierList = supplierBO.getAllSuplier();

            for (SupplierDTO supplier : supplierList){
                obList.add(new SupplierTM(
                        supplier.getSupplier_Id(),
                        supplier.getFirst_Name(),
                        supplier.getLast_Name(),
                        supplier.getCompany_Name(),
                        supplier.getNic(),
                        supplier.getContact()
                ));
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Supplier_Id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        colComName.setCellValueFactory(new PropertyValueFactory<>("Company_Name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/supplier_form.fxml");
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
