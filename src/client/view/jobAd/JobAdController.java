package client.view.jobAd;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.view.jobSearch.JobSearchViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;

public class JobAdController implements FXMLController {
    private ViewHandler viewHandler;
    private JobAdViewModel jobAdVM;
    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        jobAdVM = (JobAdViewModel) vm;
        positionNameLabel.textProperty().bindBidirectional(jobAdVM.positionNameProperty());
        companyLabel.textProperty().bindBidirectional(jobAdVM.companyNameProperty());
        jobDescriptionField.textProperty().bindBidirectional(jobAdVM.jobDescriptionProperty());

    }

    @FXML
    private Label companyLabel;

    @FXML
    private TextArea jobDescriptionField;

    @FXML
    private Circle pictureCircle;

    @FXML
    private Label positionNameLabel;


}
