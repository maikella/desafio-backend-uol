package com.desafio.uol.dto;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record CodinomesDTO(@JacksonXmlProperty(localName = "codinome") @JacksonXmlElementWrapper(useWrapping = false) List<String> ligaDaJustica) {

}
