package view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlCuenta;
import controller.Lista;
import model.Cuenta;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    // Creación de Paneles
    private JPanel panPrincipal;
    private PanLista panLista;
    private PanInsertar panInsertar;
    
    // Menú
    private JMenuBar menuBar;
    private JMenu mnuOpciones;
    private JMenuItem menuItemCargar, menuItemVerLista, menuItemInsertar, menuItemGuardar, menuItemVaciar, menuItemTest;

    // Lista de Cuentas
    public static Lista<Cuenta> cuentas = new Lista<>();

    // Controlador
    private CtrlCuenta ctrlCuenta;

    /**
     * Crear el Frame Principal.
     */
    public FrmPrincipal() {
        ctrlCuenta = new CtrlCuenta();

        // Configuración del Frame Principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 508, 323);
        setTitle("Practica 3 - Jesús al cuadrado");
        setResizable(false);
        setLocationRelativeTo(null);

        panPrincipal = new JPanel();
        panPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panPrincipal);

        // Configuración de Layout
        panPrincipal.setLayout(new CardLayout(0, 0));

        // Inicialización de Paneles
        panLista = new PanLista();
        panInsertar = new PanInsertar(cuentas, panLista);
        
        panPrincipal.add(panLista, "panLista");
        panPrincipal.add(panInsertar, "panInsertar");
        // Configuración del Menú
        addMenu();
        addListeners();
    }

    private void addMenu() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuItemVerLista = new JMenuItem("Ver Lista");
        menuBar.add(menuItemVerLista);
        
        menuItemInsertar = new JMenuItem("Insertar");
        menuBar.add(menuItemInsertar);

        mnuOpciones = new JMenu("Opciones");
        menuBar.add(mnuOpciones);

        menuItemCargar = new JMenuItem("Cargar");
        mnuOpciones.add(menuItemCargar);
        
        menuItemGuardar = new JMenuItem("Guardar");
        mnuOpciones.add(menuItemGuardar);
        
        menuItemVaciar = new JMenuItem("Vaciar");
        mnuOpciones.add(menuItemVaciar);
        
        menuItemTest = new JMenuItem("Test");
        mnuOpciones.add(menuItemTest);
    }

    private void addListeners() {
    	// Listener para "Ver Lista"
        menuItemVerLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panPrincipal.getLayout());
                cl.show(panPrincipal, "panLista");
            }
        });
    	
     // Listener para "Insertar"
        menuItemInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panPrincipal.getLayout());
                cl.show(panPrincipal, "panInsertar");
            }
        });
    	
        // Listener para "Cargar"
        menuItemCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cuentas = ctrlCuenta.leerFichero(); // Leer el fichero
                panLista.actualizarLista(cuentas); // Actualizar el panel con los nuevos datos
            }
        });
        
        // Listener para "Guardar"
        menuItemGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		ctrlCuenta.escribirFichero(cuentas); // Leer el fichero
            		JOptionPane.showMessageDialog(rootPane, "Archivo guardado");
            	}catch(Exception ex) {
            		JOptionPane.showMessageDialog(rootPane, "Error guardando archivo");
            	}
                
            }
        });
        
     // Listener para "Vaciar"
        menuItemVaciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cuentas = new Lista<Cuenta>(); // Leer el fichero
                panLista.actualizarLista(cuentas); // Actualizar el panel con los nuevos datos
            }
        });
    }
}
