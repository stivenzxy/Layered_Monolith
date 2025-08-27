package App2ComponentePDF;

import Interfaces.DocumentoBuilder;
import Interfaces.FabricaBuilder;

public class DocumentoPdfBuilderFactory implements FabricaBuilder {
    private static FabricaBuilder instance;

    private DocumentoPdfBuilderFactory() {}

    public static FabricaBuilder getInstance() {
        if (instance == null) {
            instance = new DocumentoPdfBuilderFactory();
        }
        return instance;
    }

    @Override
    public DocumentoBuilder crearBuilder() {
        return new DocumentoPdfExtendidoBuilder();
    }
}
