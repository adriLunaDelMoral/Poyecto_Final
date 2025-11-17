package es.iesjandula.reaktor_school_automation_server.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActuadorRequestDto
{

	private String nombreDispositivo;	
	private String estado;

}
