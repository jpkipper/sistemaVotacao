package br.com.db.sistema.votacao.v1.service;

import org.springframework.stereotype.Service;

import br.com.db.sistema.votacao.v1.exception.AssociateNotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.repository.AssociateRepository;
import br.com.db.sistema.votacao.validator.CpfValidator;
import lombok.NonNull;

@Service
public class AssociateService
{
    private final AssociateRepository associateRepository;
    public CpfValidator cpfValidator;

    public AssociateService( AssociateRepository associateRepository )
    {
        this.associateRepository = associateRepository;
    }

    public void createAssociate(@NonNull AssociateDTO associateDTO ) throws Exception
    {
        String cpf = associateDTO.getCpf();
        cpfValidator.isCPFValid( cpf );

        if( findAssociate( cpf ) != null )
            throw new Exception( "Associate already registered!" );

        Associate associate = new Associate();

        associate.setId( associateDTO.getId() );
        associate.setCpf( cpf );
        associate.setName( associateDTO.getName() );
        associate.setStatus( associateDTO.getStatus() );

        associateRepository.save( associate );
    }

    public AssociateDTO findAssociateDTO( String cpf )
    {
        Associate associate = findAssociate( cpf );

        AssociateDTO associateDTO = new AssociateDTO();
        associateDTO.setId( associate.getId() );
        associateDTO.setName( associate.getName() );
        associateDTO.setCpf( cpf );
        associateDTO.setStatus( associate.getStatus() );

        return associateDTO;
    }

    public Associate findAssociate( String cpf )
    {
        return associateRepository.findByCpf( cpf )
            .orElseThrow(() -> new AssociateNotFoundException( "Associate with the CPF: " + cpf + " not found!" ) );
    }
}
