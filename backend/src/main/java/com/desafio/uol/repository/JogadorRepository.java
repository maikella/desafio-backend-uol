package com.desafio.uol.repository;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.desafio.uol.mapper.JogadorRowMapper;
import com.desafio.uol.model.JogadorModel;

@Repository
public class JogadorRepository {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private JogadorRowMapper jogadorRowMapper;

    public void salvar(JogadorModel jogador) {
        jdbc.update("INSERT INTO jogadores (codinome, nome, email, telefone, grupo) VALUES (?, ?, ?, ?, ?);",
                jogador.getCodinome(),
                jogador.getNome(), jogador.getEmail(), jogador.getTelefone(), jogador.getGrupo());
    }

    public List<JogadorModel> retornarJogadores() {
        return jdbc.query("SELECT * FROM jogadores", jogadorRowMapper);
    }

    public JogadorModel retornarJogadorPorCodinome(String codinome){
        return jdbc.query("SELECT * FROM jogadores WHERE codinome = ?", jogadorRowMapper, codinome).get(0);

    }

    public boolean codinomeExiste(String codinome) {
        return jdbc.query("SELECT * FROM jogadores WHERE codinome = ?", jogadorRowMapper, codinome).size() == 0;
    }

    public void removerJogadorPorCodinome(String codinome) {
        jdbc.update("DELETE FROM jogadores WHERE codinome = ?", codinome);
    }

    public void atualizarJogadorPorCodinome(JogadorModel jogador){
        jdbc.update("UPDATE jogadores SET nome = ?, email = ?, telefone = ? WHERE codinome = ?", jogador.getNome(), jogador.getEmail(), jogador.getTelefone(), jogador.getCodinome());
    }
}
