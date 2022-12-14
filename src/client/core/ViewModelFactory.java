package client.core;

import client.model.IUserModel;
import client.model.UserModel;
import client.view.applicant.user.ProfileViewModel;
import client.view.chat.ChatViewModel;
import client.view.applicant.editUserProfile.EditUserProfileViewModel;
import client.view.company.createJobAd.CreateJobAdViewModel;
import client.view.company.editCompanyProfile.EditCompanyProfileViewModel;
import client.view.company.overviewCompany.OverviewCompanyViewModel;
import client.view.company.selectApplicants.SelectApplicantsViewModel;
import client.view.jobAd.JobAdViewModel;
import client.view.jobSearch.JobSearchViewModel;
import client.view.login.LoginViewModel;
import client.view.applicant.menu.MenuViewModel;
import client.view.company.menuCompany.MenuCompanyViewModel;
import client.view.applicant.overview.OverviewViewModel;

public class ViewModelFactory {
    private IUserModel model= UserModel.getInstance();
    public ViewModelFactory(){
    }

    public IUserModel getModel() {
        return model;
    }

    public LoginViewModel getLoginVM(){
        return new LoginViewModel(model);
    }
    public OverviewViewModel getOverviewVM() {
        return new OverviewViewModel(model);
    }
    public MenuViewModel getMenuVM(){
        return new MenuViewModel(model);
    }
    public MenuCompanyViewModel getMenuCompanyVM(){
        return new MenuCompanyViewModel(model);
    }
    public ProfileViewModel getUserVM(){
        return new ProfileViewModel(model);
    }
    public JobSearchViewModel getJobSearchVM(){
        return new JobSearchViewModel(model);
    }
    public EditUserProfileViewModel getEditUserVM(){
        return new EditUserProfileViewModel(model);
    }
    public ChatViewModel getChatVM(){
        return new ChatViewModel(model);
    }
    public JobAdViewModel getJobAdVM(){
        return new JobAdViewModel(model);
    }
    public EditCompanyProfileViewModel getEditCompanyProfileVM(){return new EditCompanyProfileViewModel(model);}
    public OverviewCompanyViewModel getOverviewCompanyVM(){ return new OverviewCompanyViewModel(model);}
    public CreateJobAdViewModel getCreateJobAdVM(){ return new CreateJobAdViewModel(model);}
    public SelectApplicantsViewModel getSelectApplicantsViewModel(){ return new SelectApplicantsViewModel(model);}
}
