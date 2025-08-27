package App2ComponentePDFAdaptado;

public class ComponentePdf {
    private static ComponentePdf instancia;
    private String contenido;

    private ComponentePdf() {}

    public static ComponentePdf getInstance() {
        if(instancia == null) {
            instancia = new ComponentePdf();
        }
        return instancia;
    }

    public void pdfFijaContenido(String contenido) {
        this.contenido = contenido;
    }

    public void pdfPrepararVisualizacion() {
        System.out.println("Preparando visualización PDF");
    }

    public void pdfRefrescar() {
        System.out.println("Contenido actualizado del PDF: " + contenido);
    }

    public void pdfFinalizarVisualizacion() {
        System.out.println("Finalizando visualización PDF");
    }

    public void pdfEnviarImpresora() {
        System.out.println("Enviando a impresora: " + contenido);
    }
}