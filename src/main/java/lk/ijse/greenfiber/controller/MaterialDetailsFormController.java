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
import lk.ijse.greenfiber.bo.custom.MaterialBO;
import lk.ijse.greenfiber.bo.custom.impl.MaterialBOImpl;
import lk.ijse.greenfiber.dto.MaterialDTO;
import lk.ijse.greenfiber.tm.MaterialTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MaterialDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<MaterialTM> tblMattirial;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnBackReport;

    MaterialBO materialBO = (MaterialBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MATERIAL);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMetirialDetails();
        setCellValueFactory();
    }

    private void loadMetirialDetails() {
        ObservableList<MaterialTM> obList = FXCollections.observableArrayList();

        try {
            List<MaterialDTO>matirialList = materialBO.getAllMaterial();

            for (MaterialDTO matirial : matirialList){
                obList.add(new MaterialTM(
                        matirial.getMattirial_Id(),
                        matirial.getDescription(),
                        matirial.getQty_on_hand(),
                        matirial.getUnit_price()
                ));
            }
            tblMattirial.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("mattirial_Id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty_on_hand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/matirial_form.fxml");
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
