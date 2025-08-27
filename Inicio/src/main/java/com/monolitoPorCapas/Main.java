package com.monolitoPorCapas;

import ConsoleViews.SistemaClientes.VistaClientesConsola;
import DesktopViews.SistemaClientes.VistaClientesEscritorio;
import Factories.SistemaClientesFactory;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando la interfaz gráfica de escritorio...");
        SwingUtilities.invokeLater(() -> {
            VistaClientesEscritorio v = SistemaClientesFactory.createClientesDesktopView();
            VistaPrincipalEscritorio mainFrame = new VistaPrincipalEscritorio(v);
            mainFrame.setVisible(true);
        });

        // 3. Iniciar la interfaz de consola en el hilo principal.
        //    Esto se ejecutará después de que la ventana de Swing haya sido "despachada".
        System.out.println("Iniciando la interfaz de consola...");
        VistaClientesConsola v = SistemaClientesFactory.createClientesConsoleView();
        VistaPrincipalConsola vistaPrincipalConsola = new VistaPrincipalConsola(v);
        vistaPrincipalConsola.iniciar();

        // Mensaje final cuando el bucle de la consola termina.
        System.out.println("La aplicación ha finalizado su ejecución en la consola.");
    }
}
