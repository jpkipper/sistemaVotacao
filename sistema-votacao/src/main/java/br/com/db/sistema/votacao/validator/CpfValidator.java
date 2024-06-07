package br.com.db.sistema.votacao.validator;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.stereotype.Component;

@Component
public class CpfValidator extends CPFValidator
{    
    public void isCPFValid( String cpf )
    {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        boolean isValid = cpfValidator.isValid(cpf, null);

        if( !isValid )
            throw new RuntimeException( "Invalid CPF ( " + cpf + " )");
    }
}
