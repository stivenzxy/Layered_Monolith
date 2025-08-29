package Biblioteca.Utils;

import java.util.ResourceBundle;

public class MysqlConfiguration {
    public static String getPropiedad(String clave){
        ResourceBundle recursoBundle = ResourceBundle.getBundle("MysqlConfiguration");
        return recursoBundle.getString(clave);
    }
}