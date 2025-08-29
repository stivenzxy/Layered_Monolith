package Biblioteca.Services;

import Biblioteca.Entities.Libro;
import Biblioteca.Interfaces.LibroRepository;
import Biblioteca.Interfaces.Services.LibroService;
import Biblioteca.Mappers.LibroMapper;
import DTO.Biblioteca.LibroDTO;

import java.util.List;
import java.util.stream.Collectors;

public class LibroServiceImpl implements LibroService {

    private final LibroRepository repository;

    public LibroServiceImpl(LibroRepository repository) {
        this.repository = repository;
    }

    public String agregarLibro(LibroDTO libroDTO) {
        Libro libroEntidad = LibroMapper.dtoToEntity(libroDTO);

        if (libroEntidad.getTitulo() == null || libroEntidad.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío.");
        }
        return repository.agregarLibro(libroEntidad);
    }

    public List<LibroDTO> obtenerLibrosRegistrados() {
        List<Libro> listaEntidades = repository.obtenerLibrosRegistrados();

        return listaEntidades.stream()
                .map(LibroMapper::entityToDto)
                .collect(Collectors.toList());
    }
}