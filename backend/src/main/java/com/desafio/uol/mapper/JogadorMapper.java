package com.desafio.uol.mapper;

import org.springframework.stereotype.Component;

import com.desafio.uol.dto.AtualizarJogadorDTO;
import com.desafio.uol.dto.JogadorDTO;
import com.desafio.uol.model.JogadorModel;

@Component
public class JogadorMapper {
    public JogadorModel dtoParaEntidade(JogadorDTO jogadorDTO, String codinome, String grupo) {
        return new JogadorModel(codinome, jogadorDTO.nome(), jogadorDTO.email(), jogadorDTO.telefone(), grupo);
    }

    public JogadorModel atualizarInformacoesJogador(AtualizarJogadorDTO jogadorDTO, JogadorModel jogador) {
        jogador.setNome(jogadorDTO.nome());
        jogador.setEmail(jogadorDTO.email());
        jogador.setTelefone(jogadorDTO.telefone());

        return jogador;
    }
}
