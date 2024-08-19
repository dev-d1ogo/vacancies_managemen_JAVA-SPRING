package br.diogo.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.diogo.gestao_vagas.exceptions.ErroMessage;
import br.diogo.gestao_vagas.modules.candidate.CandidateEntity;
import br.diogo.gestao_vagas.modules.candidate.services.CreateCandidateUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
   @Autowired
   private CreateCandidateUseCase useCase;

   @PostMapping("/create")
   public ResponseEntity<Object> createUser(@Valid @RequestBody CandidateEntity entity) {
      try {
         var candidate = this.useCase.create(entity);
         return ResponseEntity.ok().body(candidate);
      } catch (Exception e) {  
         String message = e.getMessage();

         System.out.println("Essa é a mensagem de erro: " + message);
         ErroMessage errorMap = new ErroMessage(message);

         return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMap);
      }
   }

}
