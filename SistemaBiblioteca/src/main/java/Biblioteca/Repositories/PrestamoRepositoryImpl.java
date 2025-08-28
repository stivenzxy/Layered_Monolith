package Biblioteca.Repositories;

import Biblioteca.DAO.PrestamoDAO;
import Biblioteca.Entities.Prestamo;
import Biblioteca.Interfaces.PrestamoRepository;

import java.time.LocalDate;
import java.util.List;

public class PrestamoRepositoryImpl implements PrestamoRepository {

    private final PrestamoDAO prestamoDAO;

    public PrestamoRepositoryImpl() {
        prestamoDAO = new PrestamoDAO();
    }

    @Override
    public String crearPrestamo(Prestamo prestamo) {
        return prestamoDAO.crearPrestamo(prestamo);
    }

    @Override
    public boolean devolverPrestamo(Long prestamoId, LocalDate fechaDevolucion) {
        return prestamoDAO.devolverPrestamo(prestamoId, fechaDevolucion);
    }

    @Override
    public List<Prestamo> obtenerPrestamosRegistrados() {
        return prestamoDAO.obtenerTodosLosPrestamos();
    }
}
