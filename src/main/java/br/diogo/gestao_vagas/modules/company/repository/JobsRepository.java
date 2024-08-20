package br.diogo.gestao_vagas.modules.company.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.diogo.gestao_vagas.modules.company.entities.JobEntity;

public interface JobsRepository extends JpaRepository<JobEntity, UUID>{
}
