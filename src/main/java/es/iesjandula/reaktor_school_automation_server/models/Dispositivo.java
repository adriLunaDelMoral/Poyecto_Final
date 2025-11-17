package es.iesjandula.reaktor_school_automation_server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
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
@Table(name="dispositivo")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dispositivo
{

    @Column(length = 25)
	private String nombreDispositivo;

	@Column(length = 25)
	private String estado;
	
	@ManyToOne
	private Ubicacion ubicacion;
	
}
