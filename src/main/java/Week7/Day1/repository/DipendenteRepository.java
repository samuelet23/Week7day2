package Week7.Day1.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import Week7.Day1.model.Dipendente.Dipendente;
import Week7.Day1.model.Dipendente.DipendenteRequest;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer>{

    public Optional<Dipendente> findByUsername(String username);

}
