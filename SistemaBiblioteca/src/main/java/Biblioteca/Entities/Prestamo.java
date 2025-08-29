package Biblioteca.Entities;

import java.time.LocalDate;

public class Prestamo {
    private Long id;
    private Long libroId;
    private Long lectorId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Long getLectorId() {
        return lectorId;
    }

    public void setLectorId(Long lectorid) {
        this.lectorId = lectorid;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", Id lector='" + lectorId + '\'' +
                ", Id Libro='" + libroId + '\'' +
                ", fecha prestamo='" + fechaPrestamo + '\'' +
                ", fecha devolucion='" + (fechaDevolucion != null ? fechaDevolucion : "No ha sido devuelto") + '\'' +
                '}';
    }
}
