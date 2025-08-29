package DTO.Biblioteca;

public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private boolean disponible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Prestado";
        return String.format(
                "Libro[ID: %-3d | Título: %-30s | Autor: %-25s | Estado: %s]",
                id, titulo, autor, estado
        );
    }
}