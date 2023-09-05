package lk.ijse.greenfiber.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.CustomerBO;
import lk.ijse.greenfiber.bo.custom.impl.CustomerBOImpl;
import lk.ijse.greenfiber.dto.CustomerDTO;
import lk.ijse.greenfiber.entity.Customer;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerFormController {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXRadioButton radioBtnFemail;

    @FXML
    private JFXRadioButton radioBtnMail;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblNic;

    @FXML
    private Label lblContact;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtContact.setText("");
    }

    void clear(){
        txtId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtContact.setText("");
    }

    @FXML
    void txtIdClickedOnAction(MouseEvent event) {
        lblPassword.setText("");
    }

    @FXML
    void txtContactClickedOnAction(MouseEvent event) {
        lblContact.setText("");
    }

    @FXML
    void txtNicClickedOnAction(MouseEvent event) {
        lblNic.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDelete = customerBO.deleteCustomer(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION).show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (conformId()) {
           if (conformContact()){
               if (conformNic()){
                   String id = txtId.getText();
                   String fName = txtFirstName.getText();
                   String lName = txtLastName.getText();
                   String address = txtAddress.getText();
                   String nic = txtNic.getText();
                   int contact = Integer.parseInt(txtContact.getText());
                   String gender=radiaBtnGenderOnAction(event);

                   try {
                       boolean isSave = customerBO.saveCustomer(new CustomerDTO(id, fName, lName, address, nic, contact, gender));
                       if (isSave){
                           new Alert(Alert.AlertType.CONFIRMATION, "CUSTOMER SAVES!!!").show();
                           clear();
                       }else if (!isSave){
                           new Alert(Alert.AlertType.WARNING, "CUSTOMER ALREADY SAVES!!!").show();
                           clear();
                       }
                   } catch (SQLException e) {
                       new Alert(Alert.AlertType.ERROR, "SOMETHING HAPPENED!!!").show();
                       clear();
                   }
               }
           }else {
               conformNic();
           }
        }else {
            conformContact();
            conformNic();
        }
    }

    private boolean conformId() {
        if (txtId.getText().matches("^[C0-9]{4}$")) {
            return true;
        }else {
            lblPassword.setText("Incorrect Id!!");
        }
            return false;
    }

    private boolean conformNic() {
        if (txtNic.getText().matches("^([0-9x|X|v|V]{9})$")){
            return true;
        }if (txtNic.getText().matches("^([0-9]{12})$")){
            return true;
        }else {
            lblNic.setText("Incorrect Nic");
        }
        return false;
    }

    private boolean conformContact() {
        if (txtContact.getText().matches("^[0-9]{10}$")){
            return true;
        }else {
            lblContact.setText("Incorrect Contact");
        }
        return false;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        String address = txtAddress.getText();
        String nic = txtNic.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String gender=radiaBtnGenderOnAction(event);

        try {
            boolean isUpdate = customerBO.updateCustomer(new CustomerDTO(id, fName, lName, address, nic, contact, gender));
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"CUSTOMER UPDATED!!!").show();
                clear();
            }
        } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"SOMETHING HAPPENED!!!").show();
                clear();
        }
    }

    @FXML
    String radiaBtnGenderOnAction(ActionEvent event) {
        if(radioBtnMail.isSelected()){
            return "male";
        } else if(radioBtnFemail.isSelected()){
            return "female";
        }
        return null;
    }

    public void txtFnameOnAction(ActionEvent actionEvent) {actionCursor(txtLastName);}

    public void txtAddressOnAction(ActionEvent actionEvent) {
        actionCursor(txtNic);
    }

    public void txtLnameOnAction(ActionEvent actionEvent) {
        actionCursor(txtAddress);
    }

    public void txtNicOnAction(ActionEvent actionEvent) {
        actionCursor(txtContact);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {actionCursor(txtFirstName);}

    void actionCursor(TextField txt){
        txt.requestFocus();
    }

    public void setButton() {
        btnBack.setText("BACK");
        btnBack.setStyle("-fx-background-color: #6055DB;");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        String id = null;
        String name = null;
        try {
            id = customerBO.getLastCustomer();
            if (id != null){
                name = customerBO.getName(id);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/place_order_form.fxml"));
        Parent load= loader.load();
        PlaceOrderFormController controller = loader.getController();
        controller.set(id,name);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        if(txtId.getText() != null){
            String id = txtId.getText();

            try {
                CustomerDTO customer = customerBO.searchCustomer(id);
                if (customer != null){
                    System.out.println(id);
                    System.out.println(customer);
                    txtId.setText(customer.getCustomer_Id());
                    txtFirstName.setText(customer.getFirst_Name());
                    txtLastName.setText(customer.getLast_Name());
                    txtAddress.setText(customer.getAddress());
                    txtNic.setText(customer.getNic());
                    txtContact.setText(String.valueOf(customer.getContact()));
                    String gender = customer.getGender();

                    if (gender.equals("male")){
                        radioBtnMail.setSelected(true);
                    }else {
                        radioBtnFemail.setSelected(true);
                    }
                }else {
                    System.out.println("null");
                }
            } catch (SQLException e ) {
                new Alert(Alert.AlertType.ERROR,"SOMETHINGS HAPPENED!!! ").show();
            }catch (NullPointerException e){
                new Alert(Alert.AlertType.ERROR,"CUSTOMER NOT FOUND!!!").show();
            }
        }
        if(txtNic.getText() != null){
            String nic = txtNic.getText();

            try {
                CustomerDTO customer = customerBO.searchNicCustomer(nic);
                if (customer != null){
                    txtId.setText(customer.getCustomer_Id());
                    txtFirstName.setText(customer.getFirst_Name());
                    txtLastName.setText(customer.getLast_Name());
                    txtAddress.setText(customer.getAddress());
                    txtNic.setText(customer.getNic());
                    txtContact.setText(String.valueOf(customer.getContact()));
                    String gender = customer.getGender();

                    if (gender.equals("male")){
                        radioBtnMail.setSelected(true);
                    }else {
                        radioBtnFemail.setSelected(true);
                    }
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"SOMETHINGS HAPPENED!!!").show();
            }catch (NullPointerException e){
                new Alert(Alert.AlertType.ERROR,"CUSTOMER NOT FOUND!!!").show();
            }
        }
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer_details_form.fxml"));
        Parent load= loader.load();
        CustomerDetailsFormController controller = loader.getController();
        controller.setAction1();
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
