package client.view.login;

public class InvalidLoginData extends RuntimeException{
    private String msg = "";
    public InvalidLoginData(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
}
