package common.transferObjects;

import java.util.ArrayList;

public interface User {
    String getUsername();
    char getType();
    ArrayList<String> getQualities();
    String getQualitiesForDB();
    void setFullName(String name);
    void setQualities(ArrayList<String> qualities);
    String getDetails();
    void setDetails(String details);
    String getFullName();
    void setSubtitle(String subtitle);
    String getSubtitle();
    void updateUser(User newUser);
    void setConvs(ArrayList<Conversation> convs);
    ArrayList<Conversation> getConvs();
    String getConvsIdForDB();


}
