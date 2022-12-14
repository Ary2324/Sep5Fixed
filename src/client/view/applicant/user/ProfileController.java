package client.view.applicant.user;

import client.core.FXMLController;
import client.core.ViewHandler;

import client.core.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class ProfileController implements FXMLController {
    private ViewHandler viewHandler;
    private ProfileViewModel profileVM;
    private static ProfileController instance = null;
        private Node list = null;
        @FXML
        private VBox rootVBox;

        @FXML
        private Label applicantName;

        @FXML
        private Label subtitle;

        @FXML
        private Circle profilePicture;

        @FXML
        private Label personalInformation;

        @FXML
        private ListView qualitiesListView;

        @FXML
        private Label qualitiesLabel;


    public void init(ViewHandler viewHandler, ViewModel vm){
        instance = this;
        this.viewHandler = viewHandler;
        this.profileVM = (ProfileViewModel) vm;
        qualitiesListView.setItems(getObservableList(profileVM.getApplicantList()));
        applicantName.textProperty().bindBidirectional(profileVM.applicantNameProperty());
        personalInformation.textProperty().bindBidirectional(profileVM.personalInformationProperty());
        subtitle.textProperty().bindBidirectional(profileVM.subtitleProperty());

    }
    private ObservableList<String> getObservableList(ArrayList<String> arrayList){
        if(arrayList == null){
            return null;
        }
        List<String> list = arrayList;
        return FXCollections.observableList(list);
    }
    public static ProfileController getInstance(){
        return instance;
    }



}
