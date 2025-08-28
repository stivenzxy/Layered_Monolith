import Biblioteca.DatabaseConfig.MysqlConnectionManager;

public class Main {
    public static void main(String[] args) {
        MysqlConnectionManager.obtenerInstancia().inicializarBaseDeDatos();
    }
}
