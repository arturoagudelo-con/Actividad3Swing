package pruebaIUPOO;

import javax.swing.*;
import java.awt.*;

public class ConexionTester extends JFrame {

    private JTextField URL;
    private JTextField Usuario;
    private JPasswordField Contrasena;
    private JButton conectar;
    private JTextArea areaResultado;

    public ConexionTester(){
        setTitle("Conexion a Base de datos");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));

        URL = new JTextField("jdbc:sqlite:inventario.db");
        Usuario = new JTextField();
        Contrasena = new JPasswordField();

        panelFormulario.add(new JLabel(" URL de Conexión:"));
        panelFormulario.add(URL);

        panelFormulario.add(new JLabel(" Nombre de Usuario:"));
        panelFormulario.add(Usuario);

        panelFormulario.add(new JLabel(" Contraseña:"));
        panelFormulario.add(Contrasena);

        conectar = new JButton("Probar conexión");

        areaResultado = new JTextArea(8, 20);
        areaResultado.setEditable(false);

        JScrollPane scrollResultado = new JScrollPane(areaResultado);

        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(conectar, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollResultado, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new ConexionTester().setVisible(true);
        });
    }
}
