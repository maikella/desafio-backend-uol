package com.desafio.uol.exception;

public class CodinomeIndisponivelException extends RuntimeException {
    String mensagem;
    public CodinomeIndisponivelException(String mensagem){
        super(mensagem);
    }
    
}
