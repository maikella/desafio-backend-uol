package com.desafio.uol.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "liga_da_justica")
public record LigaDaJusticaDTO(
        CodinomesDTO codinomes) {
}
