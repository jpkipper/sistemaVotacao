package br.com.db.sistema.votacao.validator;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CpfValidator extends CPFValidator
{    
    public void isCPFValid( String cpf ) throws Exception
    {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        boolean isValid = cpfValidator.isValid(cpf, null);

        if( !isValid )
            throw new Exception( "Invalid CPF ( " + cpf + " )");
    }
}
