package br.diogo.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diogo.gestao_vagas.exceptions.CompanyAlreadyExists;
import br.diogo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.diogo.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity create(CompanyEntity company) {
        boolean companyHasAlreadyBeenRegistered = this.companyRepository
                .findByEmail(company.getEmail())
                .isPresent();

        if (companyHasAlreadyBeenRegistered) {
            throw new CompanyAlreadyExists();
        }
        CompanyEntity companyRegistered = this.companyRepository.save(company);

        return companyRegistered;
    }
}
