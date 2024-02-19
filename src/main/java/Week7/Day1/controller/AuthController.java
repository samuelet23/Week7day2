package Week7.Day1.controller;

import Week7.Day1.Exception.BadRequestExcpetion;
import Week7.Day1.Exception.NotFoundElementException;
import Week7.Day1.model.Dipendente.Dipendente;
import Week7.Day1.model.Dipendente.DipendenteRequest;
import Week7.Day1.service.DipendenteService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private DipendenteService dipendenteService;


    @PostMapping("/register")
    private Dipendente register(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult)  {
      badRequestException(bindingResult);
       return dipendenteService.saveDipendente(dipendenteRequest);
    }





    public static void checkIOException(BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors() ) {
            throw new IOException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void badRequestException(BindingResult bindingResult) {
        if (bindingResult.hasErrors() ) {
            throw new BadRequestExcpetion(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkNotFoundElementException(BindingResult bindingResult) throws NotFoundElementException {
        if (bindingResult.hasErrors()) {
            throw new NotFoundElementException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkException(BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new NotFoundElementException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }

}
