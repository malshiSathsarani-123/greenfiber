package lk.ijse.greenfiber.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.PaymentBO;
import lk.ijse.greenfiber.bo.custom.impl.PaymentBOImpl;
import lk.ijse.greenfiber.dto.PaymentDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbOrderId;

    @FXML
    private JFXComboBox<String> cmbSupliesId;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderAmount;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Label lblSuppliesAmount;

    @FXML
    private Label lblTime;

    @FXML
    private TextField txtPayAmountOrder;

    @FXML
    private TextField txtPayAmountSupllies;

    @FXML
    private AnchorPane root;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderId();
        loadSuppliesId();
        loadTime();
        getNextId1();
        loadDate();
    }

    private void loadDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void getNextId1() {
        try {
            String id = paymentBO.getNextId();
            lblPaymentId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSuppliesId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> id = paymentBO.genarateSuppliesId();
            for (String ids : id){
                obList.add(ids);
            }
            cmbSupliesId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTime() {
        Time time = Time.valueOf(LocalTime.now());
        lblTime.setText(String.valueOf(time));
    }

    private void loadOrderId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> id = paymentBO.genarateOrderId();
            for (String ids : id){
                obList.add(ids);
            }
            cmbOrderId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void cmbOrderIdOnAction(ActionEvent event) {
        generateAmount();
        generateCustId();
    }

    private void generateCustId() {
        try {
            String id = paymentBO.getCustomerIdOrder(cmbOrderId.getSelectionModel().getSelectedItem());
            lblCustomerId.setText(String.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateAmount() {
        try {
            Double amount = paymentBO.getAmountOrder(cmbOrderId.getSelectionModel().getSelectedItem());
            lblOrderAmount.setText(String.valueOf(amount));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnOrderPayOnAction(ActionEvent event) {
        String id = lblPaymentId.getText();
        Double amount = Double.valueOf(txtPayAmountOrder.getText());
        String type = "Order Payment";
        String orderId = cmbOrderId.getSelectionModel().getSelectedItem();
        String date = String.valueOf(LocalDate.now());
        Double balance =0.0;
        try {
            balance = paymentBO.getBalanceOrder();
            balance=balance+amount;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            boolean isSave = paymentBO.allSaveOrder(new PaymentDTO(id, date, Time.valueOf(LocalTime.now()), amount,balance, orderId,null,type));
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"PAYMENT SUCCESSFUL!!!").show();
                getNewPaymentForm();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void getNewPaymentForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment_form.fxml"));
        Parent load= loader.load();
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    public void cmbSupliesIdOnAction(ActionEvent actionEvent) {
        generateAmountSupplies();
        generateSupplierId();
    }

    private void generateSupplierId() {
        try {
            String id = paymentBO.getSupplierId(cmbSupliesId.getSelectionModel().getSelectedItem());
            lblSupplierId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateAmountSupplies() {
        try {
            Double amount = paymentBO.getAmountSupplies(cmbSupliesId.getSelectionModel().getSelectedItem());
            lblSuppliesAmount.setText(String.valueOf(amount));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtPayAmountOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnSuppliesPayOnAction(ActionEvent event) {
        String id = lblPaymentId.getText();
        if (lblPaymentId.getText().matches("^V001[0-9]{4}$")){
            System.out.println("ok");
        }else {
            System.out.println("not ok");
        }

        Double amount = Double.valueOf(txtPayAmountSupllies.getText());
        String type = "Supplies Payment";
        String suppliesId = cmbSupliesId.getSelectionModel().getSelectedItem();
        String date = String.valueOf(LocalDate.now());
        Double balance =0.0;
        try {
            balance = paymentBO.getBalanceSupplies();
            balance=balance-amount;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            boolean isSave = paymentBO.allSaveSupplies(new PaymentDTO(id, date, Time.valueOf(LocalTime.now()), amount,balance,null,suppliesId,type));
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"PAYMENT SUCCESSFUL!!!").show();
                getNewPaymentForm();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validationaid(String id) {
        if (lblPaymentId.getText().matches("^P001[0-9]{4}$")){
            System.out.println("ok");
        }else {
            System.out.println("not ok");
        }
        return false;
    }

    @FXML
    void txtPayAmountSuplliesOnAction(ActionEvent event) {

    }

}
