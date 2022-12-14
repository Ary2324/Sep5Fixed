package common.networking;


import client.model.UserNotFoundException;
import common.transferObjects.*;
import common.util.UserAlreadyConnectedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServerConnector extends Remote {
    void openConnection(String username)throws UserAlreadyConnectedException, RemoteException;
    void closeConnection(String username)throws RemoteException;
    void createNewUser(User user)throws RemoteException;
    void updateUser(User newUser) throws RemoteException;
    void updateJobAd(JobAd ad) throws RemoteException;
    void updateConv(Conversation conv) throws RemoteException;
    ArrayList<Conversation> getAllConversations() throws RemoteException;
    User getUser(String Username)throws RemoteException, UserNotFoundException;
    ArrayList<String> getAllQualities() throws RemoteException;
    void addNewQuality(String quality) throws RemoteException;
    void addNewJobAd(JobAd nextAd) throws RemoteException;
    Conversation createConversation(User user, JobAd ad) throws RemoteException;
    ArrayList<JobAd> getRelevantJobAds(User user) throws RemoteException;
    ArrayList<JobAd> getAppliedJobAds(User user) throws RemoteException;

}
