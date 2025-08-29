package Biblioteca.Services;

import Biblioteca.DAO.LectorDAO;
import Biblioteca.Entities.Lector;
import Biblioteca.Entities.Libro;
import Biblioteca.Entities.Prestamo;
import Biblioteca.Interfaces.LectorRepository;
import Biblioteca.Interfaces.LibroRepository;
import Biblioteca.Interfaces.PrestamoRepository;
import Biblioteca.Interfaces.Services.PrestamoService;
import Biblioteca.Mappers.PrestamoMapper;
import DTO.Biblioteca.PrestamoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final LectorRepository lectorRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
                               LibroRepository libroRepository,
                               LectorRepository lectorRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.lectorRepository = lectorRepository;
    }

    public PrestamoDTO crearPrestamo(PrestamoDTO prestamoDTO) {
        prestamoDTO.setFechaPrestamo(LocalDate.now());

        Prestamo prestamoEntidad = PrestamoMapper.dtoToEntity(prestamoDTO);

        prestamoRepository.crearPrestamo(prestamoEntidad);

        return PrestamoMapper.entityToDto(prestamoEntidad);
    }

    public boolean devolverPrestamo(Long prestamoId) {
        return prestamoRepository.devolverPrestamo(prestamoId, LocalDate.now());
    }

    public List<PrestamoDTO> obtenerPrestamosRegistrados() {
        List<Prestamo> prestamos = prestamoRepository.obtenerPrestamosRegistrados();

        Map<Long, Libro> mapaLibros = libroRepository.obtenerLibrosRegistrados().stream()
                .collect(Collectors.toMap(Libro::getId, libro -> libro));

        Map<Long, Lector> mapaLectores = lectorRepository.obtenerLectoresRegistrados().stream()
                .collect(Collectors.toMap(Lector::getId, lector -> lector));

        return prestamos.stream().map(prestamo -> {
            PrestamoDTO dto = PrestamoMapper.entityToDto(prestamo);

            Libro libro = mapaLibros.get(prestamo.getLibroId());
            Lector lector = mapaLectores.get(prestamo.getLectorId());

            if (libro != null) dto.setTituloLibro(libro.getTitulo());
            if (lector != null) dto.setNombreLector(lector.getNombre());

            return dto;
        }).collect(Collectors.toList());
    }
}