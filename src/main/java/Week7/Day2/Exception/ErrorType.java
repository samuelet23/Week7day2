package Week7.Day2.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
public class ErrorType {

    private  String message;
    private  LocalDate dateError;

    public ErrorType(){
        makeDateErrorFinal();
    }
    public ErrorType(String message){
        this.message = message;
        makeDateErrorFinal();
    }

    public ErrorType(String message, HttpStatus status){
        this.message = message;
        makeDateErrorFinal();
    }

    private void makeDateErrorFinal(){
        this.dateError =LocalDate.now();
    }
}
