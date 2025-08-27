package Utils;

import java.util.ResourceBundle;

public class Configuracion {
    public static String getPropiedad(String clave){
        ResourceBundle recursoBundle = ResourceBundle.getBundle("H2Configuration");
        return recursoBundle.getString(clave);
    }
}
