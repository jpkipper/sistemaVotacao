package br.com.db.sistema.votacao.v1.model.enums;

public enum AssociateStatusEnum 
{
    ABLE_TO_VOTE( "ABLE_TO_VOTE" ),
    UNABLE_TO_VOTE( "UNABLE_TO_VOTE" );

    private final String value;

    AssociateStatusEnum( String value )
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}