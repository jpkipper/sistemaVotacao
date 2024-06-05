package br.com.db.sistema.votacao.v1.model.enums;

public enum VoteEnum 
{
    SIM( "SIM" ),
    NÃO( "NÃO" );

    private final String value;

    VoteEnum( String vote )
    {
        this.value = vote;
    }

    public String getValue()
    {
        return value;
    }
}
