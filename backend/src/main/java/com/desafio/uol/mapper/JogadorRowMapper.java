package com.desafio.uol.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.desafio.uol.model.JogadorModel;

@Component
public class JogadorRowMapper implements RowMapper<JogadorModel> {

    @Override
    public JogadorModel mapRow(ResultSet arg0, int arg1) throws SQLException {
        return new JogadorModel(
                arg0.getString("codinome"),
                arg0.getString("nome"),
                arg0.getString("email"),
                arg0.getString("telefone"),
                arg0.getString("grupo"));
    }

}
