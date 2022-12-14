package client.view.company.editCompanyProfile;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;


public class EditCompanyProfileController implements FXMLController {
    private boolean isEditing = true;
    private ViewHandler viewHandler;
    private EditCompanyProfileViewModel editCompanyProfileVM;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private TextArea companyDescriptionTextArea;

    @FXML
    private Button saveBtn;

    @FXML
    void onSaveBtn(ActionEvent actionEvent) {
        editCompanyProfileVM.safeInfo();
        viewHandler.openCompanyOverview();
    }
    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        editCompanyProfileVM = (EditCompanyProfileViewModel) vm;
        companyNameTextField.textProperty().bindBidirectional(editCompanyProfileVM.getCompanyNamePropertyProperty());
        companyDescriptionTextArea.textProperty().bindBidirectional(editCompanyProfileVM.getCompanyDescriptionPropertyProperty());

    }

}
