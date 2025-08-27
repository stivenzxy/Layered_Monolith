package App2ComponentePDF;

import Interfaces.Documento;
import Interfaces.FabricaDocumento;

public class DocumentoPdfFactory implements FabricaDocumento{
    private static FabricaDocumento instance;

    private DocumentoPdfFactory() {}

    public static FabricaDocumento getInstance() {
        if (instance == null) {
            instance = new DocumentoPdfFactory();
        }
        return instance;
    }

    @Override
    public Documento crear() {
        return new DocumentoPdf();
    }
}
