package es.iesjandula.reaktor_school_automation_server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.reaktor_school_automation_server.dtos.SensorRequestDto;
import es.iesjandula.reaktor_school_automation_server.models.Sensor;
import es.iesjandula.reaktor_school_automation_server.repository.ISensorRepository;
import es.iesjandula.reaktor_school_automation_server.utils.Constants;
import es.iesjandula.reaktor_school_automation_server.utils.SistemaVozException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/sensor")
@RestController
public class SensorRestController
{
	@Autowired
    private ISensorRepository sensorRepository;    


    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> crearSensor(@RequestBody(required = true) SensorRequestDto sensorRequestDto) {
        try {
            if (sensorRequestDto.getNombreDispositivo() == null || sensorRequestDto.getNombreDispositivo().isEmpty()) {
                log.error(Constants.ERR_SENSOR_NULO_VACIO);
                throw new SistemaVozException(Constants.ERR_SENSOR_CODE, Constants.ERR_SENSOR_NULO_VACIO);
            }
            if (this.sensorRepository.existsById(sensorRequestDto.getNombreDispositivo())) {
                log.error(Constants.ERR_SENSOR_EXISTE);
                throw new SistemaVozException(Constants.ERR_SENSOR_CODE, Constants.ERR_SENSOR_EXISTE);
            }
            Sensor sensor = new Sensor();
            sensor.setNombreDispositivo(sensorRequestDto.getNombreDispositivo());
            sensor.setEstado(sensorRequestDto.getEstado());
            sensor.setValor(sensorRequestDto.getValor());
            sensor.setTipo(sensorRequestDto.getTipo());
            this.sensorRepository.saveAndFlush(sensor);
            log.info(Constants.ELEMENTO_AGREGADO);
            return ResponseEntity.ok().build();
        } catch (SistemaVozException exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerSensor() {
        return ResponseEntity.ok().body(this.sensorRepository.findAll());
    }
}
