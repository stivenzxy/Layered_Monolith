package Biblioteca.Services;

import Biblioteca.Entities.Lector;
import Biblioteca.Interfaces.LectorRepository;
import Biblioteca.Interfaces.Services.LectorService;
import Biblioteca.Mappers.LectorMapper;
import DTO.Biblioteca.LectorDTO;

import java.util.List;
import java.util.stream.Collectors;

public class LectorServiceImpl implements LectorService {

    private final LectorRepository repository;

    public LectorServiceImpl(LectorRepository repository) {
       this.repository = repository;
    }

    public String agregarLector(LectorDTO lectorDTO) {
        Lector lectorEntidad = LectorMapper.dtoToEntity(lectorDTO);
        return repository.agregarLector(lectorEntidad);
    }

    public List<LectorDTO> obtenerLectoresRegistrados() {
        List<Lector> listaEntidades = repository.obtenerLectoresRegistrados();

        return listaEntidades.stream()
                .map(LectorMapper::entityToDto)
                .collect(Collectors.toList());
    }
}