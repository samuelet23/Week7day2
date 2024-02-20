package Week7.Day2.controller;

import Week7.Day2.model.Dipendente.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Week7.Day2.Exception.*;
import Week7.Day2.model.Dispositivo.Dispositivo;
import Week7.Day2.model.Dispositivo.DispositivoRequest;
import Week7.Day2.service.DispositivoService;

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
    @PreAuthorize("hasAuthority('VENDITORE')")
    public Dispositivo saveDispositivo(@RequestBody @Validated  DispositivoRequest dispositivo, BindingResult bindingResult) throws Exception {
        checkException(bindingResult);
        return dispositivoService.saveDispositivo(dispositivo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('VENDITORE')")
    public Dispositivo updateDispositivo (@PathVariable int id, @RequestBody @Validated DispositivoRequest dispositivo, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dispositivoService.updateDispositivo(id,dispositivo);
    }

    @PatchMapping("/{id}/inManutenzione")
    @PreAuthorize("hasAuthority('VENDITORE')")
    public Dispositivo updateStatoDispositivoInManutenzione(@PathVariable int id ) throws NotFoundElementException {
        return dispositivoService.setinManutenzione(id);
    }
    @PatchMapping("/{id}/dismesso")
    @PreAuthorize("hasAuthority('VENDITORE')")
    public Dispositivo updateStatoDispositivoDismesso(@PathVariable int id ) throws NotFoundElementException {
        return dispositivoService.setDismesso(id);
    }

    @PatchMapping("/{idDipendente}/{idDispositivo}")
    @PreAuthorize("hasAuthority('VENDITORE')")
    public Dispositivo assegnaDispositivoAlDipendente(@PathVariable int idDipendente, @PathVariable int idDispositivo) throws NotFoundElementException {
        return dispositivoService.assegnaDispositivo(idDipendente, idDispositivo);
    }
    @DeleteMapping("/idDipendente/idDispositivo")
    @PreAuthorize("hasAuthority('VENDITORE')")
    public Dipendente deleteDispositivoDalDipendente(@RequestParam int idDipendente, @RequestParam int idDispositivo) throws NotFoundElementException {
        return dispositivoService.eliminaDispositivoDaDipendente(idDipendente, idDispositivo);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('VENDITORE')")
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
