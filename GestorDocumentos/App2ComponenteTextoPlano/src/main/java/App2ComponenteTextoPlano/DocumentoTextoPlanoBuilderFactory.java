package App2ComponenteTextoPlano;

import Interfaces.DocumentoBuilder;
import Interfaces.FabricaBuilder;

public class DocumentoTextoPlanoBuilderFactory implements FabricaBuilder {
    private static FabricaBuilder instance;

    private DocumentoTextoPlanoBuilderFactory() {}

    public static FabricaBuilder getInstance() {
        if (instance == null) {
            instance = new DocumentoTextoPlanoBuilderFactory();
        }
        return instance;
    }

    @Override
    public DocumentoBuilder crearBuilder() {
        return new DocumentoTextoPlanoBuilder();
    }
}
