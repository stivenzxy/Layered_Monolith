package Fachada;

import DTO.ClienteDTO;
import Entities.Cliente;
import Fachada.Interfaces.SistemaClientesFacade;
import Fachada.Mappers.ClienteMapper;
import Interfaces.ClientesRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FachadaClientes implements SistemaClientesFacade {

    private final ClientesRepository repository;

    public FachadaClientes(ClientesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClienteDTO> obtenerListadoClientes() {
        List<Cliente> clientes = repository.obtenerTodos();

        return clientes.stream()
                .map(ClienteMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO buscarClientePorId(Long id) {
        return repository.obtenerPorId(id)
                .map(ClienteMapper::entityToDto)
                .orElse(null);
    }

    @Override
    public void guardarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.dtoToEntity(clienteDTO);
        repository.crear(cliente);
    }

    @Override
    public String actualizarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.dtoToEntity(clienteDTO);
        return repository.actualizar(cliente);
    }

    @Override
    public boolean eliminarCliente(Long id) {
        return repository.eliminar(id);
    }
}