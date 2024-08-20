package br.diogo.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.diogo.gestao_vagas.exceptions.CompanyAlreadyExists;
import br.diogo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.diogo.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder hashedPassword;

    public CompanyEntity create(CompanyEntity company) {
        this.validateCompanyDoesNotExist(company.getCompany_user());
        this.encodeCompanyPassword(company);
        
        CompanyEntity companyRegistered = this.companyRepository.save(company);

        return companyRegistered;
    }

    private void validateCompanyDoesNotExist(String companyUser) {
        companyRepository.findByCompany(companyUser)
                .ifPresent(existingCompany -> {
                    throw new CompanyAlreadyExists();
                });
    }

    private void encodeCompanyPassword(CompanyEntity company) {
        String encodedPassword = hashedPassword.encode(company.getPassword());
        company.setPassword(encodedPassword);
    }
}
