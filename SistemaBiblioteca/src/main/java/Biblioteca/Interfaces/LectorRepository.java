package Biblioteca.Interfaces;

import Biblioteca.Entities.Lector;

import java.util.List;

public interface LectorRepository {
    String agregarLector(Lector lector);
    List<Lector> obtenerLectoresRegistrados();
}