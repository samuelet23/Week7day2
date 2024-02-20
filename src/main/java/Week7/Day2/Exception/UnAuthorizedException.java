package Week7.Day2.Exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException (String message){
        super(message);
    }
}
