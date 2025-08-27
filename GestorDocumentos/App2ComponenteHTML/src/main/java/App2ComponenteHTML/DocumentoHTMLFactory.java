package App2ComponenteHTML;

import Interfaces.Documento;
import Interfaces.FabricaDocumento;

public class DocumentoHTMLFactory implements FabricaDocumento {
    private static DocumentoHTMLFactory instance;

    private DocumentoHTMLFactory() {}

    public static DocumentoHTMLFactory getInstance() {
        if (instance == null) {
            instance = new DocumentoHTMLFactory();
        }
        return instance;
    }

    @Override
    public Documento crear() {
        return new DocumentoHTML();
    }
}
