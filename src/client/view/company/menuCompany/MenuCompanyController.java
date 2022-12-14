package client.view.company.menuCompany;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuCompanyController implements FXMLController {
    public ViewHandler viewHandler;
    public MenuCompanyViewModel menuVM;

    @FXML
    Button overviewBtn;
    @FXML
    Button editProfileBtn;
    @FXML
    Button jobAdsBtn;
    @FXML
    Button chatBtn;
    @FXML
    Button logoutBtn;
    @FXML
    private Button applicantsBtn;

    @FXML
    void onChatBtn(ActionEvent event) {
        viewHandler.openChatView();
    }

    @FXML
    void onEditProfileBtn(ActionEvent event) {
        viewHandler.openEditCompanyProfileView();
    }

    @FXML
    void onJobAdsBtn(ActionEvent event) {
        viewHandler.openAddJobView();
    }

    @FXML
    void onLogoutBtn(ActionEvent event) {
        menuVM.resetModel();
        viewHandler.openLoginView();
    }

    @FXML
    void onApplicantsBtn(ActionEvent event){
        viewHandler.openSelectApplicantView();
    }

    @FXML
    void onOverviewBtn(ActionEvent event) {
        viewHandler.openCompanyOverview();
    }



    public void init(ViewHandler viewHandler, ViewModel menuVM) {
        this.viewHandler = viewHandler;
        this.menuVM = (MenuCompanyViewModel)menuVM;
    }
}
