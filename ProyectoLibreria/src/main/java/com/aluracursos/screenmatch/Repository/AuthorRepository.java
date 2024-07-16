package com.aluracursos.screenmatch.Repository;

import com.aluracursos.screenmatch.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Autor, Integer> {
    Autor findAuthorByNombreIgnoreCase(String nombre);
    List<Autor> findAuthorsByFechaNacimientoBeforeAndFechaFallecimientoAfterOrFechaFallecimientoIsNull(Integer fechaNacimiento, Integer fechaFallecimiento);
}

