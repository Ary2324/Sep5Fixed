package client.view.login;

import client.core.ViewModel;
import client.model.IUserModel;
import client.model.UserModel;
import client.model.UserNotFoundException;
import common.util.UserAlreadyConnectedException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty userNameProperty = new SimpleStringProperty("");
    private StringProperty ipProperty = new SimpleStringProperty("10.154.220.82");
    private StringProperty errorLabelProperty = new SimpleStringProperty("");


    public LoginViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
    }


    public StringProperty getUserNameProperty(){
        return userNameProperty;
    }
    public StringProperty getIpProperty(){
        return ipProperty;
    }
    public StringProperty getErrorLabelProperty(){
        return errorLabelProperty;
    }

    public void login() throws UserNotFoundException, InvalidLoginData, UserAlreadyConnectedException {
        checkAdmin(userNameProperty.get());
        if(userNameProperty.get().equals("")){
            throw new InvalidLoginData("Invalid Username");
        }
        if(ipProperty.get().equals("")){
            throw new InvalidLoginData("Invalid IP");
        }
        //IMPORTANT ORDER
        if(clientModel.connectToServer(userNameProperty.get(), ipProperty.get())){
            System.out.println("LoginView::login::success");
        }else{
            System.out.println("LoginView::login::fail");
            throw new InvalidLoginData("Server not found");
        }
    }
    private void checkAdmin(String username){
        if(username.equals("admin1234")){
            UserModel.getInstance().resetModel();
            System.exit(22);
        }
    }

    public void logInError(String msg){
        errorLabelProperty.setValue(msg);
    }

    public void createNewUser(char type){
        clientModel.createNewUser(userNameProperty.get(),type);
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
