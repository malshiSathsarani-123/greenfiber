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
import lk.ijse.greenfiber.bo.custom.PlaceSuppliesBO;
import lk.ijse.greenfiber.bo.custom.impl.PlaceSuppliesBOImpl;
import lk.ijse.greenfiber.db.DBConnection;
//import lk.ijse.greenfiber.dto.CartSuppliesDto;
//import lk.ijse.greenfiber.dto.Matirial;
//import lk.ijse.greenfiber.model.MattirialModel;
//import lk.ijse.greenfiber.model.PlaceSuppliesModel;
//import lk.ijse.greenfiber.model.SupplierModel;
//import lk.ijse.greenfiber.model.SuppliesModel;
import lk.ijse.greenfiber.dto.*;
import lk.ijse.greenfiber.tm.PlaceSuppliesTM;
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

public class PlaceSuppliesFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbMatirialCode;

    @FXML
    private JFXComboBox<String> cmbSupplierrId;

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
    private Label lblDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblSuppliesDate;

    @FXML
    private Label lblSuppliesTime;

    @FXML
    private Label lblSuppliesId;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PlaceSuppliesTM> tblOrderCart1;

    @FXML
    private TextField txtQty;

    private ObservableList<PlaceSuppliesTM> obList = FXCollections.observableArrayList();

    PlaceSuppliesBO placeSuppliesBO = (PlaceSuppliesBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACESUPPLIES);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblSuppliesDate.setText(String.valueOf(LocalDate.now()));
        lblSuppliesTime.setText(String.valueOf(LocalTime.now()));
        setNextSuppliesId();
        loadSupplierId();
        loadMattirialCode();
        setCellValueFactory();
    }

    private void setNextSuppliesId() {
        try {
            String nextId = placeSuppliesBO.generateNextSuppliesId();
            lblSuppliesId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadMattirialCode() {

        try {
            ObservableList<String>obList=FXCollections.observableArrayList();
            List<String>codes= placeSuppliesBO.generateMaterialCode();

            for (String code : codes){
                obList.add(code);
            }
            cmbMatirialCode.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSupplierId() {

        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String>ids = placeSuppliesBO.generateSupplierId();

            for (String id : ids){
                obList.add(id);
            }
            cmbSupplierrId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbMatirialCode.getSelectionModel().getSelectedItem();
        String description = lblDescription.getText();
        Integer qty = Integer.valueOf(txtQty.getText());
        Double price = Double.valueOf(lblUnitPrice.getText());
        Double total = qty*price;
        Button btn = new Button("Remove");
        btn.setCursor(Cursor.HAND);

        if (!obList.isEmpty()){
            for (int i = 0; i < tblOrderCart1.getItems().size(); i++) {
               if (colItemCode.getCellData(i).equals(code)){
                   qty+=(Integer) colQty.getCellData(i);
                   total=qty*price;

                   obList.get(i).setQty(qty);
                   obList.get(i).setTotal(total);

                   tblOrderCart1.refresh();
                   netTotal();
                   return;
               }
            }
        }
        setRemoveBtnOnAction(btn);
        PlaceSuppliesTM tm = new PlaceSuppliesTM(code, description, qty, price, total, btn);

        obList.add(tm);
        tblOrderCart1.setItems(obList);
        txtQty.setText("");
        netTotal();
    }

    private void netTotal() {
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

    private void setRemoveBtnOnAction(Button btn) {

        btn.setOnAction((e)->{
            ButtonType yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "ARE YOU SURE REMOVE THIS MATTIRIAL",yes,no).showAndWait();

            if (buttonType.orElse(no) == yes){
                obList.remove(tblOrderCart1.getSelectionModel().getSelectedItem());
                tblOrderCart1.refresh();
                netTotal();
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
    void btnNewSupplierOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier_form.fxml"));
        Parent load= loader.load();
        SupplierFormController controller = loader.getController();
        controller.setButton();
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnPlaceSuppliesOnAction(ActionEvent event) {
        String suppliesId = lblSuppliesId.getText();
        String supplierId = cmbSupplierrId.getSelectionModel().getSelectedItem();
        String amount = lblNetTotal.getText();
        List<SuppliesDetailDTO>suppliesDetailDTOList=new ArrayList<>();

        SuppliesDTO suppliesDTO = new SuppliesDTO(suppliesId,amount,LocalDate.now(), Time.valueOf(LocalTime.now()),supplierId);
        for (int i = 0; i < tblOrderCart1.getItems().size(); i++) {
            PlaceSuppliesTM tm = obList.get(i);
            SuppliesDetailDTO suppliesDetailDTO = new SuppliesDetailDTO(suppliesId,tm.getCode(), tm.getQty(), tm.getUnitPrice(), tm.getTotal());
            suppliesDetailDTOList.add(suppliesDetailDTO);
        }

        try {
            boolean isSave = placeSuppliesBO.saveSupplies(suppliesDTO,suppliesDetailDTOList);
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION,"PLACED SUPPLIES").show();
                getBill();
                newPlaceSupplies();

            }
        } catch (SQLException | IOException | JRException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"NOT PLACED SUPPLIES").show();
            e.printStackTrace();
        }
    }
    private void getBill()throws JRException, SQLException{
        String id = lblSuppliesId.getText();
        JasperDesign load = null;
        load = JRXmlLoader.load(new File("G:\\1st semester\\Final project 01\\finalProject\\src\\main\\resources\\report\\suppliess.jrxml"));
        JRDesignQuery newQuery = new JRDesignQuery();
        String sql = "SELECT m.Description as name, m.Unit_Price as Unit_Price, sd.Mattirial_Qty , sd.Total as Total FROM mattirial m INNER JOIN supplies_detail sd ON m.Mattirial_Code = sd.Mattirial_Code WHERE sd.Supplies_id =  '"+id+"'";
        newQuery.setText(sql);
        load.setQuery(newQuery);
        JasperReport js = JasperCompileManager.compileReport(load);
        HashMap<String,Object> hm=new HashMap<>();
        hm.put("suppliies","Name");
        JasperPrint jp = JasperFillManager.fillReport(js, null, DBConnection.getInstance().getConnection());
        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();
    }


    private void newPlaceSupplies() throws IOException {
        URL resource = getClass().getResource("/view/place_supplies_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void cmbMatirialOnAction(ActionEvent event) {
        String code = cmbMatirialCode.getSelectionModel().getSelectedItem();

        try {
            MaterialDTO material = placeSuppliesBO.searchMaterial(code);
            if (material != null){
                lblDescription.setText(material.getDescription());
                lblUnitPrice.setText(String.valueOf(material.getUnit_price()));
                lblQtyOnHand.setText(String.valueOf(material.getQty_on_hand()));

                txtQty.requestFocus();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbSupplierrIdOnAction(ActionEvent event) {
        String id = cmbSupplierrId.getSelectionModel().getSelectedItem();

        try {
            String name = placeSuppliesBO.getSupplierName(id);
            lblSupplierName.setText(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    public void set(String id, String name) {
        cmbSupplierrId.setValue(id);
        lblSupplierName.setText(name);
    }

    public void btnNewMattirialOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/matirial_form.fxml"));
        Parent load= loader.load();
        MatirialFormController controller = loader.getController();
        controller.setButton();
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void setMattirial(MaterialDTO material) {
        cmbMatirialCode.setValue(material.getMattirial_Id());
        lblDescription.setText(material.getDescription());
        lblUnitPrice.setText(String.valueOf(material.getUnit_price()));
        lblQtyOnHand.setText(String.valueOf(material.getQty_on_hand()));
    }
}
