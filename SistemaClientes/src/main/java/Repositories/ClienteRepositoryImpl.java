package Repositories;

import DAO.ClienteDAO;
import Entities.Cliente;
import Interfaces.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteDAO clienteDAO;

    public ClienteRepositoryImpl() {
        clienteDAO = new ClienteDAO();
    }

    @Override
    public String crear(Cliente cliente) {
        return clienteDAO.crear(cliente);
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long id) {
        return Optional.ofNullable(clienteDAO.obtenerPorId(id));
    }

    @Override
    public List<Cliente> obtenerTodos() {
       return clienteDAO.obtenerTodos();
    }

    @Override
    public String actualizar(Cliente cliente) {
        return clienteDAO.actualizar(cliente);
    }

    @Override
    public boolean eliminar(Long id) {
        return clienteDAO.eliminar(id);
    }
}
