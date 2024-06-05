package br.com.db.sistema.votacao.v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.db.sistema.votacao.v1.model.entity.Associate;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long>
{
    Optional<Associate> findByCpf(String cpf);
}
