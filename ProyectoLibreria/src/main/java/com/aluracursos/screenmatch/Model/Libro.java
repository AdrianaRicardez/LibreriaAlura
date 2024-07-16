package com.aluracursos.screenmatch.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String titulo;

    private String nombreAuthor;
    private String language;
    private Integer cantidadDescargas;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Autor author;

    public Libro() {}

    public Libro(DatosLibro datosLibro, Autor author) {
        this.author = author;
        this.cantidadDescargas = datosLibro.cantidadDescargas();
        this.titulo = datosLibro.titulo();
        this.language = datosLibro.languages().get(0);
        this.nombreAuthor = author.getNombre();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreAuthor() {
        return nombreAuthor;
    }

    public void setNombreAuthor(String nombreAuthor) {
        this.nombreAuthor = nombreAuthor;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public Autor getAuthor() {
        return author;
    }

    public void setAuthor(Autor author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return """
                //////////////////////////////////////////////
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %d
                //////////////////////////////////////////////
                """.formatted(titulo, nombreAuthor, language, cantidadDescargas);
    }
}

