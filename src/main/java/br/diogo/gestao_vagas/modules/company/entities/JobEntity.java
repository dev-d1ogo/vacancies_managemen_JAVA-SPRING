package br.diogo.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "jobs")
@Data
public class JobEntity {

    @Length(min = 10, message = "A descriçao para o trabalho deve conter um pouco mais de 10 caracteres")
    private String benefits;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Preencha a senioridade")
    private String level;
   
    @NotBlank(message = "A descriçao da vaga deve estar preenchida")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    private UUID company_id;
    
    @CreationTimestamp
    private LocalDateTime created_at;

}
