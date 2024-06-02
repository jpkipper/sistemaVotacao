package br.com.db.sistema.votacao.v1.enums;

public enum AgendaStatusEnum
{
    WAITING_FOR_RESULT( "Waiting for result" ),
    APPROVED( "Approved" ),
    REJECTED( "Rejected" ),
    TIED( "Tied" );

    private final String value;

    AgendaStatusEnum( String value )
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
