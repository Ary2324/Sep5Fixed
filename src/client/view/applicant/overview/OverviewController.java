package client.view.applicant.overview;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class OverviewController implements FXMLController {
    private ViewHandler viewHandler;
    private OverviewViewModel overviewVM;


    @FXML
    HBox centerHBox;
    @FXML
    VBox listsVBox;
    @FXML
    Label titleLabel;

    public void init(ViewHandler viewHandler, ViewModel vm){
        this.viewHandler = viewHandler;
        overviewVM = (OverviewViewModel) vm;
        titleLabel.textProperty().bindBidirectional(overviewVM.getTitleLabelProperty());
        listsVBox.getChildren().addAll(overviewVM.getRelevantJobs(viewHandler), overviewVM.getAppliedJobs(viewHandler));
        centerHBox.getChildren().add(viewHandler.getUserProfile());
    }


}
