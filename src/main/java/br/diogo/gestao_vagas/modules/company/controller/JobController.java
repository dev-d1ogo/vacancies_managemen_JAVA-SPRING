package br.diogo.gestao_vagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.diogo.gestao_vagas.exceptions.ErroMessage;
import br.diogo.gestao_vagas.modules.company.entities.JobEntity;
import br.diogo.gestao_vagas.modules.company.services.CreateJobUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private CreateJobUseCase useCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createJob(@Valid @RequestBody JobEntity entity) {
        System.out.println(entity.toString());
        try {
            System.out.println(entity.getCompany_id());

            var response = this.useCase.create(entity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String message = e.getMessage();
            ErroMessage error = new ErroMessage(message);

            return ResponseEntity.badRequest().body(error);
        }
    }

}
