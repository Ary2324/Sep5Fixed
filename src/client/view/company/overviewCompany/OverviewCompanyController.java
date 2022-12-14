package client.view.company.overviewCompany;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.view.company.editCompanyProfile.EditCompanyProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class OverviewCompanyController implements FXMLController {

    private ViewHandler viewHandler;
    private OverviewCompanyViewModel overviewVM;

    @FXML
    private BorderPane borderRoot;

    @FXML
    private TextArea companyDescriptionTextArea;

    @FXML
    private Label companyNameLabel;

    @FXML
    private VBox listVbox;

    @FXML
    private Label topLabel;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        overviewVM = (OverviewCompanyViewModel) vm;
        topLabel.textProperty().bindBidirectional(overviewVM.getTitleLabelProperty());
        companyNameLabel.textProperty().bindBidirectional(overviewVM.getCompanyNameProperty());
        companyDescriptionTextArea.textProperty().bindBidirectional(overviewVM.getCompanyDescriptionProperty());
        listVbox.getChildren().add(overviewVM.getJobAddList(viewHandler));
    }

}
