package client.view.chat;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ChatViewController implements FXMLController{
    private ViewHandler viewHandler;
    private ChatViewModel chatVM;

    @FXML
    ListView<String> messagesListView;

    @FXML
    ListView<String> accountListView;

    @FXML
    TextField inputTextField;

    @FXML
    Label accountNameLabel;

    public void init(ViewHandler viewHandler, ViewModel vm){
        this.viewHandler = viewHandler;
        this.chatVM = (ChatViewModel) vm;
        inputTextField.textProperty().bindBidirectional(chatVM.getMessageInputProperty());
        accountNameLabel.textProperty().bindBidirectional(chatVM.getTopLabelProperty());
        accountListView.setItems(chatVM.getAccountsList());
        messagesListView.setItems(chatVM.getMessagesList());
        
    }

    @FXML
    private void onLogoutBtn(){
        
    }
    @FXML
    private void onSentBtn(){
        chatVM.sentMessage();
    }

    @FXML
    void onContextRequested(ContextMenuEvent event) {
        
    }

    @FXML
    void onRefreshBtn(){chatVM.update();}

    @FXML
    void onListMousePress(MouseEvent event) {
        if(event.getTarget() instanceof Text) {
            String target = event.getTarget().toString();
            String[] newTarget = target.split("\"");
            System.out.println("ChatViewController::onListMousePress::" + newTarget[1]);
            chatVM.openConversation(newTarget[1]);

        }
    }
}
