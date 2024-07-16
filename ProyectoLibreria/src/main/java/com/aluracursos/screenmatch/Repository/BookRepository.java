package com.aluracursos.screenmatch.Repository;

import com.aluracursos.screenmatch.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Libro, Integer> {
    Libro findBookByTitulo(String titulo);
    List<Libro> findBooksByLanguage(String language);
}

