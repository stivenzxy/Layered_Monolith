package App2ComponenteHTML;

import Interfaces.DocumentoBuilder;
import Interfaces.FabricaBuilder;

public class DocumentoHTMLBuilderFactory implements FabricaBuilder {
    private static FabricaBuilder instance;

    private DocumentoHTMLBuilderFactory() {}

    public static FabricaBuilder getInstance() {
        if (instance == null) {
            instance = new DocumentoHTMLBuilderFactory();
        }
        return instance;
    }

    @Override
    public DocumentoBuilder crearBuilder() {
        return new DocumentoHTMLBuilder();
    }
}
