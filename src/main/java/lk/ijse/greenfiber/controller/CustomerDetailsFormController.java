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
import lk.ijse.greenfiber.bo.custom.CustomerBO;
import lk.ijse.greenfiber.bo.custom.impl.CustomerBOImpl;
import lk.ijse.greenfiber.dto.CustomerDTO;
import lk.ijse.greenfiber.tm.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnBackReport;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomers();
        setCellValueFactory();
    }

    private void loadCustomers() {
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDTO>customerList = customerBO.getAllCustomer();

            for (CustomerDTO customer : customerList){
                obList.add(new CustomerTM(
                        customer.getCustomer_Id(),
                        customer.getFirst_Name(),
                        customer.getLast_Name(),
                        customer.getAddress(),
                        customer.getNic(),
                        customer.getContact(),
                        customer.getGender()
                ));
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/customer_form.fxml");
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
