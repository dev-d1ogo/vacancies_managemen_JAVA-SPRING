package br.diogo.gestao_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "candidate") // Cria uma tabela no banco de dados com a nossa entidade
@Data // Cria getters e setters 
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank(message = "O campo (name) não pode ser vazio")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "O campo (email) não pode ser vazio")
    @Email(message = "O campo (email) deve conter um e-mail valido")
    private String email;
    private String username;

    @Length(min = 6, message = "A senha deve conter mais de 6 caracteres")
    private String password;
    private String description;
    
    @Length(max = 500)
    private String curriculum;
    @CreationTimestamp
    private LocalDateTime created_at;
}
