package Biblioteca.Interfaces;

import Biblioteca.Entities.Libro;

import java.util.List;

public interface LibroRepository {
    String agregarLibro(Libro libro);
    boolean eliminarLibro(Long id);
    List<Libro> obtenerLibrosRegistrados();
}
