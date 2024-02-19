package Week7.Day1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Week7.Day1.Exception.*;
import Week7.Day1.model.Dipendente.Dipendente;
import Week7.Day1.model.Dipendente.DipendenteRequest;
import Week7.Day1.model.Dispositivo.Dispositivo;
import Week7.Day1.model.Dispositivo.DispositivoRequest;
import Week7.Day1.service.DispositivoService;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivi")
public class DispositivoController {


    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("")
    public List<Dispositivo> getAll() throws NotFoundElementException {
        return dispositivoService.getAll();
    }

    @GetMapping("/inManutenzione")
    public List<Dispositivo> getAllInManutenzione() throws NotFoundElementException {
        return dispositivoService.getAllInManutenzione();
    }
    @GetMapping("/dismessi")
    public List<Dispositivo> getAllDismessi() throws NotFoundElementException {
        return dispositivoService.getAllDismessi();
    }

    @GetMapping("/{id}")
    public Dispositivo getDispositivoById(@PathVariable int id ) throws NotFoundElementException {
        return dispositivoService.getById(id);
    }

    @PostMapping("")
    public Dispositivo saveDispositivo(@RequestBody @Validated  DispositivoRequest dispositivo, BindingResult bindingResult) throws Exception {
        checkException(bindingResult);
        return dispositivoService.saveDispositivo(dispositivo);
    }

    @PutMapping("/{id}")
    public Dispositivo updateDispositivo (@PathVariable int id, @RequestBody @Validated DispositivoRequest dispositivo, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dispositivoService.updateDispositivo(id,dispositivo);
    }

    @PatchMapping("/{id}/inManutenzione")
    public Dispositivo updateStatoDispositivoInManutenzione(@PathVariable int id ) throws NotFoundElementException {
        return dispositivoService.setinManutenzione(id);
    }
    @PatchMapping("/{id}/dismesso")
    public Dispositivo updateStatoDispositivoDismesso(@PathVariable int id ) throws NotFoundElementException {
        return dispositivoService.setDismesso(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDispositivo(@PathVariable int id) throws NotFoundElementException {
        dispositivoService.deleteDispositivo(id);
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
    public static void checkElementAlreadyAssignedException(BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new ElementAlreadyAssignedException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkDispositivoNonAssegnatoException(BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new DispositivoNonAssegnatoException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkDispositivoInManutenzioneException(BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new DispositivoInManutenzioneException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkDispositivoDismessoException(BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new DispositvoDismessoException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
}
