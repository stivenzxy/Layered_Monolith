package Biblioteca.Repositories;

import Biblioteca.DAO.LibroDAO;
import Biblioteca.Entities.Lector;
import Biblioteca.Entities.Libro;
import Biblioteca.Interfaces.LectorRepository;
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
    public boolean eliminarLibro(Long id) {
        return libroDAO.eliminarLibro(id);
    }

    @Override
    public List<Libro> obtenerLibrosRegistrados() {
        return libroDAO.obtenerLibrosRegistrados();
    }
}
