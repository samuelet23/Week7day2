package Week7.Day2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import Week7.Day2.model.Dispositivo.Dispositivo;
import Week7.Day2.model.Type.StatoDispositivo;

import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {

    @Query("SELECT d FROM Dispositivo d WHERE d.statoDispositivo = :statoDispositivo")
    List<Dispositivo> findAllByStatoDispositivo(StatoDispositivo statoDispositivo);

}
