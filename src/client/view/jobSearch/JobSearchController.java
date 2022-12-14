package client.view.jobSearch;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.view.jobAd.JobAdViewModel;
import common.util.LogBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class JobSearchController implements FXMLController {
    private ViewHandler viewHandler;
    private JobSearchViewModel jobSearchVM;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        jobSearchVM = (JobSearchViewModel)vm;

        positionNameLabel.textProperty().bindBidirectional(jobSearchVM.positionNameProperty());
        companyNameLabel.textProperty().bindBidirectional(jobSearchVM.companyNameProperty());
        jobDescriptionField.textProperty().bindBidirectional(jobSearchVM.jobDescriptionProperty());
        companyDescriptionField.textProperty().bindBidirectional(jobSearchVM.companyDescriptionProperty());
        applyNowBtn.textProperty().bindBidirectional(jobSearchVM.actionBtnProperty());
        applyNowBtn.setDisable(jobSearchVM.alreadyApplied());
        loadReqList();
    }
    @FXML
    private Button applyNowBtn;

    @FXML
    private Label companyNameLabel;

    @FXML
    private TextArea jobDescriptionField;

    @FXML
    private TextArea companyDescriptionField;

    @FXML
    private Button nextAddBtn;

    @FXML
    private Circle pictureCircle;

    @FXML
    private Label positionNameLabel;

    @FXML
    private Button prevAddBtn;

    @FXML
    private ListView<String> reqListView;

    @FXML
    void onApplyNowBtn(ActionEvent event) {
        if(jobSearchVM.getActionBtn()=='A'){
            jobSearchVM.onApplyBtn();
            jobSearchVM.onNextAddBtn();
            loadReqList();
        }else{
            viewHandler.openEditJobView(jobSearchVM.getCurrentJobAd());
        }

    }

    @FXML
    void onNextAddBtn(ActionEvent event) {
        jobSearchVM.onNextAddBtn();
        loadReqList();
    }

    @FXML
    void onPrevAddBtn(ActionEvent event) {
        jobSearchVM.onPrevAddBtn();
        loadReqList();
    }

    private void loadReqList(){
        reqListView.setItems(jobSearchVM.jobRequirementsList());
        applyNowBtn.setDisable(jobSearchVM.alreadyApplied());
    }

}
