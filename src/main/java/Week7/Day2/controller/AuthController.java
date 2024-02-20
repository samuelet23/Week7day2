package Week7.Day2.controller;

import Week7.Day2.Exception.BadRequestExcpetion;
import Week7.Day2.model.Auth.LoginRequest;
import Week7.Day2.model.Dipendente.Dipendente;
import Week7.Day2.model.Dipendente.DipendenteRequest;
import Week7.Day2.security.JwtTools;
import Week7.Day2.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public Dipendente register(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult)  {
      badRequestException(bindingResult);
       return dipendenteService.saveDipendente(dipendenteRequest);
    }

    @PostMapping("/login")
    public String login (@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) throws Exception {
        badRequestException(bindingResult);
        Dipendente dipendente =dipendenteService.getByUsername(loginRequest.getUsername());
        if (encoder.matches(loginRequest.getPassword(), dipendente.getPassword())) {
            return jwtTools.createToken(dipendente);
        }
        else {
            throw new Exception("username/password errato");
        }
    }



    public static void badRequestException(BindingResult bindingResult) {
        if (bindingResult.hasErrors() ) {
            throw new BadRequestExcpetion(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
}
