package Controllers.SistemaBiblioteca;

import Biblioteca.Interfaces.Services.LibroService;
import DTO.Biblioteca.LibroDTO;

import java.util.List;

public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    public String agregarLibro(LibroDTO libroDTO) {
        return libroService.agregarLibro(libroDTO);
    }

    public List<LibroDTO> obtenerLibrosRegistrados() {
        return libroService.obtenerLibrosRegistrados();
    }
}