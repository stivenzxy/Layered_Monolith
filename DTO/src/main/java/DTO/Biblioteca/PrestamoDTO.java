package DTO.Biblioteca;

import java.time.LocalDate;

public class PrestamoDTO {
    private Long id;
    private Long libroId;
    private Long lectorId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    private String tituloLibro;
    private String nombreLector;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getLibroId() { return libroId; }

    public void setLibroId(Long libroId) { this.libroId = libroId; }

    public Long getLectorId() { return lectorId; }

    public void setLectorId(Long lectorId) { this.lectorId = lectorId; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }

    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }

    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getTituloLibro() { return tituloLibro; }

    public void setTituloLibro(String tituloLibro) { this.tituloLibro = tituloLibro; }

    public String getNombreLector() { return nombreLector; }

    public void setNombreLector(String nombreLector) { this.nombreLector = nombreLector; }

    @Override
    public String toString() {
        String devolucionStr = (fechaDevolucion != null) ? fechaDevolucion.toString() : "Pendiente";
        return String.format(
                "Préstamo[ID: %d | Libro: '%s' | Lector: '%s' | Prestado: %s | Devuelto: %s]",
                id, tituloLibro, nombreLector, fechaPrestamo.toString(), devolucionStr
        );
    }
}
