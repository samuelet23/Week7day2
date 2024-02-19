package Week7.Day1.Exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
