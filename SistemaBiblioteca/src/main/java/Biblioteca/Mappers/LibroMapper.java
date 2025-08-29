package Biblioteca.Mappers;

import Biblioteca.Entities.Libro;
import DTO.Biblioteca.LibroDTO;

public class LibroMapper {

    public static Libro dtoToEntity(LibroDTO dto) {
        if (dto == null) return null;
        Libro entidad = new Libro();
        entidad.setId(dto.getId());
        entidad.setTitulo(dto.getTitulo());
        entidad.setAutor(dto.getAutor());
        entidad.setDisponible(dto.isDisponible());
        return entidad;
    }

    public static LibroDTO entityToDto(Libro entidad) {
        if (entidad == null) return null;
        LibroDTO dto = new LibroDTO();
        dto.setId(entidad.getId());
        dto.setTitulo(entidad.getTitulo());
        dto.setAutor(entidad.getAutor());
        dto.setDisponible(entidad.isDisponible());
        return dto;
    }
}