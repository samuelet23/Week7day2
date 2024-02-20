package Week7.Day2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Week7.Day2.model.Dipendente.Dipendente;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer>{

    public Optional<Dipendente> findByUsername(String username);

}
