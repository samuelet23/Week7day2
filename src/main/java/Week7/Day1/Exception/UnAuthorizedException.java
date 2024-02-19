package Week7.Day1.Exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException (String message){
        super(message);
    }
}
