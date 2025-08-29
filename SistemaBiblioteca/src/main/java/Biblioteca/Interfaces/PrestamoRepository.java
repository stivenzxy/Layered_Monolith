package Biblioteca.Interfaces;

import Biblioteca.Entities.Libro;
import Biblioteca.Entities.Prestamo;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository {
    String crearPrestamo(Prestamo prestamo);
    boolean devolverPrestamo(Long prestamoId, LocalDate fechaDevolucion);
    List<Prestamo> obtenerPrestamosRegistrados();
}
