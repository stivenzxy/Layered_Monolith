package Biblioteca.Mappers;

import Biblioteca.Entities.Lector;
import DTO.Biblioteca.LectorDTO;

public class LectorMapper {

    public static Lector dtoToEntity(LectorDTO dto) {
        if (dto == null) return null;
        Lector entidad = new Lector();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setEmail(dto.getEmail());
        return entidad;
    }

    public static LectorDTO entityToDto(Lector entidad) {
        if (entidad == null) return null;
        LectorDTO dto = new LectorDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setEmail(entidad.getEmail());
        return dto;
    }
}