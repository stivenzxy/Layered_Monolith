package App2ComponenteTextoPlano;

import Interfaces.Documento;
import Interfaces.FabricaDocumento;

public class DocumentoTextoPlanoFactory implements FabricaDocumento {
    private static FabricaDocumento instance;

    private DocumentoTextoPlanoFactory() {}

    public static FabricaDocumento getInstance() {
        if (instance == null) {
            instance = new DocumentoTextoPlanoFactory();
        }
        return instance;
    }

    @Override
    public Documento crear() {
        return new DocumentoTextoPlano();
    }
}
