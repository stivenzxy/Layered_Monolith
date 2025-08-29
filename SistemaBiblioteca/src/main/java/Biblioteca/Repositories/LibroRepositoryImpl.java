package Biblioteca.Repositories;

import Biblioteca.DAO.LibroDAO;

import Biblioteca.Entities.Libro;
import Biblioteca.Interfaces.LibroRepository;

import java.util.List;

public class LibroRepositoryImpl implements LibroRepository {

    private final LibroDAO libroDAO;

    public LibroRepositoryImpl() {
        libroDAO = new LibroDAO();
    }

    @Override
    public String agregarLibro(Libro libro) {
        return libroDAO.agregarLibro(libro);
    }

    @Override
    public List<Libro> obtenerLibrosRegistrados() {
        return libroDAO.obtenerLibrosRegistrados();
    }
}
