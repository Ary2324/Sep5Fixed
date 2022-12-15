package client.view.applicant.overview;

import client.core.ViewHandler;
import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public class OverviewViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty titleLabelProperty;

    public OverviewViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        titleLabelProperty = new SimpleStringProperty("Welcome " + clientModel.getUser().getUsername());
    }

    public Node getRelevantJobs(ViewHandler vh){
        ListViewBtnCustom customList = new ListViewBtnCustom(vh, clientModel);
        return customList.createList(clientModel.getClientJobAds(), "Jobs for you");

    }
    public Node getAppliedJobs(ViewHandler vh){
        ListViewBtnCustom customList = new ListViewBtnCustom(vh,clientModel);
        return customList.createList(clientModel.getAppliedJobAds(), "Jobs you applied");
    }

    public StringProperty getTitleLabelProperty() {
        return titleLabelProperty;
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //UPDATE CODE
            }
        });
    }
}
