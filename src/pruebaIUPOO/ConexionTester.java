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
        setSize(450,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new ConexionTester().setVisible(true);
        });
    }
}
