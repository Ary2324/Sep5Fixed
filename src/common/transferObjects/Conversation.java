package common.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Conversation implements Serializable {
    private int id;
    private ArrayList<User> users = new ArrayList<>();
    private int jobId;

    private ArrayList<String> messages = new ArrayList<String>();

    public Conversation(int id, User applicant, JobAd ad){
        users.add(applicant);
        users.add(ad.getCompany());
        this.jobId = ad.getJobId();
        this.id = id;
    }
    public Conversation(int id){
        this.id = id;
    }


    public void addMsg(User sender, String text){
        String nextMsg;
        if(sender.getFullName().equals("")){
            nextMsg = sender.getUsername() +": "+ text;
        }else{
            nextMsg = sender.getFullName()+ ": "+ text;
        }
        messages.add(nextMsg);
    }

    public ArrayList<String> getAllMessages() {
        return messages;
    }

    public int getId(){
        return id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getJobId() {
        return jobId;
    }
    public void setJobId(int newId){
        jobId = newId;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean containsUser(User user){
        if(users == null) {
            return false;
        }
        if(users.size()<2){
            return false;
        }
        if(users.get(0).getUsername().equals(user.getUsername())){
            return true;
        }else{
            return users.get(1).getUsername().equals(user.getUsername());
        }
    }
    public boolean containsUser(String fullName){
        if(users == null) {
            System.out.println("1");
            return false;
        }
        if(users.size()<2){
            System.out.println("2");
            return false;
        }
        if(users.get(0).getFullName().equals(fullName)){
            System.out.println("3");
            return true;
        }else{
            System.out.println("4");
            return users.get(1).getUsername().equals(fullName);
        }
    }

    public String getMessagesForDB() {
        String result = "ARRAY [";
        if(messages.size() == 0){
            return "NULL";
        }else{
            for(int i = 0; i<messages.size(); i++){
                result +="'" + messages.get(i) + "'";
                if(i+1 < messages.size()){
                    result += ", ";
                }
            }
            result += "]";
            return result;
        }

    }

    public void update(Conversation conversation){
        id = conversation.getId();
        users = conversation.getUsers();
        jobId = conversation.getJobId();
        messages = conversation.getAllMessages();
    }
     public User getOtherUser(User thisUser){
        if(users.get(0).getUsername().equals(thisUser.getUsername())){
            return users.get(1);
        }else {
            return users.get(0);
        }
     }


}
