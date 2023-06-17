package lk.ijse.greenfiber.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {

    @FXML
    private AnchorPane LoadContext;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment_details_form.fxml"));
        Parent load= loader.load();
        root.getChildren().clear();
        root.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),root);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    public void btnAttendenceOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/attendence_dtails_form.fxml"));
        Parent load= loader.load();
        root.getChildren().clear();
        root.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),root);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee_details_form.fxml"));
        Parent load= loader.load();
        EmployeeDetailsFormController controller = loader.getController();
        controller.setAction();
        root.getChildren().clear();
        root.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),root);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer_details_form.fxml"));
        Parent load= loader.load();
        CustomerDetailsFormController controller = loader.getController();
        controller.setAction();
        root.getChildren().clear();
        root.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),root);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    public void btnMaterialOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mattirial_details_form.fxml"));
        Parent load= loader.load();
        MaterialDetailsFormController controller = loader.getController();
        controller.setAction();
        root.getChildren().clear();
        root.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),root);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/product_details_form.fxml"));
        Parent load= loader.load();
        ProductDetailsFormController controller = loader.getController();
        controller.setAction();
        root.getChildren().clear();
        root.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),root);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier_details_form.fxml"));
        Parent load= loader.load();
        SupplierDetailsFormController controller = loader.getController();
        controller.setAction();
        root.getChildren().clear();
        root.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),root);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vehicle_details_form.fxml"));
        Parent load= loader.load();
        VehicleDetailsFormController controller = loader.getController();
        controller.setAction();
        root.getChildren().clear();
        root.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),root);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

}
