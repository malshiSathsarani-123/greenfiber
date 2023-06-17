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
import lk.ijse.greenfiber.bo.custom.VehicleBO;
import lk.ijse.greenfiber.bo.custom.impl.VehicleBOImpl;
import lk.ijse.greenfiber.dto.VehicleDTO;
import lk.ijse.greenfiber.tm.VehicleTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableColumn<?, ?> colOwner_Name;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<VehicleTM> tblVehicle;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnBackReport;


    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadVehicleDetails();
        setCellValueFactory();
    }

    private void loadVehicleDetails() {
        ObservableList<VehicleTM> obList = FXCollections.observableArrayList();
        try {
            List<VehicleDTO>vehicleList = vehicleBO.getAllVehical();
            for (VehicleDTO vehicle : vehicleList){
                obList.add(new VehicleTM(
                        vehicle.getId(),
                        vehicle.getNo(),
                        vehicle.getType(),
                        vehicle.getColor(),
                        vehicle.getName()
                ));
            }
            tblVehicle.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("no"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colOwner_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/vehicle_form.fxml");
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
