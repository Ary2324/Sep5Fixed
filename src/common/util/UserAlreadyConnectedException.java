package common.util;

public class UserAlreadyConnectedException extends RuntimeException{
    public String getMsg(){
        return "UserAlreadyConnectedException";
    }
}
