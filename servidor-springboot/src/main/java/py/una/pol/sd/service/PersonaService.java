/*
* Integrantes:
        - Esteban Gabriel Fernández Arrúa - 5425495
        - Oscar Augusto Bianciotto Lobasso - 4850464
* */

package py.una.pol.sd.service;

import java.util.List;
import org.springframework.stereotype.Service;
import py.una.pol.sd.model.Persona;
import py.una.pol.sd.repository.PersonaRepository;
import py.una.pol.sd.utils.StringUtils;

@Service
public class PersonaService {

    PersonaRepository repository;

    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }

    public List<Persona> getPersonas(){

        return repository.findAll();
    }
    
    public Persona crear(Persona p){
        validatePersona(p);
        if (repository.existsById(p.getCedula())) {
            throw new IllegalArgumentException("Persona ya existe");
        }
        return repository.save(p);
    }

    private Persona get(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Persona no encontrada")
        );
    }

    private void validatePersona(Persona per) {
        if (per == null) {
            throw new IllegalArgumentException("Persona no puede ser nula");
        }
        if (StringUtils.isEmpty(per.getCedula())) {
            throw new IllegalArgumentException("Cedula no puede ser nula");
        }
        if (StringUtils.isEmpty(per.getNombre())) {
            throw new IllegalArgumentException("Nombre no puede ser nulo");
        }
        if (StringUtils.isEmpty(per.getApellido())) {
            throw new IllegalArgumentException("Apellido no puede ser nulo");
        }
    }

    public Persona update(Persona per) {
        Persona personaDb = get(per.getCedula());
        personaDb.setNombre(per.getNombre());
        personaDb.setApellido(per.getApellido());
        return repository.save(personaDb);
    }

    public void delete(Integer id) {
        Persona personaDb = get(id);
        repository.delete(personaDb);
    }
}
