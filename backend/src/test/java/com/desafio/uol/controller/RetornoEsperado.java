package com.desafio.uol.controller;

import java.util.List;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.desafio.uol.model.JogadorModel;

public class RetornoEsperado implements ResultMatcher {

    final JogadorModel jogador1 = new JogadorModel("Batman1", "nome", "email@gmail", null, "Liga da Justiça");
    final JogadorModel jogador2 = new JogadorModel("Batman2", "nome", "email@gmail", null, "Liga da Justiça");
    final JogadorModel jogador3 = new JogadorModel("Batman3", "nome", "email@gmail", null, "Liga da Justiça");

    List<JogadorModel> jogadores = List.of(jogador1, jogador2, jogador3);

    @Override
    public void match(MvcResult result) throws Exception {
       result.getRequest().toString().equals(jogadores.toString());
    }

}
