package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {
    
    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity) throws IllegalOperationException {
            log.info("Inicia proceso de creación de la especialidad");
                    
            if (!validateDescripcion(especialidadEntity.getDescripcion()))
                    throw new IllegalOperationException("Descripción is not valid");

            log.info("Termina proceso de creación del médico");
            
            return especialidadRepository.save(especialidadEntity);
    }

    private boolean validateDescripcion(String descripcion) {
		return (descripcion != null && descripcion.length()>9);
	}

}
