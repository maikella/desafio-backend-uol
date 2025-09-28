package com.desafio.uol.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.desafio.uol.dto.JogadorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class JogadorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    final JogadorDTO jogador = new JogadorDTO("nome", "email@gmail", null, "Liga da Justiça");
    final String mensagemSucesso = "Jogador cadastrado com sucesso";

    @Test
    @DisplayName("Deve retornar mensagem de sucesso caso o Jogador tenha sido cadastrado com sucesso")
    void retornarJogadores1() throws Exception {
        mockMvc.perform(post("/jogadores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(jogador)))
                .andExpect(content().string(mensagemSucesso));
    }

}
