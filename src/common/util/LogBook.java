package common.util;

import java.util.ArrayList;

public class LogBook {
    private static LogBook instance =  null;
    private ArrayList<String> activities = new ArrayList<String>();
    private ArrayList<String> clientActivities = new ArrayList<String>();
    private ArrayList<String> serverActivities = new ArrayList<String>();
    private int clientPrinted = 0;
    private int serverPrinted = 0;
    private int activitiesPrinted = 0;
    private LogBook(){

    }
    public static LogBook getInstance(){
        if(instance == null){
            return new LogBook();
        }
        return instance;
    }
    public void saveClientActivity(String activity){
        activities.add("Client::" + activity);
        clientActivities.add("Client::" + activity);
    }
    public void saveServerActivity(String activity){
        activities.add("Server::" + activity);
        serverActivities.add("Server::" + activity);
    }
    public void printServerActivities(){
        for(; serverPrinted < serverActivities.size(); serverPrinted++){
            System.out.println(serverActivities.get(serverPrinted).toString());
        }

    }
    public void printClientActivities(){
        for(; clientPrinted < clientActivities.size(); clientPrinted++){
            System.out.println(clientActivities.get(clientPrinted).toString());
        }

    }
    public void printActivities(){
        for(;activitiesPrinted  < activities.size(); activitiesPrinted++){
            System.out.println(activities.get(activitiesPrinted).toString());
        }

    }
    public void quickClientLog(String activity){
        activities.add("Client::" + activity);
        clientActivities.add("Client::" + activity);
        System.out.println("Client::" + activity);
    }
    public void quickServerLog(String activity){
        activities.add("Server::" + activity);
        serverActivities.add("Server::" + activity);
        System.out.println("Server::" + activity);
    }

    public void quickDBLog(String activity){
        String result = "Server::JDBC::"+activity;
        saveServerActivity(result);
        System.out.println(result);
    }

}
