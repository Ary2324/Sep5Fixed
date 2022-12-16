package client.core;


import client.model.UserModel;
import client.view.chat.ChatViewModel;
import client.view.company.createJobAd.CreateJobAdViewModel;
import client.view.company.selectApplicants.SelectApplicantsViewModel;
import client.view.jobSearch.JobSearchViewModel;
import common.transferObjects.Conversation;
import common.transferObjects.JobAd;
import common.transferObjects.User;
import common.util.LogBook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private final LogBook log = LogBook.getInstance();
    private ViewModelFactory vmf;
    private Scene mainScene;
    private Stage stage;


    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
        stage = new Stage();
        stage.setOnCloseRequest(ev -> {
            System.out.println("Client model Reset");
            UserModel.getInstance().resetModel();
        });
    }

    public void start() {
        openLoginView();
    }

    public void openLoginView() {
        log.printActivities();
            BorderPane root = (BorderPane)createFromFXML(vmf.getLoginVM(),"../view/login/LoginView.fxml");

            //No MENU BAR

            mainScene = new Scene(root,800, 600);
            stage.setScene(mainScene);
            stage.setTitle("Login");
            stage.show();

    }

    public void openOverview(){
        if(vmf.getModel().getUser().getType()== 'A'){
            openApplicantOverview();
        }else{
            openCompanyOverview();
        }
    }

    public void openEditUserProfileView(){
        BorderPane root = (BorderPane)createFromFXML(vmf.getEditUserVM(),"../view/applicant/editUserProfile/EditUserProfile.fxml");
        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Edit Profile");
        stage.show();

    }
    public void openEditCompanyProfileView(){
        BorderPane root = (BorderPane)createFromFXML(vmf.getEditCompanyProfileVM(),"../view/company/editCompanyProfile/EditCompanyProfile.fxml");
        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Edit Profile");
        stage.show();

    }


    public void openApplicantOverview() {
            BorderPane root = (BorderPane)createFromFXML(vmf.getOverviewVM(),
                    "../view/applicant/overview/Overview.fxml");
            root.setLeft(getApplicantMenuBar());

            mainScene = new Scene(root,800, 600);
            stage.setScene(mainScene);
            stage.setTitle("Applicant Profile Overview");
            stage.show();
    }

    public void openCompanyOverview() {
        BorderPane root = (BorderPane)createFromFXML(vmf.getOverviewCompanyVM(),"../view/company/overviewCompany/OverviewCompany.fxml");

        root.setLeft(getCompanyMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Company Profile Overview");
        stage.show();
    }

    public void openSelectApplicantView(JobAd jobAd){
        SelectApplicantsViewModel vm = vmf.getSelectApplicantsViewModel();
        vm.loadJobAd(jobAd);
        BorderPane root = (BorderPane)createFromFXML(vm,"../view/company/selectApplicants/SelectApplicants.fxml");

        root.setLeft(getCompanyMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Selects Applicants");
        stage.show();
    }
    public void openSelectApplicantView(){
        SelectApplicantsViewModel vm = vmf.getSelectApplicantsViewModel();
        BorderPane root = (BorderPane)createFromFXML(vm,"../view/company/selectApplicants/SelectApplicants.fxml");

        root.setLeft(getCompanyMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Selects Applicants");
        stage.show();
    }

    public void openJobSearch(){
        BorderPane root = (BorderPane)createFromFXML(vmf.getJobSearchVM(),"../view/jobSearch/JobSearch.fxml");

        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Job Search");
        stage.show();
    }
    public void openJobSearch(JobAd jobAd){
        JobSearchViewModel vm = vmf.getJobSearchVM();
        vm.setJobAdd(jobAd);
        BorderPane root = (BorderPane)createFromFXML(vm,"../view/jobSearch/JobSearch.fxml");

        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Job Search");
        stage.show();
    }
    public void openChatView(){
        BorderPane root = (BorderPane)createFromFXML(vmf.getChatVM(),"../view/chat/ChatView.fxml");

        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Chat");
        stage.show();
    }
    public void openChatView(User user){
        ChatViewModel vm = vmf.getChatVM();
        vm.openConversation(user);
        BorderPane root = (BorderPane)createFromFXML(vm,"../view/chat/ChatView.fxml");

        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Chat");
        stage.show();
    }

    public void openAddJobView(){
        BorderPane root = (BorderPane)createFromFXML(vmf.getCreateJobAdVM(),"../view/company/createJobAd/CreateJobAd.fxml");

        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Add Job");
        stage.show();
    }
    public void openEditJobView(JobAd ad){
        CreateJobAdViewModel vm = vmf.getCreateJobAdVM();
        vm.loadJobAdForEdit(ad);
        BorderPane root = (BorderPane)createFromFXML(vm,"../view/company/createJobAd/CreateJobAd.fxml");

        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Edit Job");
        stage.show();
    }
    public VBox getMenuBar(){
        if(vmf.getModel().getUser().getType()=='A'){
            return getApplicantMenuBar();
        }else{
            return getCompanyMenuBar();
        }
    }

    private VBox getApplicantMenuBar(){
        return(VBox)(createFromFXML(vmf.getMenuVM(), "../view/applicant/menu/Menu.fxml"));
    }
    private VBox getCompanyMenuBar(){
        return(VBox)(createFromFXML(vmf.getMenuCompanyVM(), "../view/company/menuCompany/MenuCompany.fxml"));
    }
    public VBox getUserProfile(){
        return (VBox)(createFromFXML(vmf.getUserVM(), "../view/applicant/user/Profile.fxml"));
    }
    public VBox getJobAd(){
        return (VBox)(createFromFXML(vmf.getJobAdVM(), "../view/jobAd/JobAd.fxml"));
    }

    private Parent createFromFXML(ViewModel vm, String filePath){
        Parent layout;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(filePath));
            layout = loader.load();

            FXMLController controller = loader.getController();
            controller.init(this, vm);

        } catch (IOException e) {
            e.printStackTrace();
            layout = null;
        }
        return layout;
    }

    public void showPopup(Popup nextPopup){

        nextPopup.show(stage);
    }

}




