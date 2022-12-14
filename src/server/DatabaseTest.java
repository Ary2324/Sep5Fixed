package server;

import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.Conversation;
import common.transferObjects.JobAd;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseTest {
    static JDBCConnector database = new JDBCConnector();

    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<>();
        database.connect("hattie.db.elephantsql.com", 5432, "zdpvllpz", "DkaoNfKGKMNfkg8bVfKyN3pJxPM2GWmn");

        //test.add("SUPER COOL");

        //database.insertNewJobAdd(new JobAd("Backend Dev", new Company("Kamstrup"), "Job Description dfasjdsakjdnaksdnasdnasda", test));
        //database.insertNewUser(new Applicant("maja"));

        //database.updateUser(database.getUser("arla"));
       // System.out.println(database.insertConversation(database.getUser("alice"), database.getAllJobAds().get(1) ));
        database.truncateAllTables();
        database.truncateQualitiesTable();

        database.close();


    }
}
