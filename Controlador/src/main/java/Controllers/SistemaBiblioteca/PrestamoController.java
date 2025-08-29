package Controllers.SistemaBiblioteca;

import Biblioteca.Interfaces.Services.PrestamoService;
import DTO.Biblioteca.PrestamoDTO;

import java.util.List;

public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    public PrestamoDTO crearPrestamo(PrestamoDTO prestamoDTO) {
        return prestamoService.crearPrestamo(prestamoDTO);
    }

    public boolean devolverPrestamo(Long prestamoId) {
        return prestamoService.devolverPrestamo(prestamoId);
    }

    public List<PrestamoDTO> obtenerPrestamosRegistrados() {
        return prestamoService.obtenerPrestamosRegistrados();
    }
}
