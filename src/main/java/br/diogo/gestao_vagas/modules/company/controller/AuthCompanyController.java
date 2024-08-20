package br.diogo.gestao_vagas.modules.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.diogo.gestao_vagas.exceptions.ErroMessage;
import br.diogo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.diogo.gestao_vagas.modules.company.services.AuthCompanyUseCase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    AuthCompanyUseCase usecase;

    @PostMapping("/company")
    public ResponseEntity<Object> authenticate(@RequestBody AuthCompanyDTO entity) {
        try {
            String token = usecase.execute(entity);
            Map<String, String> tokenDTO = new HashMap<>();

            tokenDTO.put("token", token);
            return ResponseEntity.ok().body(tokenDTO);
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println("Essa é a mensagem de erro: " + message);

            ErroMessage errorMessage = new ErroMessage(message);
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

}
