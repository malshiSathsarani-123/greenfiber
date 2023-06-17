package lk.ijse.greenfiber.controller;

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
import lk.ijse.greenfiber.bo.custom.PaymentBO;
import lk.ijse.greenfiber.bo.custom.impl.PaymentBOImpl;
import lk.ijse.greenfiber.dto.PaymentDTO;
import lk.ijse.greenfiber.tm.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPayId;

    @FXML
    private TableColumn<?, ?> colSuppliesId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<PaymentTM> tblPaymentCart;

    @FXML
    private AnchorPane root;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/report_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPaymentDetails();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colPayId.setCellValueFactory(new PropertyValueFactory<>("payId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colSuppliesId.setCellValueFactory(new PropertyValueFactory<>("suppliesId"));
    }

    private void loadPaymentDetails() {
        ObservableList<PaymentTM> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDTO>paymentList = paymentBO.getAllPayment();

            for (PaymentDTO payment : paymentList){
                obList.add(new PaymentTM(
                        payment.getPayId(),
                        payment.getDate(),
                        payment.getAmount(),
                        payment.getBalance(),
                        payment.getType(),
                        payment.getOrderId(),
                        payment.getSuppliesId()
                ));
                tblPaymentCart.setItems(obList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
