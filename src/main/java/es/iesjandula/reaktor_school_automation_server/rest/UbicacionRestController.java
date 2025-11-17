package es.iesjandula.reaktor_school_automation_server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.reaktor_school_automation_server.dtos.UbicacionRequestDto;
import es.iesjandula.reaktor_school_automation_server.models.Ubicacion;
import es.iesjandula.reaktor_school_automation_server.repository.IUbicacionRepository;
import es.iesjandula.reaktor_school_automation_server.utils.Constants;
import es.iesjandula.reaktor_school_automation_server.utils.SistemaVozException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/api/ubicacion")
@RestController
public class UbicacionRestController
{
	@Autowired
    private IUbicacionRepository ubicacionRepository;    

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> crearUbicacion(@RequestBody(required = true) UbicacionRequestDto ubicacionRequestDto) {
        try {
            if (ubicacionRequestDto.getNombreUbicacion() == null || ubicacionRequestDto.getNombreUbicacion().isEmpty()) {
                log.error(Constants.ERR_UBICACION_NULO_VACIO);
                throw new SistemaVozException(Constants.ERR_UBICACION_CODE, Constants.ERR_UBICACION_NULO_VACIO);
            }
            if (this.ubicacionRepository.existsById(ubicacionRequestDto.getNombreUbicacion())) {
                log.error(Constants.ERR_UBICACION_EXISTE);
                throw new SistemaVozException(Constants.ERR_UBICACION_CODE, Constants.ERR_UBICACION_EXISTE);
            }
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setNombreUbicacion(ubicacionRequestDto.getNombreUbicacion());
            this.ubicacionRepository.saveAndFlush(ubicacion);
            log.info(Constants.ELEMENTO_AGREGADO);
            return ResponseEntity.ok().build();
        } catch (SistemaVozException exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerUbicacion() {
        return ResponseEntity.ok().body(this.ubicacionRepository.findAll());
    }
}
