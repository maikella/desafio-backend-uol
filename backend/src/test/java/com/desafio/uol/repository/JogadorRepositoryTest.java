package com.desafio.uol.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.desafio.uol.exception.JogadorJaExisteException;
import com.desafio.uol.exception.JogadorNaoExisteException;
import com.desafio.uol.model.JogadorModel;

@SpringBootTest
public class JogadorRepositoryTest {
    @Autowired
    JogadorRepository repository;

    JogadorModel jogador;
    JogadorModel jogadorNaoExiste;

    static int cont = 0;


    @BeforeEach
    void salvarJogador() {
        jogador = new JogadorModel("Batman "+cont, "nome", "email@gmail", null, "liga da justiça");
        repository.salvar(jogador);
        cont++;
    }

    @BeforeEach
    void criarJogador() {
        jogadorNaoExiste = new JogadorModel("Visão", "nome", "email@gmail", null, "liga da justiça");
    }

    @Test
    @DisplayName("Deve retornar o Jogador caso ele seja salvo com sucesso")
    void teste0() {
        JogadorModel jogador = new JogadorModel("Flash", "nome", "email@gmail", null, "liga da justiça");
        Assertions.assertThat(repository.salvar(jogador)).isEqualTo(jogador);
    }

    @Test
    @DisplayName("Deve retornar a exceção 'JogadorJaExistenteException' caso o Jogador já existir")
    void teste1() {
        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(JogadorJaExisteException.class, () -> {
            repository.salvar(jogador);
        });

        org.junit.jupiter.api.Assertions
                .assertEquals("Jogador com o codinome \"" + jogador.getCodinome() + "\" já existe", ex.getMessage());

    }

    @Test
    @DisplayName("Deve retornar o Jogador caso ele exista")
    void teste2() {
        Assertions.assertThat(repository.jogadorExiste(jogador.getCodinome())).isEqualTo(jogador);
    }

    @Test
    @DisplayName("Deve retornar a exceção 'JogadorJaExistenteException' caso o Jogador já não exista")
    void teste3() {
        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(JogadorNaoExisteException.class, () -> {
            repository.jogadorExiste(jogadorNaoExiste.getCodinome());
        });

        org.junit.jupiter.api.Assertions
                .assertEquals("Jogador com o codinome \"" + jogadorNaoExiste.getCodinome() + "\" não existe", ex.getMessage());

    }

    @Test
    @DisplayName("Deve retornar verdadeiro caso o Jogador for removido com sucesso")
    void teste4() {
        Assertions.assertThat(repository.removerJogadorPorCodinome(jogador.getCodinome())).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar a exceção 'JogadorNaoExisteException' caso o Jogador não tenha sido removido com sucesso")
    void teste5() {

        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(JogadorNaoExisteException.class, () -> {
            repository.removerJogadorPorCodinome(jogadorNaoExiste.getCodinome());
        });

        org.junit.jupiter.api.Assertions
                .assertEquals("Jogador com o codinome \"" + jogadorNaoExiste.getCodinome() + "\" não existe", ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar verdadeiro caso o Jogador tenha sido atualizado com sucesso")
    void teste6() {
        Assertions.assertThat(repository.atualizarJogadorPorCodinome(jogador)).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar a exceção 'JogadorNaoExisteException' caso o Jogador não tenha sido atualizado com sucesso")
    void teste7() {

        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(JogadorNaoExisteException.class, () -> {
            repository.atualizarJogadorPorCodinome(jogadorNaoExiste);
        });

        org.junit.jupiter.api.Assertions
                .assertEquals("Jogador com o codinome \"" + jogadorNaoExiste.getCodinome() + "\" não existe", ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar o Jogador relacionado ao codinome caso ele exista")
    void teste8() {
        Assertions.assertThat(repository.retornarJogadorPorCodinome(jogador.getCodinome())).isEqualTo(jogador);
    }

    @Test
    @DisplayName("Deve ser retornado a exceção 'JogadorNaoExisteException' caso o Jogador relacionado ao codinome não exista ")
    void teste9() {

        Exception ex = org.junit.jupiter.api.Assertions.assertThrows(JogadorNaoExisteException.class, () -> {
            repository.removerJogadorPorCodinome(jogadorNaoExiste.getCodinome());
        });

        org.junit.jupiter.api.Assertions
                .assertEquals("Jogador com o codinome \"" + jogadorNaoExiste.getCodinome() + "\" não existe", ex.getMessage());

    }

}