package com.aluracursos.screenmatch.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDatos implements InterfazConveritDatos {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
