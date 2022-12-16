package client.view.company.selectApplicants;

import client.core.ViewHandler;
import client.core.ViewModel;
import client.model.IUserModel;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAd;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SelectApplicantsViewModel implements ViewModel {
    private IUserModel clientModel;
    private Applicant applicant;
    private JobAd jobAd;
    private int currentApplicantIndex;

    private StringProperty applicantName= new SimpleStringProperty("Applicant name");
    private  StringProperty subtitle= new SimpleStringProperty("subtitle");
    private  StringProperty personalInformation= new SimpleStringProperty("INFROMATION");
    private  StringProperty jobTitle= new SimpleStringProperty("JobTitle");
    private  StringProperty actionBtnProperty= new SimpleStringProperty("");

    public SelectApplicantsViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        if(clientModel.getClientJobAds().size()>0){
            loadJobAd(clientModel.getClientJobAds().get(0));
        }else{
            loadJobAd(new JobAd("No applicants", clientModel.getUser(), "",new ArrayList<String>()));
            jobTitle.setValue("No Applicants");
        }

    }
    public void loadJobAd(JobAd jobAd){
       this.jobAd = jobAd;
       jobTitle.setValue("Applicants for: " + jobAd.getJobTitle());
       if(jobAd.getApplicants().size()>0){
           applicant = jobAd.getApplicants().get(0);
           fillApplicantInfo();
       }else{
           jobTitle.setValue("No Applicants");
           applicant = new Applicant("No user found");

       }


    }
    public void onOpenChatBtn(ViewHandler vh){
        for(int i = 0; i<clientModel.getAllConversations().size(); i++){
            if(clientModel.getAllConversations().get(i).containsUser(applicant)){
                vh.openChatView(applicant);
                return;
            }
        }
        clientModel.createNewConversation(applicant, jobAd);
    }


    public void onNextApplicantBtn(){
        showNextApplicant();
    }

    public void onPrevApplicantBtn(){
        showPrevApplicant();
    }

    private void showNextApplicant(){
        int nextApplicantIndex = currentApplicantIndex +1;

        if(nextApplicantIndex >= jobAd.getApplicants().size()){
            nextApplicantIndex = 0;
            currentApplicantIndex = -1;
        }
        setApplicant(jobAd.getApplicants().get(nextApplicantIndex));
        currentApplicantIndex++;
    }
    private void showPrevApplicant(){
        int prevApplicant = currentApplicantIndex - 1;

        if(prevApplicant < 0){
            prevApplicant = jobAd.getApplicants().size() - 1;
            currentApplicantIndex = jobAd.getApplicants().size();
        }
        setApplicant(jobAd.getApplicants().get(prevApplicant));
        currentApplicantIndex --;
    }

    private void setApplicant(Applicant applicant){
        this.applicant = applicant;
        fillApplicantInfo();
    }

    private void fillApplicantInfo(){
        applicantName.setValue(applicant.getFullName());
        subtitle.setValue(applicant.getSubtitle());
        personalInformation.setValue(applicant.getDetails());
    }
    public StringProperty topLabelProperty(){
        return jobTitle;
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
    public StringProperty actionBtnProperty(){
        return actionBtnProperty;
    }

    public ObservableList<String> applicantSkillsList(){
        List<String> list = new ArrayList<>();
            for(int j = 0; j < applicant.getQualities().size(); j++){
                list.add(applicant.getQualities().get(j));
            }

        ObservableList<String> myObservableList = FXCollections.observableList(list);
        return myObservableList;
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
