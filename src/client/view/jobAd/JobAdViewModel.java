package client.view.jobAd;

import client.core.ViewModel;
import client.model.IUserModel;
import common.transferObjects.JobAd;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JobAdViewModel implements ViewModel {
    private IUserModel clientModel;
    private static JobAd jobAdd;

    private static StringProperty positionName = new SimpleStringProperty("");
    private static StringProperty subtitle = new SimpleStringProperty("");
    private static StringProperty companyName = new SimpleStringProperty("");
    private static StringProperty jobDescription = new SimpleStringProperty("");
    private static StringProperty requirements = new SimpleStringProperty("");

    public JobAdViewModel(IUserModel model) {
        clientModel = model;
        clientModel.attachObserver(this);
    }


    public static void getJobAdInfo(){
        positionName.setValue(jobAdd.getJobTitle());
        companyName.setValue(jobAdd.getCompany().getFullName());
        jobDescription.setValue(jobAdd.getJobDescription());;
    }

    public static void setJobAdInfo(){
        jobAdd.setJobDescription(jobDescription.get());
        jobAdd.setJobTitle(positionName.get());

    }


    public StringProperty positionNameProperty() {
        return positionName;
    }


    public StringProperty subtitleProperty() {
        return subtitle;
    }


    public StringProperty companyNameProperty() {
        return companyName;
    }


    public StringProperty jobDescriptionProperty() {
        return jobDescription;
    }


    public StringProperty requirementsProperty() {
        return requirements;
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
