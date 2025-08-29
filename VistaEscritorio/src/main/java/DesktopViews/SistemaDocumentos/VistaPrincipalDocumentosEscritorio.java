package DesktopViews.SistemaDocumentos;

import Controllers.SistemaDocumentosController;
import DesktopViews.SistemaDocumentos.Actions.*;
import Interfaces.Documento;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipalDocumentosEscritorio extends JPanel implements DocumentosDesktopView {
    private final SistemaDocumentosController controller;

    private JTextField txtFormato, txtContenido, txtTitulo, txtImagenUrl, txtFirma;
    private JComboBox<String> comboFormato;
    private JTextArea txtAreaContenido;
    private JCheckBox chkDocumentoExtendido;

    private Action crearDocumentoSimpleAction;
    private Action crearDocumentoExtendidoAction;
    private Action limpiarFormularioAction;

    public VistaPrincipalDocumentosEscritorio(SistemaDocumentosController controller) {
        this.controller = controller;
        crearActions();
        initComponents();
        initListeners();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Panel principal con formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Crear Documento"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Checkbox para documento extendido
        chkDocumentoExtendido = new JCheckBox("Documento Extendido (con Builder)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelFormulario.add(chkDocumentoExtendido, gbc);

        // Formato del documento
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelFormulario.add(new JLabel("Formato:"), gbc);
        
        comboFormato = new JComboBox<>(new String[]{"pdf", "html", "texto"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFormulario.add(comboFormato, gbc);

        // Título (solo para documentos extendidos)
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Título:"), gbc);
        
        txtTitulo = new JTextField(20);
        txtTitulo.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelFormulario.add(txtTitulo, gbc);

        // Contenido
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(new JLabel("Contenido:"), gbc);
        
        txtAreaContenido = new JTextArea(4, 20);
        txtAreaContenido.setLineWrap(true);
        txtAreaContenido.setWrapStyleWord(true);
        JScrollPane scrollContenido = new JScrollPane(txtAreaContenido);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelFormulario.add(scrollContenido, gbc);

        // URL de imagen (solo para documentos extendidos)
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelFormulario.add(new JLabel("URL Imagen:"), gbc);
        
        txtImagenUrl = new JTextField(20);
        txtImagenUrl.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelFormulario.add(txtImagenUrl, gbc);

        // Firma (solo para documentos extendidos)
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelFormulario.add(new JLabel("Firma:"), gbc);
        
        txtFirma = new JTextField(20);
        txtFirma.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelFormulario.add(txtFirma, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(new JButton(crearDocumentoSimpleAction));
        panelBotones.add(new JButton(crearDocumentoExtendidoAction));
        panelBotones.add(new JButton(limpiarFormularioAction));

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.CENTER);

        // Panel inferior para mostrar resultados
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createTitledBorder("Información del Documento"));
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void initListeners() {
        chkDocumentoExtendido.addActionListener(e -> {
            boolean esExtendido = chkDocumentoExtendido.isSelected();
            txtTitulo.setEnabled(esExtendido);
            txtImagenUrl.setEnabled(esExtendido);
            txtFirma.setEnabled(esExtendido);
            
            if (esExtendido) {
                crearDocumentoSimpleAction.setEnabled(false);
                crearDocumentoExtendidoAction.setEnabled(true);
            } else {
                crearDocumentoSimpleAction.setEnabled(true);
                crearDocumentoExtendidoAction.setEnabled(false);
            }
        });
    }

    private void crearActions() {
        crearDocumentoSimpleAction = new CrearDocumentoSimpleAction(this, controller);
        crearDocumentoExtendidoAction = new CrearDocumentoExtendidoAction(this, controller);
        limpiarFormularioAction = new LimpiarFormularioDocumentoAction(this);
        
        // Inicialmente solo documento simple está habilitado
        crearDocumentoExtendidoAction.setEnabled(false);
    }

    @Override
    public String getFormato() {
        return (String) comboFormato.getSelectedItem();
    }

    @Override
    public String getContenido() {
        return txtAreaContenido.getText();
    }

    @Override
    public String getTitulo() {
        return txtTitulo.getText();
    }

    @Override
    public String getImagenUrl() {
        return txtImagenUrl.getText();
    }

    @Override
    public String getFirma() {
        return txtFirma.getText();
    }

    @Override
    public void limpiarFormulario() {
        comboFormato.setSelectedIndex(0);
        txtAreaContenido.setText("");
        txtTitulo.setText("");
        txtImagenUrl.setText("");
        txtFirma.setText("");
        chkDocumentoExtendido.setSelected(false);
        txtTitulo.setEnabled(false);
        txtImagenUrl.setEnabled(false);
        txtFirma.setEnabled(false);
        crearDocumentoSimpleAction.setEnabled(true);
        crearDocumentoExtendidoAction.setEnabled(false);
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarDocumentoCreado(Documento documento) {
        String mensaje = "Documento creado exitosamente!\n\n";
        if (chkDocumentoExtendido.isSelected()) {
            mensaje += "Tipo: Documento Extendido\n";
            mensaje += "Formato: " + getFormato().toUpperCase() + "\n";
            mensaje += "Título: " + getTitulo() + "\n";
            mensaje += "Contenido: " + getContenido() + "\n";
            mensaje += "Imagen: " + getImagenUrl() + "\n";
            mensaje += "Firma: " + getFirma();
        } else {
            mensaje += "Tipo: Documento Simple\n";
            mensaje += "Formato: " + getFormato().toUpperCase() + "\n";
            mensaje += "Contenido: " + getContenido();
        }
        
        JOptionPane.showMessageDialog(this, mensaje, "Documento Creado", JOptionPane.INFORMATION_MESSAGE);
        limpiarFormulario();
    }
}
