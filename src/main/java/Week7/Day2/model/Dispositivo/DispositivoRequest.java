package Week7.Day2.model.Dispositivo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import Week7.Day2.model.Type.StatoDispositivo;
import Week7.Day2.model.Type.TipoDispositivo;

@Data
public class DispositivoRequest {

    @NotNull(message = "Il campo desiderato non può essere null")
    private TipoDispositivo tipoDispositivo;

    @NotNull(message = "Il campo desiderato non può essere null")
    private StatoDispositivo statoDispositivo;

    private Integer idDipendente;
}

