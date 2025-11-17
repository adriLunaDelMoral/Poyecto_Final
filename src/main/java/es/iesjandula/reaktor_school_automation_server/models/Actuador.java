package es.iesjandula.reaktor_school_automation_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="actuador")
public class Actuador extends Dispositivo
{

}
