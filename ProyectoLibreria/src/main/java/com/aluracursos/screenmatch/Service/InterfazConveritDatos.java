package com.aluracursos.screenmatch.Service;

public interface InterfazConveritDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}

