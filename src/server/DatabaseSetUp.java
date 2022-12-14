package server;

public class DatabaseSetUp {
    private static DatabaseSetUp currentInstance = null;
    private DatabaseSetUp(){

    }
    public static DatabaseSetUp getInstance(){
        if(currentInstance == null){
            return new DatabaseSetUp();
        }
        return currentInstance;
    }

    public void doStuff(String command){
        System.out.println("Delete all");
    }
}
