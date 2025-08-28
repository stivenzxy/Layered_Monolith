package Biblioteca.Entities;

public class Lector {
    private Long id;
    private String nombre;
    private String email;

    public Lector() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", nombre completo='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
