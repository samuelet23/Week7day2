package Week7.Day2.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(NotFoundElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorType notFoundExceptionHandler(NotFoundElementException e){
        return new ErrorType(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorType exceptionHandler(Exception e){
        return new ErrorType(e.getMessage());
    }

    @ExceptionHandler(ElementAlreadyAssignedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorType elementAlreadyAssignedExceptionHandler(ElementAlreadyAssignedException e){
        return new ErrorType(e.getMessage());
    }
    @ExceptionHandler(DispositivoNonAssegnatoException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ErrorType dispositivoNonAssegnatoExceptionHandler(DispositivoNonAssegnatoException e){
        return new ErrorType(e.getMessage());
    }
    @ExceptionHandler(DispositivoInManutenzioneException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorType dispositivoInManutenzioneExceptionHandler(DispositivoInManutenzioneException e){
        return new ErrorType(e.getMessage());
    }
    @ExceptionHandler(DispositvoDismessoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorType dispositivoDismessoExceptionHandler(DispositvoDismessoException e){
        return new ErrorType(e.getMessage());
    }
    @ExceptionHandler(BadRequestExcpetion.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorType badRequestExceptionHandler(BadRequestExcpetion e){
        return new ErrorType(e.getMessage());
    }
    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorType UnAuthorizedExceptionHandler(BadRequestExcpetion e){
        return new ErrorType(e.getMessage());
    }



}
