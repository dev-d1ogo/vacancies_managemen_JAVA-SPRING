package br.diogo.gestao_vagas.exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(){
        super("Usuario ja existe");
    }
}
