package lk.ijse.greenfiber.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.UserBO;
import lk.ijse.greenfiber.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtFname;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtPassCode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtReTypePassword;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("DASH BORD");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtFname.getText();
        try {
            boolean isDelete = userBO.delete(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION,"YOUR DETAILS DELETED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.CONFIRMATION,"YOUR DETAILS NOT DELETED!!!").show();
            clear();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!txtPassCode.getText().equals("MaSaJa78@")){
            new Alert(Alert.AlertType.ERROR,"SORRY RECHECK YOUR PASSCODE!!!").show();
            txtPassCode.setText("");
        }else {
            if (!txtPassword.getText().equals(txtReTypePassword.getText())){
                new Alert(Alert.AlertType.ERROR,"SORRY RECHECK YOUR PASSWORD!!!").show();
                txtPassword.setText("");
                txtReTypePassword.setText("");
            }else {
                boolean isConform = passwordIsConform();
                if (isConform){
                    save();
                }
            }
        }
    }

    private boolean passwordIsConform() {
        if (txtPassword.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")){
            return true;
        }else {
            new Alert(Alert.AlertType.ERROR,"PASSWORD INVALIDED!!!").show();
            return false;
        }
    }

    void save(){
        String name = txtFname.getText();
        String contact = txtContact.getText();
        String passWord = txtPassword.getText();
        String mail = txtMail.getText();

        UserDTO user = new UserDTO(name, contact, passWord,mail);

        try {
            boolean isSave = userBO.save(user);
            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION," YOUR DETAILS REGISTERED!!!").show();
                clear();
            }
            if (!isSave){
                new Alert(Alert.AlertType.ERROR," YOUR USER NAME ALREADY REGISTERED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,"SORRY!! YOUR NOT REGISTERED!!!").show();
            e.printStackTrace();
            clear();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!txtPassCode.getText().equals("MaSaJa78@")){
            new Alert(Alert.AlertType.ERROR,"SORRY RECHECK YOUR PASSCODE!!!").show();
            txtPassCode.setText("");
        }else {
            if (!txtPassword.getText().equals(txtReTypePassword.getText())){
                new Alert(Alert.AlertType.ERROR,"SORRY RECHECK YOUR PASSWORD!!!").show();
                txtPassword.setText("");
                txtReTypePassword.setText("");
            }else {
                boolean isConform = passwordIsConform();
                if (isConform){
                    update();
                }
            }
        }
    }

    private void update() {
        String name = txtFname.getText();
        String contact = txtContact.getText();
        String passWord = txtPassword.getText();
        String mail = txtMail.getText();

        UserDTO user = new UserDTO(name, contact, passWord,mail);

        try {
            boolean isUpdate = userBO.update(user);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION," YOUR DETAILS UPDATED!!!").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,"SORRY!! YOUR DETAILS NOT UPDATED!!!").show();
            e.printStackTrace();
            clear();
        }
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {actionCursor(txtMail);}

    @FXML
    void txtFnameOnAction(ActionEvent event) {actionCursor(txtContact);}

    @FXML
    void txtMailOnAction(ActionEvent event) {actionCursor(txtPassword);}

    @FXML
    void txtPassCodeOnAction(ActionEvent event) {
        if (!txtPassCode.getText().equals("MaSaJa78@")){
            new Alert(Alert.AlertType.ERROR,"SORRY RECHECK YOUR PASSCODE!!!").show();
            txtPassCode.setText("");
        }else {
            actionCursor(txtFname);
        }
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        boolean isConform = passwordIsConform();
        if (isConform){
            actionCursor(txtReTypePassword);
        }else {
            new Alert(Alert.AlertType.ERROR,"PASSWORD INVALIDED!!!").show();
            txtPassword.setText("");
        }
    }

    @FXML
    void txtReTypePasswordOnAction(ActionEvent event) {
        if (!txtPassword.getText().equals(txtReTypePassword.getText())){
            new Alert(Alert.AlertType.ERROR,"SORRY RECHECK YOUR PASSWORD!!!").show();
            txtPassword.setText("");
            txtReTypePassword.setText("");
        }
    }

    void actionCursor(TextField txt){txt.requestFocus();}

    void clear(){
        txtPassCode.setText("");
        txtContact.setText("");
        txtPassword.setText("");
        txtFname.setText("");
        txtMail.setText("");
        txtReTypePassword.setText("");
    }
}



