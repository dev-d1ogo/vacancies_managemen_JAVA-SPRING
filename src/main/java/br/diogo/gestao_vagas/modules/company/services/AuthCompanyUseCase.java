package br.diogo.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.diogo.gestao_vagas.exceptions.CompanyNotExists;
import br.diogo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.diogo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.diogo.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secret;

    public String execute(AuthCompanyDTO companyDTO){

        String company_user = companyDTO.getCompany_user();
        var company = validateCompanyDoesExists(company_user);

        String password = companyDTO.getPassword();
        
        String encodedPassword = company.getPassword();
        System.out.println(password);
        System.out.println(encodedPassword);

        validatePassword(password, encodedPassword);

        String company_id = company.getId().toString();


        return generateJWT(company_id);
        
    }

    private CompanyEntity validateCompanyDoesExists(String companyUser) {
        return companyRepository.findByCompany(companyUser)
                .orElseThrow(() -> {
                    throw new CompanyNotExists();
                });
    }

    private void validatePassword(String password, String encodedPassword){
        boolean equalsPassword = passwordEncoder.matches(password, encodedPassword);

        if(!equalsPassword){
            throw new RuntimeException("Senha incorreta");
        }
    }

    private String generateJWT(String company_id){
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        var token = JWT.create()
                        .withIssuer("javagas")
                        .withSubject(company_id)
                        .sign(algorithm);
        return token;

    }
}


