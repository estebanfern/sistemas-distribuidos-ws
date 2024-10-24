package py.una.pol.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.una.pol.sd.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
