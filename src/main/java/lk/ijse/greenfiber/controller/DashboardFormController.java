package lk.ijse.greenfiber.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.DashboardBO;
import lk.ijse.greenfiber.bo.custom.impl.DashboardBOImpl;
import lk.ijse.greenfiber.model.*;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DashboardFormController implements Initializable {

    @FXML
    private AnchorPane LoadContext;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrder;

    @FXML
    private Label lblSupplir;

    @FXML
    private Label lblSupplies;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUser;

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblBalance;

    //DashboardBO dashboardBO = (DashboardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBORD);
        DashboardBO dashboardBO =new DashboardBOImpl();
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
        loadTime();
        loadCustomerCount();
        loadOrderCount();
        loadSupplierCount();
        loadSuppliesCount();
        loadPieChart();
        loadBalance();
        loadBarChart();
    }

    private void loadBarChart() {
      //   Set the x-axis label
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("X Axis Label");

        // Set the y-axis label
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y Axis Label");

        Double balance=0.0;
        try {
            balance = dashboardBO.getBalance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Create the bar chart data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("January", 4000));
        series.getData().add(new XYChart.Data<>("February", 5000));
        series.getData().add(new XYChart.Data<>("March", 3500));
        series.getData().add(new XYChart.Data<>("April",balance));
        series.getData().add(new XYChart.Data<>("May",0));
        series.getData().add(new XYChart.Data<>("June",0));
        series.getData().add(new XYChart.Data<>("July",0));
        series.getData().add(new XYChart.Data<>("August",0));
        series.getData().add(new XYChart.Data<>("September",0));
        series.getData().add(new XYChart.Data<>("October",0));
        series.getData().add(new XYChart.Data<>("November",0));
        series.getData().add(new XYChart.Data<>("December",0));

        // Add the data to the bar chart
        barChart.getData().add(series);

        // Set the x-axis and y-axis on the bar chart
////        barChart.setCategoryAxis(xAxis);
////        barChart.setNumberAxis(yAxis);
    }

    private void loadBalance() {
        try {
            Double balance = dashboardBO.getBalance();
            lblBalance.setText(String.valueOf(balance));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadPieChart() throws SQLException {
        Integer present = 0;
        Integer absent = 0;
        try {
            present = dashboardBO.getAttendenceCount();
            System.out.println(present);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            absent = dashboardBO.getAbsentCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("PRESENT", present),
                new PieChart.Data("ABSENT", absent)
        );
        pieChart.setData(data);
        pieChart.setTitle("EMPLOYEE ATTENDENCE");
    }

    private void loadSuppliesCount() {
        try {
            Integer count = dashboardBO.getSuppliesCount();
            lblSupplies.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSupplierCount() {
        try {
            Integer count = dashboardBO.getSupplierCount();
            lblSupplir.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadOrderCount() {
        try {
            Integer count = dashboardBO.getOrderCount();
            lblOrder.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerCount() {
        try {
            Integer count = dashboardBO.getCustomerCount();
            lblCustomer.setText(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTime() {
        Time time = Time.valueOf(LocalTime.now());
        lblTime.setText(String.valueOf(time));
    }

    private void loadDate() {lblDate.setText(String.valueOf(LocalDate.now()));}

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/customer_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnDashBoard(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
        window.centerOnScreen();
        window.show();
    }

    public void btnAteendenceOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/attendence01_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/place_order_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnPlaceSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/place_supplies_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnManufactiaringOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/manufactuaring_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnSuppliersrOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/supplier_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnEmployeesOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/employee_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnReportsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/report_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/vehicle_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnMattirialsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/matirial_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnDistributeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/distribute01_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/product_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void btnGoogleOnAction(ActionEvent actionEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI uri = new URI("https://www.google.com/");
            desktop.browse(uri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void btnMailOnAction(ActionEvent actionEvent) {
        try {
            String recipient = "malshisathsarani78@gmail.com";
            String mailtoUrl = "mailto:" + recipient;
            Desktop desktop = Desktop.getDesktop();
            URI mailtoUri = URI.create(mailtoUrl);
            desktop.mail(mailtoUri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void btnFacebookOnAction(ActionEvent actionEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI uri = new URI("https://web.facebook.com/");
            desktop.browse(uri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void btnWhatsappOnAction(ActionEvent actionEvent) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI uri = new URI("https://web.whatsapp.com/");
            desktop.browse(uri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.centerOnScreen();
    }

    public void setUserName(String text) {
        lblUser.setText(text);
    }

    public void btnReports01OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/payment_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        LoadContext.getChildren().clear();
        LoadContext.getChildren().add(load);
        TranslateTransition transition =new TranslateTransition(Duration.seconds(1),LoadContext);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }
}
