package client.view.company.createJobAd;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.view.company.editCompanyProfile.EditCompanyProfileViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateJobAdController implements FXMLController {
    private ViewHandler viewHandler;
    private CreateJobAdViewModel createJobAdVM;

    private Node list = null;

    @FXML
    private VBox rootVBox;

    @FXML
    private Button addReqBtn;

    @FXML
    private TextField addReqTextField;

    @FXML
    private TextArea jobDescriptionTextArea;

    @FXML
    private TextField jobTitleTextField;

    @FXML
    private Button saveBtn;

    @FXML
    private Label companyNameLabel;

    @FXML
    void onAddReqBtn(ActionEvent event) {
        if (list != null) {
            rootVBox.getChildren().remove(list);
        }
        createJobAdVM.addQuality();
        list = createJobAdVM.getFullList();
        rootVBox.getChildren().add(list);
        createJobAdVM.setQualityListEditable(true);
    }

    @FXML
    void onSaveBtn(ActionEvent event) {
        createJobAdVM.createNewAd();
        viewHandler.openCompanyOverview();
    }

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        createJobAdVM = (CreateJobAdViewModel) vm;

        addReqTextField.textProperty().bindBidirectional(createJobAdVM.nextQualityProperty());
        jobTitleTextField.textProperty().bindBidirectional(createJobAdVM.jobTitleProperty());
        jobDescriptionTextArea.textProperty().bindBidirectional(createJobAdVM.jobDescriptionProperty());
        companyNameLabel.textProperty().bindBidirectional(createJobAdVM.companyNameProperty());

        if (list != null) {
            rootVBox.getChildren().remove(list);
        }
        list = createJobAdVM.getFullList();
        createJobAdVM.setQualityListEditable(true);
        rootVBox.getChildren().add(list);

    }
}
