package client.view.applicant.user;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class ProfileViewModel implements ViewModel {
    ArrayList<String> userQualities = new ArrayList<>();
    private IUserModel clientModel;
    private StringProperty applicantName = new SimpleStringProperty("Full Name");
    private StringProperty subtitle = new SimpleStringProperty("subtitle");
    private StringProperty personalInformation = new SimpleStringProperty("Write something about yourself");

    public ProfileViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        if(clientModel.getUser() != null){
            loadInfoFromModel();
        }


    }
    private void loadInfoFromModel(){
        applicantName = new SimpleStringProperty(clientModel.getUser().getFullName());
        subtitle = new SimpleStringProperty(clientModel.getUser().getSubtitle());
        personalInformation = new SimpleStringProperty(clientModel.getUser().getDetails());
    }
     public char getUserType(){
        try{
            return clientModel.getUser().getType();
        }catch (NullPointerException ex){
            return 0;
        }

     }

     public ArrayList<String> getApplicantList(){
        if(clientModel.getUser() == null){
            return null;
        }
        return clientModel.getUser().getQualities();
     }

    public StringProperty applicantNameProperty() {
        return applicantName;
    }

    public StringProperty subtitleProperty() {
        return subtitle;
    }

    public StringProperty personalInformationProperty() {
        return personalInformation;
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
