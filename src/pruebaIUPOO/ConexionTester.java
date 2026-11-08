package pruebaIUPOO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

        conectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = URL.getText();
                String usuario = Usuario.getText();

                String contrasena = new String(Contrasena.getPassword());

                areaResultado.setText("Estado conexion");
                areaResultado.setForeground(Color.BLUE);

                probarConexionBD(url, usuario, contrasena);

            }
        });



    }

    private void probarConexionBD(String url, String usuario, String contrasenia) {
        try {

            if (url.contains("sqlite")) {
                Class.forName("org.sqlite.JDBC");
            } else if (url.contains("mysql")) {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }


            try (Connection conn = DriverManager.getConnection(url, usuario, contrasenia)) {


                if (conn != null && !conn.isClosed()) {
                    areaResultado.setText("Conexión exitosa");
                    areaResultado.setForeground(Color.GREEN); // Texto en Verde
                }

            }
        } catch (ClassNotFoundException ex) {

            areaResultado.setText("Fallo de conexión\nError: No se encontró el Driver de la BD.\n" + ex.getMessage());
            areaResultado.setForeground(Color.RED); // Texto en Rojo

        } catch (SQLException ex) {
            areaResultado.setText("Fallo de conexión\nError técnico: " + ex.getMessage());
            areaResultado.setForeground(Color.RED); // Texto en Rojo
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new ConexionTester().setVisible(true);
        });
    }
}
