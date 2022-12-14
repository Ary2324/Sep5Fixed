package client.view.applicant.editUserProfile;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class EditUserProfileController implements FXMLController {
    private boolean isEditing = true;
    private ViewHandler viewHandler;
    private EditUserProfileViewModel editApplicantProfileVM;


    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        editApplicantProfileVM = (EditUserProfileViewModel) vm;
        //rootVBox.getChildren().add(editApplicantProfileVM.getList());
        applicantName.textProperty().bindBidirectional(editApplicantProfileVM.applicantNameProperty());
        personalInformation.textProperty().bindBidirectional(editApplicantProfileVM.personalInformationProperty());
        addYourQualityTextField.textProperty().bindBidirectional(editApplicantProfileVM.getNextQuality());
        subtitle.textProperty().bindBidirectional(editApplicantProfileVM.subtitleProperty());
        allowEditing();
    }
    private Node list = null;

    @FXML
    private Button EditBtn;

    @FXML
    private VBox rootVBox;

    @FXML
    private TextField applicantName;

    @FXML
    private TextField subtitle;

    @FXML
    private Circle ProfilePicture;

    @FXML
    private TextArea personalInformation;

    @FXML
    TextField addYourQualityTextField;

    @FXML
    Button addNextQualityBtn;

    @FXML
    void onEditBtn(ActionEvent event) {
        if(isEditing){
            lockEditing();
        }else{
            allowEditing();
        }
    }

    @FXML
    void onAddNextQualityBtn(ActionEvent event){
        if (list != null) {
            rootVBox.getChildren().remove(list);
        }
        editApplicantProfileVM.addQuality();
        list = editApplicantProfileVM.getFullList();
        rootVBox.getChildren().add(list);
        editApplicantProfileVM.setQualityListEditable(true);
    }

    private void allowEditing(){
        EditBtn.setText("Save");
        isEditing = true;
        if (list != null) {
            rootVBox.getChildren().remove(list);
        }
        list = editApplicantProfileVM.getFullList();
        editApplicantProfileVM.setQualityListEditable(true);
        rootVBox.getChildren().add(list);
        applicantName.setEditable(true);
        subtitle.setEditable(true);
        personalInformation.setEditable(true);
        addYourQualityTextField.setEditable(true);

    }
    private void lockEditing(){
        EditBtn.setText("Edit");
        isEditing = false;
        rootVBox.getChildren().remove(list);
        applicantName.setEditable(false);
        subtitle.setEditable(false);
        personalInformation.setEditable(false);
        addYourQualityTextField.setEditable(false);
        editApplicantProfileVM.safeInfo();
        viewHandler.openOverview();
    }

}
