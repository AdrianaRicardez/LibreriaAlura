package com.aluracursos.screenmatch.Model;

public enum Idiomas {
    SPANISH("es"),
    INGLISH("en"),
    PORTUGUESE("pt"),
    FRENCH("fr"),
    ITALIAN("it"),
    JAPANESE("ja"),
    CHINESE("zh"),
    KOREAN("ko");

    private final String language;

    Idiomas(String language) {
        this.language = language;
    }

    public static Idiomas fromString(String language) {
        for (Idiomas lengua : Idiomas.values()) {
            if (lengua.language.equalsIgnoreCase(language))
                return lengua;
        }
        throw new IllegalArgumentException(language + " is not supported");
    }

    public String getLanguage() {
        return language;
    }
}

