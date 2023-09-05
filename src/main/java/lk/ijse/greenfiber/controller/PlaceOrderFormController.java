package lk.ijse.greenfiber.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.PlaceOrderBO;
import lk.ijse.greenfiber.bo.custom.impl.PlaceOrderBOImpl;
import lk.ijse.greenfiber.db.DBConnection;
import lk.ijse.greenfiber.dto.OrderDetailDTO;
import lk.ijse.greenfiber.dto.OrdersDTO;
import lk.ijse.greenfiber.dto.ProductDTO;
import lk.ijse.greenfiber.tm.PlaceOrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class PlaceOrderFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbProductCode;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PlaceOrderTM> tblOrderCart1;

    @FXML
    private TextField txtQty;

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadNextOrderId();
        loadCustomerId();
        loadProductCode();
        setCellValueFactory();
    }

    private void loadProductCode() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String>code = placeOrderBO.genarateProductCode();

            for (String codes : code){
                obList.add(codes);
            }
            cmbProductCode.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String>id = placeOrderBO.genarateCustomerId();

            for (String ids : id){
                obList.add(ids);
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadNextOrderId() {
        try {
            String nextOrderId = placeOrderBO.getNextOrderId();
            lblOrderId.setText(nextOrderId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDateAndTime() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblTime.setText(String.valueOf(Time.valueOf(LocalTime.now())));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbProductCode.getSelectionModel().getSelectedItem();
        String description = lblDescription.getText();
        Integer qty = Integer.valueOf(txtQty.getText());
        Double price = Double.valueOf(lblUnitPrice.getText());
        Double total = qty*price;
        Button btn = new Button("Remove");
        btn.setCursor(Cursor.HAND);

        btnRemoveOnAction(btn);

        if (!obList.isEmpty()){
            for (int i = 0; i < tblOrderCart1.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (Integer) colQty.getCellData(i);
                    total = qty * price;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    tblOrderCart1.refresh();
                    getNetTotal();
                    return;
                }
            }
        }

        PlaceOrderTM tm = new PlaceOrderTM(code, description, qty, price, total, btn);
        obList.add(tm);
        tblOrderCart1.setItems(obList);
        getNetTotal();
        txtQty.setText("");
    }

    private void btnRemoveOnAction(Button btn) {

        btn.setOnAction((e)->{
            ButtonType yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION,"ARE YOU SURE REMOVE THIS MATTIRIAL",yes,no).showAndWait();

            if (buttonType.orElse(no)==yes){
                obList.remove(tblOrderCart1.getSelectionModel().getSelectedItem());
                tblOrderCart1.refresh();
                getNetTotal();
            }
        });
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("DASH BORD");
        stage.centerOnScreen();
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer_form.fxml"));
        Parent load= loader.load();
        CustomerFormController controller = loader.getController();
        controller.setButton();
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

        String orderId = lblOrderId.getText();
        String amount = lblNetTotal.getText();
        String date = String.valueOf(LocalDate.now());
        String time = String.valueOf(Time.valueOf(LocalTime.now()));
        String customerId = cmbCustomerId.getSelectionModel().getSelectedItem();

        List<OrderDetailDTO>orderDtoList = new ArrayList<>();

        OrdersDTO ordersDTO = new OrdersDTO(orderId,amount,date,time,customerId);
        for (int i = 0; i < tblOrderCart1.getItems().size(); i++) {
            PlaceOrderTM tm=obList.get(i);
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderId,tm.getCode(), tm.getQty(), tm.getUnitPrice(), tm.getTotal());
            orderDtoList.add(orderDetailDTO);
        }
        try {
            boolean isSave = placeOrderBO.saveOrder(ordersDTO,orderDtoList);
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"ORDER PLACED!!!").show();
                getBill();
                newPlaceOrders();
            }
        } catch (SQLException | IOException | JRException e) {
            e.printStackTrace();
        }
    }

    private void getBill()throws JRException, SQLException{
        String id = lblOrderId.getText();
        JasperDesign load = null;
        load = JRXmlLoader.load(new File("G:\\2nd semester\\LayeredArchitecture\\LayerdArchitectureCourswork\\src\\main\\resources\\report\\orderDetails.jrxml"));
        JRDesignQuery newQuery = new JRDesignQuery();
        String sql = "SELECT p.Description as Name, p.Unit_Price as Unit_Price, od.Product_Qty , od.Total as Total FROM product p INNER JOIN order_detail od ON p.Product_Code = od.Product_Code WHERE od.Order_Id = '"+id+"'";
        newQuery.setText(sql);
        load.setQuery(newQuery);
        JasperReport js = JasperCompileManager.compileReport(load);
        HashMap<String,Object> hm=new HashMap<>();
        hm.put("order","Name");
        JasperPrint jp = JasperFillManager.fillReport(js, null, DBConnection.getInstance().getConnection());
        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();
    }

    private void newPlaceOrders() throws IOException {
        URL resource = getClass().getResource("/view/place_order_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerId.getSelectionModel().getSelectedItem();

        try {
            String name = placeOrderBO.getCustomerName(id);
            lblCustomerName.setText(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void cmbProductCodeOnAction(ActionEvent event) {
        String code = cmbProductCode.getSelectionModel().getSelectedItem();

        try {
            ProductDTO product = placeOrderBO.searchProduct(code);
            if (product != null){
                lblDescription.setText(product.getDescription());
                lblUnitPrice.setText(String.valueOf(product.getUnitPrice()));
                lblQtyOnHand.setText(String.valueOf(product.getQtyOnHand()));

                txtQty.requestFocus();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getNetTotal(){
        Double netTotal = 0.0;
        for (int i = 0; i < tblOrderCart1.getItems().size(); i++) {
            netTotal +=(Double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void set(String id, String name) {
        cmbCustomerId.setValue(id);
        lblCustomerName.setText(name);
    }

    public void btnDeleveryOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/distribute_form.fxml"));
        Parent load= loader.load();
        DistributeFormController controller = loader.getController();
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnPayOnAction(ActionEvent actionEvent) {}
}