package Biblioteca.Mappers;

import Biblioteca.Entities.Prestamo;
import DTO.Biblioteca.PrestamoDTO;

public class PrestamoMapper {

    public static Prestamo dtoToEntity(PrestamoDTO dto) {
        if (dto == null) return null;
        Prestamo entidad = new Prestamo();
        entidad.setId(dto.getId());
        entidad.setLibroId(dto.getLibroId());
        entidad.setLectorId(dto.getLectorId());
        entidad.setFechaPrestamo(dto.getFechaPrestamo());
        entidad.setFechaDevolucion(dto.getFechaDevolucion());
        return entidad;
    }

    public static PrestamoDTO entityToDto(Prestamo entidad) {
        if (entidad == null) return null;
        PrestamoDTO dto = new PrestamoDTO();
        dto.setId(entidad.getId());
        dto.setLibroId(entidad.getLibroId());
        dto.setLectorId(entidad.getLectorId());
        dto.setFechaPrestamo(entidad.getFechaPrestamo());
        dto.setFechaDevolucion(entidad.getFechaDevolucion());
        return dto;
    }
}
