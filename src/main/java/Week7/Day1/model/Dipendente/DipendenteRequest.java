package Week7.Day1.model.Dipendente;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteRequest {

    @NotNull(message = "Il campo username non può essere null")
    @NotEmpty(message = "Il campo username non può essere vuoto")
    @Size(min = 4, message = "Il campo username deve essere di almeno 4 caratteri")
    private String username;


    @NotNull(message = "Il campo password non può essere null")
    @NotEmpty(message = "Il campo password non può essere vuoto")
    @Size(min = 8, message = "Il campo password deve essere di almeno 8 caratteri")
    private String password;


    @NotEmpty( message = "Il campo nome non può essere vuoto")
    @NotNull(message = "Il campo nome non può essere null")
    @Size(min = 3, message = "Il nome deve contenere almeno 3 caratteri")
    private String nome;

    @NotEmpty( message = "Il campo cognome non può essere vuoto")
    @NotNull(message = "Il campo cognome non può essere null")
    @Size(min = 3, message = "Il cognome deve contenere almeno 4 caratteri")
    private String cognome;

    private String img;

    @NotNull
    @Email(
            message = "inserisci un email esistente",
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
    )
    private String email;

//    private List<Integer> idDispositivi;
}
