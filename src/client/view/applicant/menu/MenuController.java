package client.view.applicant.menu;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController implements FXMLController {
    public ViewHandler viewHandler;
    public MenuViewModel menuVM;

    @FXML
    Button overviewBtn;
    @FXML
    Button editProfileBtn;
    @FXML
    Button jobSearchBtn;
    @FXML
    Button chatBtn;
    @FXML
    Button logoutBtn;
    @FXML
    void onChatBtn(ActionEvent event) {
        viewHandler.openChatView();
    }

    @FXML
    void onEditProfileBtn(ActionEvent event) {
        viewHandler.openEditUserProfileView();
    }

    @FXML
    void onJobSearchBtn(ActionEvent event) {
        viewHandler.openJobSearch();
    }

    @FXML
    void onLogoutBtn(ActionEvent event) {
        menuVM.resetModel();
        viewHandler.openLoginView();
    }

    @FXML
    void onOverviewBtn(ActionEvent event) {
        viewHandler.openOverview();
    }



    public void init(ViewHandler viewHandler, ViewModel menuVM) {
        this.viewHandler = viewHandler;
        this.menuVM = (MenuViewModel)menuVM;
    }
}
