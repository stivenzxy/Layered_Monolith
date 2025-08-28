package Entities;

public class Cliente {
    private Long id;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String email;

    private Cliente(Builder builder) {
        if (builder.numeroDocumento == null || builder.numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento de identificación del cliente es obligatorio.");
        }

        this.id = builder.id;
        this.numeroDocumento = builder.numeroDocumento;
        this.nombres = builder.nombres;
        this.apellidos = builder.apellidos;
        this.email = builder.email;
    }

    public static Builder builder() {
        return new Builder();
    }

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

    public static class Builder {
        private Long id;
        private String numeroDocumento;
        private String nombres;
        private String apellidos;
        private String email;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder numeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
            return this;
        }

        public Builder nombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder apellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}