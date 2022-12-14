package client.view.company.editCompanyProfile;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditCompanyProfileViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty companyNameProperty = new SimpleStringProperty("");
    private StringProperty companyDescriptionProperty = new SimpleStringProperty("");


    public EditCompanyProfileViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        loadInfoFromModel();
    }

    private void loadInfoFromModel(){
        companyNameProperty = new SimpleStringProperty(clientModel.getUser().getFullName());
        companyDescriptionProperty = new SimpleStringProperty(clientModel.getUser().getDetails());
    }

    public void safeInfo(){
        clientModel.getUser().setFullName(companyNameProperty.get());
        clientModel.getUser().setDetails(companyDescriptionProperty.get());
        clientModel.updateUser();
    }

    public StringProperty getCompanyNamePropertyProperty() {
        return companyNameProperty;
    }

    public StringProperty getCompanyDescriptionPropertyProperty() {
        return companyDescriptionProperty;
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
