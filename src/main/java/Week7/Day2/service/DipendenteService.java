package Week7.Day2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Week7.Day2.Exception.DispositivoNonAssegnatoException;
import Week7.Day2.Exception.ElementAlreadyAssignedException;
import Week7.Day2.Exception.NotFoundElementException;
import Week7.Day2.model.Dipendente.Dipendente;
import Week7.Day2.model.Dipendente.DipendenteRequest;
import Week7.Day2.model.Dispositivo.Dispositivo;
import Week7.Day2.model.Type.StatoDispositivo;
import Week7.Day2.repository.DipendenteRepository;
import Week7.Day2.repository.DispositivoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private PasswordEncoder encoder;


    public List<Dipendente> getAll() throws NotFoundElementException {
        List<Dipendente> dipendenti =dipendenteRepository.findAll();
        if (dipendenti.isEmpty()) {
            throw new NotFoundElementException("La lista è vuota");
        }
        return dipendenti;
    }

    public Dipendente getById(int id) throws NotFoundElementException {
        Optional<Dipendente> optionalDipendente = dipendenteRepository.findById(id);
        if (optionalDipendente.isEmpty()){
            throw new NotFoundElementException("Dipende con id= "+id+" non trovato");
        }
        return optionalDipendente.get();
    }

    public Dipendente getByUsername(String username) throws NotFoundElementException {
        return dipendenteRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundElementException("Dipendente non trovato"));
    }
    public Dipendente saveDipendente(DipendenteRequest dipendente){
        Dipendente d = new Dipendente();
        d.setNome(dipendente.getNome());
        d.setCognome(dipendente.getCognome());
        d.setUsername(dipendente.getUsername());
        d.setPassword(encoder.encode(dipendente.getPassword()));
        d.setRuolo(dipendente.getRuolo());
        d.setEmail(dipendente.getEmail());
        sendEmail(
                d.getEmail(),
                d,
                "Registrazione al servizio di Gestione Dispositivi",
                "Ciao"+dipendente.getUsername() +"Siamo lieti di darti il benvenuto al nostro servizio di gestione dispositivi! Siamo qui per semplificare la tua esperienza nella gestione dei dispositivi, offrendoti soluzioni efficienti e personalizzate per le tue esigenze. Con il nostro servizio, avrai accesso a una gamma di strumenti e funzionalità progettate per ottimizzare il controllo e la sicurezza dei tuoi dispositivi. Che tu stia gestendo dispositivi personali o aziendali, siamo qui per supportarti in ogni passo del processo.");
        return dipendenteRepository.save(d);
    }



    public Dipendente updateDipendente(int id, DipendenteRequest dipendente) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setNome(dipendente.getNome());
        d.setCognome(dipendente.getCognome());
        d.setUsername(dipendente.getUsername());
        d.setPassword(encoder.encode(dipendente.getPassword()));
        d.setEmail(dipendente.getEmail());
        sendEmail(d.getEmail(),
                d,
                "Aggiornamento Dati Dipendente",
                "Il dipendente con id="+d.getId()+" è stato aggiornato adesso le sue credenziali sono;" +
                        "Nome: "+d.getNome()+
                        "Cognome: "+d.getCognome()+
                        "Username: "+d.getUsername()+
                        "Le tue credenziali sono state aggironate con successo"
        );
        return dipendenteRepository.save(d);
    }
    public Dipendente updateNome(int id, String nome) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setNome(nome);
        sendEmail(d.getEmail(),
                d,
                "Aggiornamento nome",
                "Ciao" +d.getNome()+ ", Il tuo nome è stato aggiornato con successo");
        return dipendenteRepository.save(d);
    }
    public Dipendente updateCognome(int id, String cognome) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setCognome(cognome);
        sendEmail(d.getEmail(),
                d,
                "Aggiornamento cognome",
                "Ciao"+ d.getCognome()+ " ,Il tuo cognome è stato aggiornato ");
        return dipendenteRepository.save(d);

    }
    public Dipendente updateUsername(int id, String username) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setUsername(username);
        sendEmail(d.getEmail(),
                d,
                "Aggiornamento Username",
                "Ciao"+d.getUsername()+"Il tuo username è stato aggiornato");
        return dipendenteRepository.save(d);

    }
    public Dipendente updateEmail(int id, String email) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setEmail(email);
        sendEmail(d.getEmail(),
                d,
                "Aggiornamento email",
                "Ciao"+ d.getUsername()+", La tua email è stata aggiornata con sucesso"
                );
        return dipendenteRepository.save(d);

    }
    public Dipendente updatePassword(int id, String password) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setPassword(encoder.encode(password));
        sendEmail(d.getEmail(),
                d,
                "Aggiornamento password",
                "Ciao"+ d.getUsername()+", La tua password è stata aggiornata con sucesso"
                );
        return dipendenteRepository.save(d);

    }

    public void deleteDipendente(int id) throws NotFoundElementException {
        Dipendente d = getById(id);
        dipendenteRepository.delete(d);
    }

    public Dipendente upload(int id, String url) throws NotFoundElementException {
        Dipendente dipendente = getById(id);
        dipendente.setImg(url);
        return dipendenteRepository.save(dipendente);
    }
    private void sendEmail(String email, Dipendente dipendente,String testoSubject,String testoEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(testoSubject);
        message.setText(testoEmail);
    }

}
