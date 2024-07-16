package com.aluracursos.screenmatch;


import com.aluracursos.screenmatch.Principal.Principal;
import com.aluracursos.screenmatch.Repository.AuthorRepository;
import com.aluracursos.screenmatch.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrosApplication implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(LibrosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(authorRepository, bookRepository);
        principal.Menu();
    }
}

