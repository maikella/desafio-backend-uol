package com.desafio.uol.exception;

public class NaoHaJogadoresCadastradosException extends RuntimeException {
    String mensagem;

    public NaoHaJogadoresCadastradosException(String mensagem) {
        super(mensagem);
    }

}
