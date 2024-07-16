package com.aluracursos.screenmatch.Principal;

import com.aluracursos.screenmatch.Model.*;
import com.aluracursos.screenmatch.Service.ConversorDatos;
import com.aluracursos.screenmatch.Repository.AuthorRepository;
import com.aluracursos.screenmatch.Repository.BookRepository;
import com.aluracursos.screenmatch.Service.ConsumoApi;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Principal {
    private Integer opcion = -1;
    private Boolean iniciar = true;
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConversorDatos conversor = new ConversorDatos();
    private final Scanner teclado = new Scanner(System.in);
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final String URL_ROOT = "https://gutendex.com/books/?search=";

    public Principal(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void Menu(){
        String menu = """
                --------------------------------------------
                Elija la opción a través de un número:
                1 - Búsqueda de libro por titulo,  se registra
                2 - Lista de libros registrados
                3 - Listar de libros por autor
                4 - Listar autores vivos en un determinado año
                5 - Listar de libros por idioma original
                0 - Salir.
                --------------------------------------------
                """;

        while (iniciar){
            try{
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion){
                    case 1:
                        agregarLibro();
                        break;
                    case 2:
                        listaDeLibrosRegistrados();
                        break;
                    case 3:
                        listaDeAutoresRegistrados();
                        break;
                    case 4:
                        listaDeAutoresVivos();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 0:
                        iniciar = false;
                        System.out.println("Terminando proceso...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e){
                teclado.nextLine();
                System.out.println("Ingrese una opción de número válida " + e.getMessage());
            }
        }
    }

    private DatosResult busquedaLibro(){
        System.out.println("Ingresa el libro que deseas buscar");
        String nombreLibro = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_ROOT + nombreLibro.replace(" ", "+"));
        return conversor.obtenerDatos(json, DatosResult.class);
    }

    private void agregarLibro() {
        DatosResult nuevabusqueda = busquedaLibro();

        if (nuevabusqueda.datosLibros().size() > 0) {
            DatosLibro datosLibro = nuevabusqueda.datosLibros().get(0);
            DatosAutor datosAuthor = datosLibro.datosAutors().get(0);
            var tituloDeLibro = bookRepository.findBookByTitulo(datosLibro.titulo());
            if (tituloDeLibro != null) {
                System.out.println("El libro ya existe, el libro ya esta registrado");
            } else {
                var authorBook = authorRepository.findAuthorByNombreIgnoreCase(datosAuthor.nombreAuthor());
                Libro book;
                if (authorBook != null) {
                    book = new Libro(datosLibro, authorBook);
                } else {
                    Autor autor = new Autor(datosAuthor);
                    authorRepository.save(autor);
                    book = new Libro(datosLibro, autor);
                }
                bookRepository.save(book);
                System.out.println("--------- SAVED ---------");
                System.out.println(book);
            }
        } else {
            System.out.println("El libro no existe");
        }
    }

    private void listaDeLibrosRegistrados() {
        List<Libro> libros = bookRepository.findAll();
        if (libros.size() > 0) {
            libros.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron libros registrados.");
        }
    }

    private void listaDeAutoresRegistrados() {
        List<Autor> authors = authorRepository.findAll();
        if (authors.size() > 0) {
            authors.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron autores registrados.");
        }
    }

    private void listaDeAutoresVivos() {
        System.out.println("Ingrese el año en el cual desea buscar autores vivos:");
        Integer year = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autoresVivos = authorRepository.findAuthorsByFechaNacimientoBeforeAndFechaFallecimientoAfterOrFechaFallecimientoIsNull(year, year);
        if (autoresVivos.size() > 0) {
            autoresVivos.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron autores vivos en ese año.");
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Seleccione el idioma:");
        System.out.println("1 - Español(es)\n2 - Inglés(en)\n3 - Portugués(pt)\n4 - Francés(fr)\n5 - Italiano(it)\n6 - Japonés(ja)\n7 - Chino(zh)\n8 - Coreano(ko)");
        Integer idioma = teclado.nextInt();
        teclado.nextLine();
        Idiomas lenguaje = Idiomas.fromString(Idiomas.values()[idioma - 1].getLanguage());
        List<Libro> books = bookRepository.findBooksByLanguage(lenguaje.getLanguage());
        if (books.size() > 0) {
            books.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron libros en el idioma.");
        }
    }
}
