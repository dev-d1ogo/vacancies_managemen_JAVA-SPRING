package br.diogo.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity(name = "jobs")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome da empresa não pode ser vazio")
    private String name;

    @Column(unique = true)
    @Email(message = "O campo (email) deve conter um e-mail valido")
    private String email;

    @Length(min = 6, message = "A senha deve conter no minímo 6 caracteres")
    private String password;
    
   

    @ManyToOne()
    @JoinColumn(name = "company_id", nullable = false)
    @NotBlank
    private CompanyEntity company;
    


    @CreationTimestamp
    private LocalDateTime created_at;

}
