package client.view.login;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.model.UserNotFoundException;
import client.view.applicant.menu.MenuController;
import common.util.UserAlreadyConnectedException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class LoginViewController implements FXMLController {
    private Popup popup = new Popup();
    private ViewHandler viewHandler;
    private LoginViewModel logInVM;
    @FXML
    TextField loginTextField;
    @FXML
    TextField ipTextField;
    @FXML
    Label errorLabel;


    public void init(ViewHandler viewHandler, ViewModel vm){
        this.viewHandler = viewHandler;
        this.logInVM = (LoginViewModel) vm;
        loginTextField.textProperty().bindBidirectional(logInVM.getUserNameProperty());
        ipTextField.textProperty().bindBidirectional(logInVM.getIpProperty());
        errorLabel.textProperty().bindBidirectional(logInVM.getErrorLabelProperty());
    }


    @FXML
    public void onLoginBtn(){
        logInVM.logInError("");
            try {
                logInVM.login();
                viewHandler.openOverview();

            }catch (UserNotFoundException userNotFound){
                System.out.println("LoginViewController::onLoginBtn::UserNotFoundException");
                showPopUp();
            }catch (InvalidLoginData invalidData){
                System.out.println("LoginViewController::onLoginBtn::InvalidLoginDataException");
                logInVM.logInError(invalidData.getMsg());
            }catch (UserAlreadyConnectedException userAlreadyConnected){
                System.out.println("LoginViewController::onLoginBtn::UserAlreadyConnected");
                logInVM.logInError(userAlreadyConnected.getMsg());
            }
    }
    private Label popupLabel = new Label("Welcome to our app. WHO ARE YOU?");
    private Button popupButtonApplicant = new Button("Applicant");
    private Button popupButtonCompany = new Button("Business User");

    private void showPopUp(){
        VBox popupVBox = new VBox();
        HBox popupHBox = new HBox();
        popupButtonApplicant.setOnAction(new PopupListener());
        popupButtonCompany.setOnAction(new PopupListener());
        popupHBox.getChildren().addAll(popupButtonApplicant,popupButtonCompany);
        popupVBox.getChildren().addAll(popupLabel, popupHBox);
        popupVBox.setSpacing(10);
        popupHBox.setSpacing(5);
        popupHBox.setAlignment(Pos.CENTER);
        popup.getContent().addAll(popupVBox);
        viewHandler.showPopup(popup);
    }



    private class PopupListener implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e)
        {
            if(e.getSource() == popupButtonApplicant){
                logInVM.createNewUser('A');
                popup.hide();
                viewHandler.openEditUserProfileView();
            }else if(e.getSource() == popupButtonCompany){
                logInVM.createNewUser('C');
                popup.hide();
                viewHandler.openEditCompanyProfileView();
            }


        }
    }

}
