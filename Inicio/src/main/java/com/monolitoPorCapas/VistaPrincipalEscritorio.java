package com.monolitoPorCapas;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipalEscritorio extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JComboBox<String> menuDropdown;

    public VistaPrincipalEscritorio(JPanel clientesView, JPanel documentosView) {
        setTitle("Sistema de Gestión Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Dropdown de Navegación (Norte) ---
        JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMenu.add(new JLabel("Seleccionar Módulo:"));
        menuDropdown = new JComboBox<>(new String[]{"Clientes", "Documentos", "Reportes (Próximamente)"});
        panelMenu.add(menuDropdown);

        add(panelMenu, BorderLayout.NORTH);

        // --- Panel de Contenido Principal (Centro) ---
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(clientesView, "Clientes");
        contentPanel.add(documentosView, "Documentos");

        add(contentPanel, BorderLayout.CENTER);

        // Listener para cambiar de panel
        menuDropdown.addActionListener(e -> {
            String selectedView = (String) menuDropdown.getSelectedItem();
            cardLayout.show(contentPanel, selectedView);
        });
    }
}
