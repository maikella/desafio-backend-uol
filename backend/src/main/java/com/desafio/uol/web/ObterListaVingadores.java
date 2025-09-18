package com.desafio.uol.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.desafio.uol.dto.OsVingadoresDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ObterListaVingadores {
    @Autowired
    private RestTemplate rest;
    @Autowired
    private ObjectMapper mapper;

    public OsVingadoresDTO obterVingadores() throws JsonMappingException, JsonProcessingException {
        final String URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
        String resposta = rest.getForObject(URL, String.class);
        return mapper.readValue(resposta, OsVingadoresDTO.class);
    }

}
