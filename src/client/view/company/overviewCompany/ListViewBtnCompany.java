package client.view.company.overviewCompany;

import client.core.ViewHandler;
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

import java.util.ArrayList;
import java.util.List;


public class ListViewBtnCompany {
    static ViewHandler vh;
    public ListViewBtnCompany(ViewHandler vh ){
        this.vh = vh;
    }

    public static class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button();
        Button button2 = new Button();

        HBoxCell(String labelText, String buttonText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            button.setText("See applicants");
            button.setOnAction(e->{
                ArrayList<JobAd> jobAds = UserModel.getInstance().getClientJobAds();
                JobAd ad = null;
                for(int i = 0; i < jobAds.size(); i++){
                    if(jobAds.get(i).getJobTitle().equals(label.getText())){
                        ad = jobAds.get(i);
                    }
                }
                vh.openSelectApplicantView(ad);
            });

            button2.setText("Edit");
            button2.setOnAction(e->{
                ArrayList<JobAd> jobAds = UserModel.getInstance().getClientJobAds();
                JobAd ad = null;
                for(int i = 0; i < jobAds.size(); i++){
                    if(jobAds.get(i).getJobTitle().equals(label.getText())){
                        ad = jobAds.get(i);
                    }
                }
                vh.openEditJobView(ad);
            });

            this.getChildren().addAll(label, button, button2);
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
