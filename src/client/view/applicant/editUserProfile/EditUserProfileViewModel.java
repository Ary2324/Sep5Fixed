package client.view.applicant.editUserProfile;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public class EditUserProfileViewModel implements ViewModel {
    private IUserModel clientModel;
    private StringProperty applicantName = new SimpleStringProperty("");
    private StringProperty subtitle = new SimpleStringProperty("");
    private StringProperty personalInformation = new SimpleStringProperty("");
    private StringProperty nextQuality = new SimpleStringProperty("");
    private QualitiesListView qualityList = new QualitiesListView();
    public EditUserProfileViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        loadInfoFromModel();
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
    public StringProperty getNextQuality(){
        return  nextQuality;
    }


    private void loadInfoFromModel(){
        applicantName = new SimpleStringProperty(clientModel.getUser().getFullName());
        subtitle = new SimpleStringProperty(clientModel.getUser().getSubtitle());
        personalInformation = new SimpleStringProperty(clientModel.getUser().getDetails());
    }

    public void safeInfo(){
        clientModel.getUser().setFullName(applicantName.get());
        clientModel.getUser().setSubtitle(subtitle.get());
        clientModel.getUser().setDetails(personalInformation.get());
        clientModel.getUser().setQualities(qualityList.getPickedQualities());
        clientModel.updateUser();
    }
    public Node getFullList(){
        qualityList = new QualitiesListView();
        System.out.println(clientModel.getAllQualities().toString());
        return qualityList.createCheckedList("Skills", clientModel.getAllQualities(), clientModel.getUser().getQualities());
    }
    public void setQualityListEditable(boolean isEditable){
        qualityList.setEditable(isEditable);
    }

    public void addQuality(){
        System.out.println("addQuality::" + nextQuality.get());
        clientModel.addNewQuality(nextQuality.get());
        clientModel.getUser().getQualities().add(nextQuality.get());
        nextQuality.setValue("");
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
