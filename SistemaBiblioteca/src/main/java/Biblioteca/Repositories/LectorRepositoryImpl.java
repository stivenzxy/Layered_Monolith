package Biblioteca.Repositories;

import Biblioteca.DAO.LectorDAO;
import Biblioteca.Entities.Lector;
import Biblioteca.Interfaces.LectorRepository;

import java.util.List;

public class LectorRepositoryImpl implements LectorRepository {

    private final LectorDAO lectorDAO;

    public LectorRepositoryImpl() {
        lectorDAO = new LectorDAO();
    }

    @Override
    public String agregarLector(Lector lector) {
        return lectorDAO.agregarLector(lector);
    }

    @Override
    public boolean eliminarLector(Long id) {
        return lectorDAO.eliminarLector(id);
    }

    @Override
    public List<Lector> obtenerLectoresRegistrados() {
        return lectorDAO.obtenerLectoresRegistrados();
    }
}
