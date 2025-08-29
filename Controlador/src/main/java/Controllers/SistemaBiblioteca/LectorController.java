package Controllers.SistemaBiblioteca;

import Biblioteca.Interfaces.Services.LectorService;
import DTO.Biblioteca.LectorDTO;

import java.util.List;

public class LectorController {

    private final LectorService lectorService;

    public LectorController(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    public String agregarLector(LectorDTO lectorDTO) {
        return lectorService.agregarLector(lectorDTO);
    }

    public List<LectorDTO> obtenerLectoresRegistrados() {
        return lectorService.obtenerLectoresRegistrados();
    }
}