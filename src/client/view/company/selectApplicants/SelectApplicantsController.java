package client.view.company.selectApplicants;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class SelectApplicantsController implements FXMLController {
    private ViewHandler viewHandler;
    private SelectApplicantsViewModel searchApplicantsVM;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        searchApplicantsVM = (SelectApplicantsViewModel)vm;

        topLabel.textProperty().bindBidirectional(searchApplicantsVM.topLabelProperty());
        applicantName.textProperty().bindBidirectional(searchApplicantsVM.applicantNameProperty());
        subtitle.textProperty().bindBidirectional(searchApplicantsVM.subtitleProperty());
        personalInformation.textProperty().bindBidirectional(searchApplicantsVM.personalInformationProperty());
        loadSkillsList();
    }

    @FXML
    private Button nextApplicantBtn;

    @FXML
    private Button prevApplicantButton;


    @FXML
    private Label applicantName;

    @FXML
    private Label subtitle;

    @FXML
    private Label topLabel;

    @FXML
    private TextArea personalInformation;

    @FXML
    private ListView<String> skills;

    @FXML
    void onNextApplicantBtn(ActionEvent event) {
        searchApplicantsVM.onNextApplicantBtn();
        loadSkillsList();
    }

    @FXML
    void onPrevApplicantBtn(ActionEvent event) {
        searchApplicantsVM.onPrevApplicantBtn();
        loadSkillsList();
    }
    @FXML
    void onOpenChatBtn(ActionEvent event){
        searchApplicantsVM.onOpenChatBtn(viewHandler);
        viewHandler.openChatView();
    }


    private void loadSkillsList(){
        skills.setItems(searchApplicantsVM.applicantSkillsList());
    }
}
