package Biblioteca.Interfaces;

import Biblioteca.Entities.Lector;

import java.util.List;

public interface LectorRepository {
    String agregarLector(Lector lector);
    boolean eliminarLector(Long id);
    List<Lector> obtenerLectoresRegistrados();
}