package Week7.Day1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import Week7.Day1.model.Dispositivo.Dispositivo;
import Week7.Day1.model.Dispositivo.DispositivoRequest;
import Week7.Day1.model.Type.StatoDispositivo;

import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {

    @Query("SELECT d FROM DispositivoRequest d WHERE d.statoDispositivo = :statoDispositivo")
    List<Dispositivo> findAllByStatoDispositivo(StatoDispositivo statoDispositivo);

}
