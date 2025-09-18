package com.desafio.uol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.uol.dto.AtualizarJogadorDTO;
import com.desafio.uol.dto.JogadorDTO;
import com.desafio.uol.dto.LigaDaJusticaDTO;
import com.desafio.uol.dto.OsVingadoresDTO;
import com.desafio.uol.exception.CodinomeIndisponivelException;
import com.desafio.uol.exception.CodinomeNaoExisteException;
import com.desafio.uol.exception.GrupoNaoExisteException;
import com.desafio.uol.exception.NaoHaJogadoresCadastradosException;
import com.desafio.uol.mapper.JogadorMapper;
import com.desafio.uol.model.Grupo;
import com.desafio.uol.model.JogadorModel;
import com.desafio.uol.repository.JogadorRepository;
import com.desafio.uol.web.ObterListaLigaDaJustica;
import com.desafio.uol.web.ObterListaVingadores;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class JogadorService {
    @Autowired
    private JogadorRepository repository;
    @Autowired
    private JogadorMapper jogadorMapper;
    @Autowired
    private ObterListaVingadores listaVingadores;
    @Autowired
    private ObterListaLigaDaJustica listaJustica;

    public List<JogadorModel> retornarJogadores() {
        List<JogadorModel> jogadores = repository.retornarJogadores();

        if (jogadores.size() == 0) {
            throw new NaoHaJogadoresCadastradosException("Ainda não há jogadores cadastrados");
        }

        return jogadores;
    }

    public void cadastrarJogador(JogadorDTO jogadorDTO) throws JsonMappingException, JsonProcessingException {
        if (grupoExiste(jogadorDTO.grupo())) {

            JogadorModel jogador = adicionarCodinome(jogadorDTO);

            if (jogador == null) {
                throw new NullPointerException("Jogador é null");
            }
            repository.salvar(jogador);
        }

    }

    private boolean grupoExiste(String grupo) {
        for (Grupo g : Grupo.values()) {
            if (g.getNome().equalsIgnoreCase(grupo)) {
                return true;
            }
        }
        throw new GrupoNaoExisteException(grupo);
    }

    private JogadorModel adicionarCodinome(JogadorDTO jogadorDTO) throws JsonMappingException, JsonProcessingException {
        String codinome = null;
        String grupo = null;

        if (jogadorDTO.grupo().equalsIgnoreCase(Grupo.OS_VINGADORES.getNome())) {
            OsVingadoresDTO osVingadores = listaVingadores.obterVingadores();

            if (osVingadores == null) {
                throw new NullPointerException("Vingadores é null");

            }

            for (int i = 0; i < osVingadores.vingadores().size(); i++) {

                if (repository.codinomeExiste(osVingadores.vingadores().get(i).codinome())) {
                    codinome = osVingadores.vingadores().get(i).codinome();
                    grupo = Grupo.OS_VINGADORES.getNome();
                }

            }
            if (codinomeNull(codinome)) {
                throw new CodinomeIndisponivelException(
                        "Não há codinome disponível para o grupo " + Grupo.OS_VINGADORES.getNome());
            }

        }

        if (jogadorDTO.grupo().equalsIgnoreCase(Grupo.LIGA_DA_JUSTICA.getNome())) {
            LigaDaJusticaDTO ligaDaJustica = listaJustica.obterListaLigaDaJustica();

            for (int i = 0; i < ligaDaJustica.codinomes().ligaDaJustica().size(); i++) {

                if (repository.codinomeExiste(ligaDaJustica.codinomes().ligaDaJustica().get(i))) {
                    codinome = ligaDaJustica.codinomes().ligaDaJustica().get(i);
                    grupo = Grupo.LIGA_DA_JUSTICA.getNome();
                }

            }
            if (codinomeNull(codinome)) {
                throw new NullPointerException(
                        "Não há codinome disponível para o grupo " + Grupo.LIGA_DA_JUSTICA.getNome());
            }
        }

        return jogadorMapper.dtoParaEntidade(jogadorDTO, codinome, grupo);

    }

    private boolean codinomeNull(String codinome) {
        return codinome == null;
    }

    public void removerJogadorPorCodinome(String codinome) {
        codinomeExiste(codinome);

        repository.removerJogadorPorCodinome(codinome);
    }

    public void atualizarJogadorPorCodinome(String codinome, AtualizarJogadorDTO jogadorDTO) {
        codinomeExiste(codinome);

        JogadorModel jogador = repository.retornarJogadorPorCodinome(codinome);

        jogador = jogadorMapper.atualizarInformacoesJogador(jogadorDTO, jogador);
        repository.atualizarJogadorPorCodinome(jogador);

    }

    private void codinomeExiste(String codinome) {

        if (repository.codinomeExiste(codinome)) {
            throw new CodinomeNaoExisteException(codinome);
        }
    }

}
