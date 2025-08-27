package App2ComponenteHTML;

import Interfaces.Documento;

public class DocumentoHTML implements Documento {
    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando documento HTML" + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo documento HTML" + contenido + "\n");
    }
}
