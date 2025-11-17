package es.iesjandula.reaktor_school_automation_server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sensor")
public class Sensor extends Dispositivo
{

    @Column(length = 25, nullable = false)
    private String valor;

    @Column(length = 25, nullable = false)
    private String tipo;

}
