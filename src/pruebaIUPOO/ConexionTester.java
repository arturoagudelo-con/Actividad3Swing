package pruebaIUPOO;
// se hace la importacion de las librerias para poder realizar nuestra tabla, para agregar botones , cuadros para ingresar datos como usuario, contraseña
// luego tenemos las librerias para conectarnos con nuestra base de datos sql
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// aqui empezamos con nuesta clase principal la cual es ConexionTestar que hereda de la clase JFrame la cual contiene los modulos necesarios
// para la creacion de la ventana, botones, cuadros para el ingreso de datos

public class ConexionTester extends JFrame {
// en estas lineas agregamos los atributos que va a tener nuestro programa, los cuales son privados para proteger una modificacion de estos
// tenemos aqui la aplicacion de JTextfield los cuales sirven para ingresar en nuestra ventana los datos solicitados de tipo String
// el tipo Button para interactuar con el usuario para dar click y ver resultados
// el tipo JTextArea para ver los resultado de cuando demos click para la conexion a nuestra base de datos
    private JTextField URL;
    private JTextField Usuario;
    private JPasswordField Contrasena;
    private JButton conectar;
    private JTextArea areaResultado;
// aqui inicializamos nuestro constructor el cual va llevar todo el cuerpo de nuestra aplicacion, lo dejamos vacio para la construccion de nuestra
// ventana y cuadros de dialogos
    public ConexionTester(){
        // funcion para ponerle el titulo a nuestra ventana
        setTitle("Conexion a Base de datos");
        // funcion para ver el tamaño de nuestra ventana largo x ancho
        setSize(500,500);
        // funcion para poder cerrar nuestra venta despues de hacer la interaccion
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // esta funcion sirve para centrar nuestra venta en el centro
        setLocationRelativeTo(null);
        // aqui definimos el tamaño de nuestros cuadros dentro del panel principal
        setLayout(new BorderLayout(10, 10));
        // aqui es donde definimos el numero de filas, columnas y tamaños que van a tener nuestros cuadros en el panel
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        // en estas lineas inicializamos nuestros cuadros interactivos donde ingresamos la url, usuario, contraseña las cuales estan vacias por indicacion
        // de la actividad
        URL = new JTextField("jdbc:sqlite:inventario.db");
        Usuario = new JTextField();
        Contrasena = new JPasswordField();
        // aqui tenemos el ingreso de un JLabel donde ingresamos el nombre de nuestros cuadros
        // aqui con el add agregamos este atributo a nuestro panel principal, el cual lo llamamos
        // a traves de la variable que declaramos para heredar todos lo metodos de JPanel.
        panelFormulario.add(new JLabel(" URL de Conexión:"));
        panelFormulario.add(URL);

        panelFormulario.add(new JLabel(" Nombre de Usuario:"));
        panelFormulario.add(Usuario);

        panelFormulario.add(new JLabel(" Contraseña:"));
        panelFormulario.add(Contrasena);

        //aqui empezamos con la declaracion de nuestro boton donde ingresamos lo que va aparecer en pantalla al usar el programa
        conectar = new JButton("Probar conexión");
        // aqui establecemos el tamaño de nuestro campo de resultado, donde se va a ver el texto luego de que demos click en el boton
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
