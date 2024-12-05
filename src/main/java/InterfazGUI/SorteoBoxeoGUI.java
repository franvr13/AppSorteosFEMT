package InterfazGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SorteoBoxeoGUI extends JFrame {
    private List<Participante> participantes;
    private List<Categoria> categorias;
    private JTextArea textArea;
    private JTextField participanteField;
    private JTextField categoriaField;

    public SorteoBoxeoGUI() {
        participantes = new ArrayList<>();
        categorias = new ArrayList<>();
        crearInterfaz();
    }

    private void crearInterfaz() {
        setTitle("Sorteo FEMT");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Barra del menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
        itemAcercaDe.addActionListener(e -> mostrarMensaje("Sorteo FEMT v1.0\nDesarrollado por [Francisco Villalba Roldán]"));
        menuAyuda.add(itemAcercaDe);
        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);

        // Panel de entradas
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        participanteField = new JTextField(15);
        categoriaField = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panelEntrada.add(new JLabel("Nombre del Participante:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panelEntrada.add(participanteField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelEntrada.add(new JLabel("Nombre de la Categoría:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panelEntrada.add(categoriaField, gbc);

        JButton agregarParticipanteButton = new JButton("Agregar Participante");
        JButton agregarCategoriaButton = new JButton("Agregar Categoría");
        JButton realizarSorteoButton = new JButton("Realizar Sorteo");

        // Acciones de los botones
        agregarParticipanteButton.addActionListener(e -> agregarParticipante());
        agregarCategoriaButton.addActionListener(e -> agregarCategoria());
        realizarSorteoButton.addActionListener(e -> realizarSorteo());

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panelEntrada.add(agregarParticipanteButton, gbc);
        gbc.gridy = 3;
        panelEntrada.add(agregarCategoriaButton, gbc);
        gbc.gridy = 4;
        panelEntrada.add(realizarSorteoButton, gbc);

        // Panel que muestra los resultados
        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new BorderLayout());
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panelResultados.add(scrollPane, BorderLayout.CENTER);

        // Agregar paneles a la ventana principal
        add(panelEntrada, BorderLayout.NORTH);
        add(panelResultados, BorderLayout.CENTER);
    }

    private void agregarParticipante() {
        String nombre = participanteField.getText().trim();
        if (!nombre.isEmpty()) {
            participantes.add(new Participante(nombre));
            mostrarMensaje("Participante agregado: " + nombre);
            participanteField.setText("");
        } else {
            mostrarMensaje("Por favor, ingresa un nombre válido.");
        }
    }

    private void agregarCategoria() {
        String nombre = categoriaField.getText().trim();
        if (!nombre.isEmpty()) {
            categorias.add(new Categoria(nombre));
            mostrarMensaje("Categoría agregada: " + nombre);
            categoriaField.setText("");
        } else {
            mostrarMensaje("Por favor, ingresa un nombre válido.");
        }
    }

    private void realizarSorteo() {
        if (participantes.isEmpty() || categorias.isEmpty()) {
            mostrarMensaje("Por favor, agrega participantes y categorías antes de realizar el sorteo.");
            return;
        }

        StringBuilder resultados = new StringBuilder("Resultados del Sorteo:\n");
        Collections.shuffle(participantes); // Mezclar participantes

        for (int i = 0; i < participantes.size(); i += 2) {
            if (i + 1 < participantes.size()) {
                resultados.append(participantes.get(i).getNombre())
                        .append(" vs ")
                        .append(participantes.get(i + 1).getNombre())
                        .append(" - Categoría: ")
                        .append(categorias.get(i % categorias.size()).getNombre())
                        .append("\n");
            }
        }

        textArea.setText(resultados.toString());
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SorteoBoxeoGUI ventana = new SorteoBoxeoGUI();
            ventana.setVisible(true);
        });
    }
}

class Participante {
    private String nombre;

    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

class Categoria {
    private String nombre;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}