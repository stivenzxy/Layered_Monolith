package Biblioteca.Interfaces.Services;

import DTO.Biblioteca.LectorDTO;

import java.util.List;

public interface LectorService {
    String agregarLector(LectorDTO lectorDTO);
    List<LectorDTO> obtenerLectoresRegistrados();
}
