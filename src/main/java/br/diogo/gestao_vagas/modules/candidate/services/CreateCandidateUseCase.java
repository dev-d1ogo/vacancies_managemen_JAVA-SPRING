package br.diogo.gestao_vagas.modules.candidate.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diogo.gestao_vagas.exceptions.UserAlreadyExists;
import br.diogo.gestao_vagas.modules.candidate.CandidateEntity;
import br.diogo.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity create(CandidateEntity entity) {
        Optional<CandidateEntity> candidateHasAlreadyRegistered = this.candidateRepository.findByEmail(entity.getEmail());

        if (candidateHasAlreadyRegistered.isPresent()) {
            throw new UserAlreadyExists();
        }
        CandidateEntity candidate = this.candidateRepository.save(entity);

        return candidate;
    }
}
