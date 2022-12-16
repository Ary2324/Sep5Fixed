package client.view.chat;


import client.core.ViewModel;

import client.model.IUserModel;
import common.transferObjects.Conversation;
import common.transferObjects.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ChatViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty messageInputProperty = new SimpleStringProperty("");
    private StringProperty topLabelProperty = new SimpleStringProperty("");
    private Conversation currentConversation;
    private ObservableList<String> messageList;
    private ObservableList<String> accountList;
    


    public ChatViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        topLabelProperty.setValue("Chat with ");
        messageList = FXCollections.observableArrayList();
        accountList = FXCollections.observableArrayList();
        if(clientModel.getAllClientConversation().size()>0){
            currentConversation = clientModel.getAllClientConversation().get(0);
            updateAccountList();

        }

        
    }

    public StringProperty getMessageInputProperty(){
        return messageInputProperty;
    }
    public StringProperty getTopLabelProperty(){return topLabelProperty;}
    public ObservableList<String> getMessagesList(){
        return messageList;
    }
    public ObservableList<String> getAccountsList(){
        return accountList;
    }
    
    public void openConversation(User user){
        ArrayList<Conversation> conversations = clientModel.getAllConversations();
        for(int i = 0; i < conversations.size(); i++){
            if(conversations.get(i).containsUser(user)){
                currentConversation = conversations.get(i);
                update();
                topLabelProperty.setValue("Chat with " + currentConversation.getOtherUser(clientModel.getUser()).getFullName());
                break;
            }
        }
    }
    public void openConversation(String username){
        ArrayList<Conversation> conversations = clientModel.getAllConversations();
        for(int i = 0; i < conversations.size(); i++){
            if(conversations.get(i).containsUser(username)){
                currentConversation = conversations.get(i);
                updateMessageList();
                topLabelProperty.setValue("Chat with " + currentConversation.getOtherUser(clientModel.getUser()).getFullName());
                break;
            }
        }
    }
    
    
    public void sentMessage(){
        System.out.println("ChatViewModel::sentMessage");
        currentConversation.addMsg(clientModel.getUser(), messageInputProperty.get());
        messageInputProperty.setValue("");
        clientModel.updateConv(currentConversation);
        update();

    }
    

    public void updateMessageList(){
        messageList.clear();
        for(int i = 0; i < currentConversation.getAllMessages().size(); i++) {
            messageList.add(currentConversation.getAllMessages().get(i));
        }

    }
    public void updateAccountList(){
        accountList.clear();
        for(int i = 0; i < clientModel.getAllClientConversation().size();i++){
            accountList.add(clientModel.getAllClientConversation().get(i).getOtherUser(clientModel.getUser()).getFullName());
        }
    }
    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updateMessageList();
                updateAccountList();
            }
        });
    }



}
