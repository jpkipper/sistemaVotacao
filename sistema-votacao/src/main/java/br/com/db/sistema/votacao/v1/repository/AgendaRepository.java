package br.com.db.sistema.votacao.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.db.sistema.votacao.v1.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>
{
}
