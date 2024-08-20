package br.diogo.gestao_vagas.modules.company.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diogo.gestao_vagas.exceptions.CompanyNotExists;
import br.diogo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.diogo.gestao_vagas.modules.company.entities.JobEntity;
import br.diogo.gestao_vagas.modules.company.repository.CompanyRepository;
import br.diogo.gestao_vagas.modules.company.repository.JobsRepository;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobsRepository jobsRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity create(JobEntity job) {
        UUID company_id = job.getCompany_id();
        Optional<CompanyEntity> companyExists = this.companyRepository.findById(company_id);

        System.out.println(companyExists.isPresent());
        
        if(!companyExists.isPresent()){
            throw new CompanyNotExists();
        }

        return this.jobsRepository.save(job);
    }
}
