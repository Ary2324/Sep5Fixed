package common.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements User, Serializable {
    private final char TYPE = 'C';
    private String username;
    private String companyName;
    private String description;
    private ArrayList<Conversation> conversations = new ArrayList<>();

    public Company(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public char getType() {
        return TYPE;
    }

    @Override
    public ArrayList<String> getQualities() {
        return null;
    }

    @Override
    public String getQualitiesForDB() {
        return null;
    }
    @Override
    public void setFullName(String name) {
        companyName = name;
    }

    @Override
    public void setQualities(ArrayList<String> qualities) {
        //nothing
    }

    @Override
    public String getDetails() {
        return description;
    }

    @Override
    public void setDetails(String details) {
        description = details;
    }

    @Override
    public String getFullName() {
        return companyName;
    }

    @Override
    public void setSubtitle(String subtitle) {
        // NO NOTHING
    }

    @Override
    public String getSubtitle() {
        return null;
    }

    @Override
    public void updateUser(User newUser) {
        setFullName(newUser.getFullName());
        setDetails(newUser.getDetails());
        setSubtitle(newUser.getSubtitle());
        setConvs(newUser.getConvs());
    }

    @Override
    public void setConvs(ArrayList<Conversation> convs) {
        conversations = convs;
    }

    @Override
    public ArrayList<Conversation> getConvs() {
        return conversations;
    }

    @Override
    public String getConvsIdForDB() {
        String result = "ARRAY [";
        if(conversations.size() == 0){
            return "NULL";
        }else{
            for(int i = 0; i<conversations.size(); i++){
                result += + conversations.get(i).getId();
                if(i+1 < conversations.size()){
                    result += ", ";
                }
            }
            result += "]";
            return result;
        }

    }
}
