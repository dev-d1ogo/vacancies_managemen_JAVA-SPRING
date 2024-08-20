package br.diogo.gestao_vagas.modules.company.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.diogo.gestao_vagas.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID>{
    Optional<CompanyEntity> findByEmail(String email);

    @Query(value = "SELECT * FROM company c WHERE company_user = :company_user", nativeQuery = true)
    Optional<CompanyEntity> findByCompany(@Param("company_user") String company_user);
    
}
