package com.desafio.uol.exception;

public class GrupoNaoExisteException extends RuntimeException {
    String grupo;
    public GrupoNaoExisteException(String grupo){
        super("Grupo \"" + grupo + "\" não existe");
    }
    
}
