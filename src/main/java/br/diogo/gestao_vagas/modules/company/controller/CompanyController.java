package br.diogo.gestao_vagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.diogo.gestao_vagas.exceptions.ErroMessage;
import br.diogo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.diogo.gestao_vagas.modules.company.services.CreateCompanyUseCase;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase useCase;

    @PostMapping("/create")
    public ResponseEntity<Object> postMethodName(@RequestBody @Valid CompanyEntity entity) {
        try {
            var response = this.useCase.create(entity);
            return ResponseEntity.ok().body(response);
            
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println("Essa é a mensagem de erro: " + message);

            ErroMessage errorMessage = new ErroMessage(message);
            return ResponseEntity.badRequest().body(errorMessage);

        }
    }
    
}
