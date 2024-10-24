/*
* Integrantes:
        - Esteban Gabriel Fernández Arrúa - 5425495
        - Oscar Augusto Bianciotto Lobasso - 4850464
* */

package py.una.pol.sd.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.una.pol.sd.dto.ApiResponse;
import py.una.pol.sd.model.Persona;
import py.una.pol.sd.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	PersonaService personaService;

	public PersonaController(PersonaService personaService) {
		this.personaService = personaService;
	}

	@GetMapping("/saludo")
	public String index() {
		return "Hola mundo caluroso de Springboot";
	}

    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<ApiResponse<List<Persona>>> getPersonas() {
		return ApiResponse.ok(
				null,
				personaService.getPersonas()
		);
    }


	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Persona>> create(@RequestBody Persona per) {
		return ApiResponse.ok(
				"Creación exitosa",
				personaService.crear(per)
		);
    }

	@PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<Persona>> update(@RequestBody Persona per) {
		return ApiResponse.ok(
				"Actualización exitosa",
				personaService.update(per)
		);
	}

	@DeleteMapping(value = "/eliminar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id) {
		personaService.delete(id);
		return ApiResponse.ok(
				"Eliminación exitosa",
				"Persona eliminada"
		);
	}

}
