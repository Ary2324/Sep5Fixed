package server;

import client.model.UserNotFoundException;
import common.transferObjects.*;
import common.util.LogBook;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;


public class JDBCConnector implements IJDBCConnector{
    private Connection connection;

    private ArrayList<User> allUsers = new ArrayList<>();

    @Override
    public void connect(String host, int portNo, String userName, String password) throws ConnectionFailedException{
        // Establishing a PostgreSQL database connection
        String databaseUrl = "jdbc:postgresql://" + host + ":" + portNo + "/" + userName;

        try {
            connection = DriverManager.getConnection(databaseUrl, userName, password);
            LogBook.getInstance().quickDBLog("connect::success::" + databaseUrl);
        } catch (PSQLException exception) {
            LogBook.getInstance().quickDBLog("connect::failed");
            throw new ConnectionFailedException();
        } catch (Exception ex) {
            LogBook.getInstance().quickDBLog("connect::exception::"+ ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void close() {
        // Close the connection
        try {
            connection.close();
            LogBook.getInstance().quickDBLog("close::success");
        } catch (SQLException exception) {
            LogBook.getInstance().quickDBLog("close::fail");
            exception.printStackTrace();
        }
    }

    private int isOnServer(String username){
        for(int i = 0; i < allUsers.size(); i++){
            if(allUsers.get(i).getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void insertNewUser(User user){
        allUsers.add(user);
        String SQL = "INSERT INTO sep5.User VALUES "
                + "('" + user.getUsername() + "', '" + user.getType() +"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewUser::"+ex.getMessage());
        }
        if(user.getType() == 'A'){
            insertNewApplicant((Applicant) user);
        }else{
            insertNewCompany((Company) user);
        }
    }

    private void insertNewCompany(Company company) {
        String SQL = "INSERT INTO sep5.company VALUES "
                + "('" + company.getUsername() + "', '" + company.getFullName() + "', '"
                + company.getDetails() + "', " + company.getConvsIdForDB() +");";

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewCompany::" + ex.getMessage());
        }
    }

    private void insertNewApplicant(Applicant applicant) {
        String SQL = "INSERT INTO sep5.applicant VALUES "
                + "('" + applicant.getUsername() + "', '" + applicant.getFullName() + "', '" + applicant.getSubtitle()  + "', '"
                + applicant.getDetails() + "', " + applicant.getQualitiesForDB() + ", " + applicant.getConvsIdForDB() +");";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewApplicant::" + ex.getMessage());
        }
    }

    @Override
    public void insertNewJobAdd(JobAd jobAd) {
        String SQL = "INSERT INTO sep5.jobad VALUES "
                + "(DEFAULT, '" + jobAd.getJobTitle() + "', '" + jobAd.getCompany().getUsername() +"', '"
                + jobAd.getJobDescription()+"', " + jobAd.getRequirementsForDB() +");";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewJobAd::" + ex.getMessage());
        }
    }

    @Override
    public void insertQuality(String quality) {
        String SQL = "INSERT INTO sep5.qualities VALUES " + "('"+ quality+"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewQuality::" + ex.getMessage());
        }

    }


    @Override
    public void updateUser(User user) {
        int localIndex = isOnServer(user.getUsername());
        if(localIndex>=0){
            allUsers.get(localIndex).updateUser(user);
        }
        if(user.getType() == 'A'){
            updateApplicant(user);
        }else{
            updateCompany(user);
        }
    }

    private void updateApplicant(User user) {
        System.out.println("update Applicant");
        String SQL = "UPDATE sep5.Applicant SET fullName = '" + user.getFullName() + "'," +
                " subtitle = '" + user.getSubtitle() + "'," +
                "personalinfromation = '" + user.getDetails() + "'," +
                "skills = " + user.getQualitiesForDB() + "," +
                "conversationsIds = " + user.getConvsIdForDB() + " WHERE username = '" + user.getUsername() + "';";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("updateApplicant::"+ex.getMessage());
        }
    }

    private void updateCompany(User user) {
        String SQL = "UPDATE sep5.Company SET companyName = '" + user.getFullName() + "'," +
                "description = '" + user.getDetails() + "'," +
                "conversationsIds = " + user.getConvsIdForDB() + " WHERE username = '" + user.getUsername() + "';";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("updateCompany::"+ex.getMessage());
        }
    }

    @Override
    public void updateJobAd(JobAd jobAd) {
        String SQL = "UPDATE sep5.jobAd SET jobTitle = '" + jobAd.getJobTitle() + "'," +
                "companyName = '" + jobAd.getCompany().getUsername() + "'," +
                "jobDescription = '" + jobAd.getJobDescription() + "'," +
                "requirements = " + jobAd.getRequirementsForDB() + ", " +
                "applicants = " + jobAd.getApplicantsForDB() +  " WHERE jobId = '" + jobAd.getJobId() + "';";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("updateJobAd::"+ ex.getMessage());
        }
    }


    @Override
    public User getUser(String username) throws UserNotFoundException{
            User user;
            if(getUserType(username) =='A'){
                user = getApplicantProfile(username);
            }else{
                user = getCompanyProfile(username);
            }
            return  user;

    }
    private char getUserType(String username) throws UserNotFoundException{
        int localIndex = isOnServer(username);
        if(localIndex >= 0){
            return allUsers.get(localIndex).getType();
        }else {
            String SQL = "SELECT type FROM sep5.User WHERE username = '" + username + "';";
            ResultSet rs;
            char userType = 0;
            try {
                Statement statement = connection.createStatement();
                rs = statement.executeQuery(SQL);

                rs.next();
                userType = rs.getString(1).charAt(0);

            } catch (SQLException ex) {
                LogBook.getInstance().quickDBLog("getUserType::" + ex.getMessage());
            }
            if (userType == 0) {
                throw new UserNotFoundException();
            }
            return userType;
        }
    }

    //Get a CompanyProfile from the DB
    private Company getCompanyProfile(String username){
        int localIndex = isOnServer(username);
        if(localIndex >= 0){
            return (Company)allUsers.get(localIndex);
        }else {
            String SQL = "SELECT* FROM sep5.Company WHERE username = '" + username + "';";
            ResultSet rs;
            Company company = new Company(username);
            try {
                Statement statement = connection.createStatement();
                rs = statement.executeQuery(SQL);

                rs.next();
                company.setFullName(rs.getString("companyname"));
                company.setDetails(rs.getString("description"));
                

            } catch (SQLException ex) {
                LogBook.getInstance().quickDBLog("getCompanyProfile::" + ex.getMessage());
            }
            allUsers.add(company);
            return company;
        }
    }

    // Get an ApplicantProfile from the DB
    private Applicant getApplicantProfile(String username){
        int localIndex = isOnServer(username);
        if(localIndex >= 0){
            return (Applicant)allUsers.get(localIndex);
        }else {
            String SQL = "SELECT* FROM sep5.applicant WHERE username = '" + username + "';";
            ResultSet rs;
            Applicant applicant = new Applicant(username);
            try {
                Statement statement = connection.createStatement();
                rs = statement.executeQuery(SQL);

                rs.next();
                applicant.setFullName(rs.getString("fullname"));
                applicant.setSubtitle(rs.getString("subtitle"));
                applicant.setDetails(rs.getString(4));

                applicant.setQualities(getQualitiesList(rs.getArray(5)));



            } catch (SQLException ex) {
                LogBook.getInstance().quickDBLog("getApplicantProfile::" + ex.getMessage());
            }
            allUsers.add(applicant);
            return applicant;
        }
    }
    private ArrayList<String> getQualitiesList(Array requirements) throws SQLException {
        if(requirements == null){
            return new ArrayList<String>();
        }
        String[] strQualities = (String[])requirements.getArray();
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < strQualities.length; i ++){
            result.add(strQualities[i]);
        }
        return result;
    }


    @Override
    public ArrayList<String> getAllQualities (){
        String SQL = "SELECT* FROM sep5.qualities ;";
        ResultSet rs;
        ArrayList<String> allQualities = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            while(rs.next()){
                allQualities.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("getAllQualities::"+ex.getMessage());
        }
        return allQualities;
    }

    @Override
    public ArrayList<JobAd> getAllJobAds() {
        ArrayList<JobAd> allJobAds = new ArrayList<>();
        String SQL = "SELECT * FROM sep5.jobAd;";
        ResultSet rs;
        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString("jobTitle");
                Company company = getCompanyProfile(rs.getString("companyname"));
                String jobDescriptionJobAd = rs.getString("jobdescription");
                ArrayList<String> requirements = getQualitiesList(rs.getArray("requirements"));
                ArrayList<Applicant> applicants = getApplicants(getQualitiesList(rs.getArray("applicants")));
                allJobAds.add(new JobAd(id,title,company,jobDescriptionJobAd,requirements,applicants));
            }

        }catch(SQLException ex){
            LogBook.getInstance().quickDBLog("getAllJobAds::"+ex.getMessage());
        }

       return allJobAds;
    }
    private ArrayList<Applicant> getApplicants(ArrayList<String> usernames){
        ArrayList<Applicant> result = new ArrayList<>();
        for(int i = 0; i < usernames.size(); i++){
            result.add(getApplicantProfile(usernames.get(i)));
        }
        return result;
    }

    @Override
    public void truncateAllTables() {
        String SQL = "TRUNCATE ONLY sep5.applicant CASCADE;\n" +
                "TRUNCATE ONLY sep5.company CASCADE;\n" +
                "TRUNCATE ONLY sep5.user CASCADE;\n" +
                "TRUNCATE ONLY sep5.jobAd RESTART IDENTITY CASCADE;\n" +
                "TRUNCATE ONLY sep5.conversation RESTART IDENTITY CASCADE;";

        try{
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        }catch(SQLException ex){
            LogBook.getInstance().quickDBLog("truncateTables::"+ex.getMessage());
        }
    }

    @Override
    public void truncateQualitiesTable(){
        String SQL = "TRUNCATE ONLY sep5.qualities CASCADE;";

        try{
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        }catch(SQLException ex){
            LogBook.getInstance().quickDBLog("truncateQualitiesTable::"+ex.getMessage());
        }
    }


}




//
//    //-----------------------------------------------------------------------------------------------------------Applicant OPERATIONS

//
//    // Updates the table Applicant in the DB
//    public void updateApplicant(String name, String attribute, String newValue) {
//        String SQL = "UPDATE sep5.Applicant SET " + attribute + " = '" + newValue + "' WHERE username = '" + name + "';";
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeQuery(SQL);
//
//        } catch (SQLException ex) {
//            LogBook.getInstance().quickDBLog("updateApplicant::"+ex.getMessage());
//        }
        // insert instead of update
//    }
//
//    //-----------------------------------------------------------------------------------------------------------JobAdd OPERATIONS
//    // Get JobAd ID from the DB
//    public int getJobAdId(String jobTitle){
//        String SQL = "SELECT jobId FROM sep5.jobAd WHERE jobTitle = '" + jobTitle + "';";
//        ResultSet rs;
//        int result = 0;
//        try{
//            Statement statement = connection.createStatement();
//            rs = statement.executeQuery(SQL);
//
//            rs.next();
//            result = rs.getInt(0);
//
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }
//        return result;
//    }
//
//    // Stores a new JobAd in the DB
//    public void insertNewJobAdd(JobAdd jobAdd) {

//    }
//
