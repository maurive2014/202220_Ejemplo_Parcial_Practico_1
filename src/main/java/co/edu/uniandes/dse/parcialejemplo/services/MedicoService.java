package co.edu.uniandes.dse.parcialejemplo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {
    
    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public MedicoEntity createMedico(MedicoEntity medicoEntity) throws EntityNotFoundException, IllegalOperationException {
            log.info("Inicia proceso de creación del médico");
                    
            if (!validateRM(medicoEntity.getRegistroMedico()))
                    throw new IllegalOperationException("Registro médico is not valid");

            if (!medicoRepository.findByRegistroMedico(medicoEntity.getRegistroMedico()).isEmpty())
                    throw new IllegalOperationException("Registro médico already exists");

            log.info("Termina proceso de creación del médico");
            return medicoRepository.save(medicoEntity);
    }

    private boolean validateRM(String registroMedico) {
		return (registroMedico != null && registroMedico.length()>2 && registroMedico.substring(0, 2).equals("RM"));
	}

}
