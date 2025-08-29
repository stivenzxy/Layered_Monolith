package Fachada.Mappers;

import DTO.Cliente.ClienteDTO;
import Entities.Cliente;

public class ClienteMapper {
    public static Cliente dtoToEntity(ClienteDTO clienteDTO) {

        if (clienteDTO == null) {
            return null;
        }

        Cliente entidad = Cliente.builder()
                .numeroDocumento(clienteDTO.getNumeroDocumento())
                .nombres(clienteDTO.getNombres())
                .apellidos(clienteDTO.getApellidos())
                .email(clienteDTO.getEmail())
                .build();

        if (clienteDTO.getId() != null) {
            entidad.setId(clienteDTO.getId());
        }

        return entidad;
    }

    public static ClienteDTO entityToDto(Cliente entidad) {
        if (entidad == null) {
            return null;
        }

        ClienteDTO dto = new ClienteDTO();
        dto.setId(entidad.getId());
        dto.setNumeroDocumento(entidad.getNumeroDocumento());
        dto.setNombres(entidad.getNombres());
        dto.setApellidos(entidad.getApellidos());
        dto.setEmail(entidad.getEmail());

        return dto;
    }
}
