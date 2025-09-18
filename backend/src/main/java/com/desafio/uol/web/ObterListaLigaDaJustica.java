package com.desafio.uol.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.desafio.uol.dto.LigaDaJusticaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component
public class ObterListaLigaDaJustica {
    @Autowired
    private RestTemplate rest;

    public LigaDaJusticaDTO obterListaLigaDaJustica() {
        final String URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";
        String resposta = rest.getForObject(URL, String.class);

        LigaDaJusticaDTO ligaDaJustica = null;

        try {
            ligaDaJustica = new XmlMapper().readValue(resposta, LigaDaJusticaDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("Erro na desserialização");
        }

        return ligaDaJustica;
    }

}
