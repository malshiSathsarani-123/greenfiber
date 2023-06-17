package lk.ijse.greenfiber.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.greenfiber.bo.BOFactory;
import lk.ijse.greenfiber.bo.custom.LoginBO;
import lk.ijse.greenfiber.bo.custom.impl.LoginBOImpl;

import java.io.IOException;
import java.sql.SQLException;


public class LoginFormController {

    @FXML
    private TextField lblPassword;

    @FXML
    private TextField lblUserId;

    @FXML
    private AnchorPane root;

    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);
    @FXML
    void btnFpasswordOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/createAccount_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Create Account");
        stage.centerOnScreen();
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String name = lblUserId.getText();
        String password = lblPassword.getText();

        try {
            boolean isConform = loginBO.conform(name,password);
            if (isConform){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
                AnchorPane anchorPane = loader.load();
                DashboardFormController controller = loader.getController();
                controller.setUserName(lblUserId.getText());
                //  AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("DASH BORD");
                stage.centerOnScreen();
            }else {
                new Alert(Alert.AlertType.ERROR,"SORRY!!! RECHECK YOUR PASSWORD OR USER NAME!!!").show();
                lblUserId.setText("");
                lblPassword.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"SOMETHING HAPPENED!!!").show();
        }
    }

    @FXML
    void btnSigIn(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/createAccount_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Create Account");
        stage.centerOnScreen();
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        lblPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        String name = lblUserId.getText();
        String password = lblPassword.getText();

        try {
            boolean isConform = loginBO.conform(name,password);
            if (isConform){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
                AnchorPane anchorPane = loader.load();
                DashboardFormController controller = loader.getController();
                controller.setUserName(lblUserId.getText());
              //  AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("DASH BORD");
                stage.centerOnScreen();
            }else {
                new Alert(Alert.AlertType.ERROR,"SORRY!!! RECHECK YOUR PASSWORD OR USER NAME!!!").show();
                lblUserId.setText("");
                lblPassword.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"SOMETHING HAPPENED!!!").show();
        }

//        if (lblUserId.getText().equals("admin") && lblPassword.getText().equals("1234")) {
//            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
//            Scene scene = new Scene(anchorPane);
//            Stage stage = (Stage) root.getScene().getWindow();
//            stage.setScene(scene);
//            stage.setTitle("DASH BORD");
//            stage.centerOnScreen();
//        }else{
//            new Alert(Alert.AlertType.ERROR, "SORRY!!! PASSWORD IS INCORRECT!!! \n PLACE TRY AGAIN!!!").show();
//            lblPassword.setText("");
//        }

    }
}
