package es.iesjandula.reaktor_school_automation_server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.reaktor_school_automation_server.dtos.ActuadorRequestDto;
import es.iesjandula.reaktor_school_automation_server.models.Actuador;
import es.iesjandula.reaktor_school_automation_server.repository.IActuadorRepository;
import es.iesjandula.reaktor_school_automation_server.utils.Constants;
import es.iesjandula.reaktor_school_automation_server.utils.SistemaVozException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/actuador")
@RestController
public class ActuadorRestController
{
	@Autowired
    private IActuadorRepository actuadorRepository;    

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> crearActuador(@RequestBody(required = true) ActuadorRequestDto actuadorRequestDto) {
        try {
            if (actuadorRequestDto.getNombreDispositivo() == null || actuadorRequestDto.getNombreDispositivo().isEmpty()) {
                log.error(Constants.ERR_ACTUADOR_NULO_VACIO);
                throw new SistemaVozException(Constants.ERR_ACTUADOR_CODE, Constants.ERR_ACTUADOR_NULO_VACIO);
            }
            if (this.actuadorRepository.existsById(actuadorRequestDto.getNombreDispositivo())) {
                log.error(Constants.ERR_ACTUADOR_EXISTE);
                throw new SistemaVozException(Constants.ERR_ACTUADOR_CODE, Constants.ERR_ACTUADOR_EXISTE);
            }
            Actuador actuador = new Actuador();
            actuador.setNombreDispositivo(actuadorRequestDto.getNombreDispositivo());
            actuador.setEstado(actuadorRequestDto.getEstado());
            this.actuadorRepository.saveAndFlush(actuador);
            log.info(Constants.ELEMENTO_AGREGADO);
            return ResponseEntity.ok().build();
        } catch (SistemaVozException exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerActuador() {
        return ResponseEntity.ok().body(this.actuadorRepository.findAll());
    }
}
