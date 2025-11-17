package es.iesjandula.reaktor_school_automation_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.reaktor_school_automation_server.models.Dispositivo;


public interface IDispositivoRepository extends JpaRepository<Dispositivo, String>
{

}
