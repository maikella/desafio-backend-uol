package com.desafio.uol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.uol.dto.AtualizarJogadorDTO;
import com.desafio.uol.dto.JogadorDTO;
import com.desafio.uol.model.JogadorModel;
import com.desafio.uol.service.JogadorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("jogadores")
public class JogadorController {
    @Autowired
    private JogadorService service;

    @GetMapping
    public List<JogadorModel> retornarJogadores(){
        return service.retornarJogadores();
    }

    @PostMapping
    public void cadastrarJogador(@Valid @RequestBody JogadorDTO jogadorDTO) throws JsonMappingException, JsonProcessingException {
        service.cadastrarJogador(jogadorDTO);
    }

    @PatchMapping("{codinome}")
    public void atualizarJogador (@PathVariable String codinome, @RequestBody AtualizarJogadorDTO jogador){
        service.atualizarJogadorPorCodinome(codinome, jogador);
    }

    @DeleteMapping("{codinome}")
    public void removerJogadorPorCodinome(@PathVariable String codinome) {
            service.removerJogadorPorCodinome(codinome);
    }

}