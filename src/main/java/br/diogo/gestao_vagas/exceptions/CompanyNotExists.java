package br.diogo.gestao_vagas.exceptions;

public class CompanyNotExists extends RuntimeException{
    public CompanyNotExists(){
        super("Nenhuma empresa vinculada");
    }
}