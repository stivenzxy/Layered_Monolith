package Biblioteca.Interfaces.Services;

import DTO.Biblioteca.PrestamoDTO;

import java.util.List;

public interface PrestamoService {
    PrestamoDTO crearPrestamo(PrestamoDTO prestamoDTO);
    boolean devolverPrestamo(Long prestamoId);
    List<PrestamoDTO> obtenerPrestamosRegistrados();
}
