package client.view.applicant.overview;
import java.util.ArrayList;
import java.util.List;

import client.core.ViewHandler;
import client.model.IUserModel;
import client.model.UserModel;
import common.transferObjects.JobAd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class ListViewBtnCustom {
    private static ViewHandler vh;
    private static IUserModel model;
    public ListViewBtnCustom(ViewHandler vh, IUserModel model){
        this.vh = vh;
        this.model = model;
    }


    public static class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button();

        HBoxCell(String labelText, String buttonText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            button.setText(buttonText);
            button.setOnAction(e->{
                ArrayList<JobAd> jobAds = model.getClientJobAds();
                JobAd ad = null;
                for(int i = 0; i < jobAds.size(); i++){
                    if(jobAds.get(i).getJobTitle().equals(label.getText())){
                        ad = jobAds.get(i);
                    }
                }
                if(ad == null){
                    button.setText("Error");
                }else{
                    vh.openJobSearch(ad);
                }

            });

            this.getChildren().addAll(label, button);
        }
    }

    public Parent createList(ArrayList<JobAd> jobAds, String title) {
        BorderPane layout = new BorderPane();

        List<HBoxCell> list = new ArrayList<>();
        for (int i = 0; i < jobAds.size(); i++) {
            list.add(new HBoxCell(jobAds.get(i).getJobTitle(), "See more"));
        }

        ListView<HBoxCell> listView = new ListView<HBoxCell>();
        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);

        layout.setTop(new Label(title));
        layout.setCenter(listView);

        return layout;
    }
}
