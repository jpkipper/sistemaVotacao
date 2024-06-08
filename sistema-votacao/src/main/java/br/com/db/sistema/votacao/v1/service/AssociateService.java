package br.com.db.sistema.votacao.v1.service;

import org.springframework.stereotype.Service;

import br.com.db.sistema.votacao.v1.exception.exceptions.DuplicateObjectException;
import br.com.db.sistema.votacao.v1.exception.exceptions.NotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.repository.AssociateRepository;
import br.com.db.sistema.votacao.validator.CpfValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssociateService
{
    private final AssociateRepository associateRepository;
    private final CpfValidator cpfValidator;

    public void createAssociate(@NonNull AssociateDTO associateDTO )
    {
        String cpf = associateDTO.getCpf();
        cpfValidator.isCPFValid(cpf);
        
        if(associateRepository.existsByCpf(cpf))
        {
            throw new DuplicateObjectException("Já existe um associado cadastrado com este documento: " + cpf);
        }

        Associate associate = new Associate();
        associate.setCpf(cpf);
        associate.setName(associateDTO.getName());
        associate.setAssociateStatusEnum(associateDTO.getStatus());

        associateRepository.save(associate);
    }

    public AssociateDTO findAssociateDTO( String cpf )
    {
        Associate associate = associateRepository.findByCpf(cpf)
                    .orElseThrow(() -> new NotFoundException( "Associate with the CPF: " + cpf + " not found!" ) );

        return AssociateDTO.builder()
            .id(associate.getId())
            .name(associate.getName())
            .cpf(cpf)
            .status(associate.getAssociateStatusEnum())
            .build();
    }

    public Associate findAssociate( String cpf )
    {
        return associateRepository.findByCpf(cpf)
                    .orElseThrow(() -> new NotFoundException( "Associate with the CPF: " + cpf + " not found!" ));
    }
}
