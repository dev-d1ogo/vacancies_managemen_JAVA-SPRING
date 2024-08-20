package br.diogo.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    @NotBlank(message = "Digite um e-mail")
    @Email(message = "O campo (email) deve conter um e-mail valido")
    private String email;
    
    @NotBlank(message = "A descrição da empresa não pode ser vazia")
    private String description;

    @NotBlank(message = "A empresa deve conter um nome de acesso")
    private String company_user;

    @NotBlank
    @Length(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    private String password;

    @NotBlank(message = "O nome da empresa não pode ser vazio")
    @Column(unique = true, nullable = false)
    private String name;

    @Pattern(
        regexp = "^(https?:\\/\\/)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+(\\/[a-zA-Z0-9#]+\\/?)?$",
        message = "Website inválido. Tente uma URL válida."
    )
    private String website;

    // Relacionamento inverso
    
    @OneToMany(mappedBy = "company")
    private List<JobEntity> jobs;
    
    

    @CreationTimestamp
    private LocalDateTime created_at;
}
