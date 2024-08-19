package br.diogo.gestao_vagas.modules.candidate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.diogo.gestao_vagas.modules.candidate.CandidateEntity;
import java.util.Optional;


// JPA Repository recebe o tipo da nossa entidade e o tipo da chave primaria da nossa tabela

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{
    Optional<CandidateEntity> findByEmail(String email);
}
