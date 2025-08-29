package Fachada;

import DTO.ClienteDTO;
import Entities.Cliente;
import Fachada.Interfaces.SistemaClientesFacade;
import Fachada.Mappers.ClienteMapper;
import Interfaces.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FachadaClientes implements SistemaClientesFacade {

    private final ClienteRepository repository;

    public FachadaClientes(ClienteRepository repository) {
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
    public String guardarCliente(ClienteDTO clienteDTO) {
        if (clienteDTO.getId() == null) {
            Cliente nuevoCliente = ClienteMapper.dtoToEntity(clienteDTO);
            return repository.crear(nuevoCliente);
        } else {
           return actualizarCliente(clienteDTO);
        }
    }

    private String actualizarCliente(ClienteDTO clienteActualizadoDTO) {
        if (clienteActualizadoDTO.getId() == null) {
            throw new IllegalArgumentException("Para actualizar, el cliente debe tener un ID.");
        }

        Cliente entidadExistente = repository.obtenerPorId(clienteActualizadoDTO.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente con ID: " + clienteActualizadoDTO.getId()));

        if (clienteActualizadoDTO.getNumeroDocumento() != null && !clienteActualizadoDTO.getNumeroDocumento().trim().isEmpty()) {
            entidadExistente.setNumeroDocumento(clienteActualizadoDTO.getNumeroDocumento());
        }
        if (clienteActualizadoDTO.getNombres() != null && !clienteActualizadoDTO.getNombres().trim().isEmpty()) {
            entidadExistente.setNombres(clienteActualizadoDTO.getNombres());
        }
        if (clienteActualizadoDTO.getApellidos() != null && !clienteActualizadoDTO.getApellidos().trim().isEmpty()) {
            entidadExistente.setApellidos(clienteActualizadoDTO.getApellidos());
        }
        if (clienteActualizadoDTO.getEmail() != null && !clienteActualizadoDTO.getEmail().trim().isEmpty()) {
            entidadExistente.setEmail(clienteActualizadoDTO.getEmail());
        }
        return repository.actualizar(entidadExistente);
    }

    @Override
    public boolean eliminarCliente(Long id) {
        return repository.eliminar(id);
    }
}