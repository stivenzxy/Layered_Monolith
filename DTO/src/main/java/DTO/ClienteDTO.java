package DTO;

public class ClienteDTO {
    public Long id;
    public String numeroDocumento;
    public String nombres;
    public String apellidos;
    public String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "--- Cliente ---" +
                "\n\tID:              " + id +
                "\n\tNro. Documento:  " + numeroDocumento +
                "\n\tNombres:         " + nombres +
                "\n\tApellidos:       " + apellidos +
                "\n\tEmail:           " + email +
                "\n---------------";
    }
}
