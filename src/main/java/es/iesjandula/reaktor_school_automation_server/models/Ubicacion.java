package es.iesjandula.reaktor_school_automation_server.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="ubicacion")
public class Ubicacion
{

	@Id
    @Column(length = 25)
	private String nombreUbicacion;

	@OneToMany(mappedBy = "ubicacion")
	private List<Dispositivo> dispositivos;
	

}
