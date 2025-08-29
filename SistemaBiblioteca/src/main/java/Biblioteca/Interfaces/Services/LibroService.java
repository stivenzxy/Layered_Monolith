package Biblioteca.Interfaces.Services;

import DTO.Biblioteca.LibroDTO;

import java.util.List;

public interface LibroService {
    String agregarLibro(LibroDTO libroDTO);
    List<LibroDTO> obtenerLibrosRegistrados();
}
