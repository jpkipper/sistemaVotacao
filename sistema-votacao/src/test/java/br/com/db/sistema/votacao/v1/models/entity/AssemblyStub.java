package br.com.db.sistema.votacao.v1.models.entity;

import java.time.LocalDateTime;

import br.com.db.sistema.votacao.v1.model.entity.Assembly;

public class AssemblyStub
{
        public static Assembly createAssemblyWithoutId()
    {
        Assembly assembly = Assembly.builder()
                                    .start( LocalDateTime.now() )
                                    .end( LocalDateTime.now().plusDays( 1 ))
                                    .build();

        return assembly;
    }
    
    public static Assembly createAssemblyWithWrongDates()
    {
        Assembly assembly = Assembly.builder()
                                    .start( LocalDateTime.now().minusDays( 1 ))
                                    .end( LocalDateTime.now() )
                                    .build();

        return assembly;
    }

    public static Assembly createAssemblyWithId()
    {
        Assembly assembly = Assembly.builder()
                                    .id( 1L )
                                    .start( LocalDateTime.now() )
                                    .end( LocalDateTime.now().plusDays( 1 ))
                                    .build();

        return assembly;
    }
    
    public static Assembly createAssemblyWithEndDateBefore()
    {
        Assembly assembly = Assembly.builder()
                                    .id( 1L )
                                    .start( LocalDateTime.now() )
                                    .end( LocalDateTime.now().minusDays( 1 ))
                                    .build();

        return assembly;
    }
}
