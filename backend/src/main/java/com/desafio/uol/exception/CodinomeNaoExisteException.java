package com.desafio.uol.exception;

public class CodinomeNaoExisteException extends RuntimeException {
    String codinome;

    public CodinomeNaoExisteException(String codinome) {
        super("Codinome \"" + codinome + "\" não foi cadastrado");
    }

}
