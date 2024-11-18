package view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
    
    // Menú
    private JMenuBar menuBar;
    private JMenu mnuOpciones;
    private JMenuItem menuItemCargar, menuItemVerLista, menuItemGuardar, menuItemVaciar, menuItemTest;

    // Lista de Cuentas
    private Lista<Cuenta> cuentas = new Lista<>();

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
        setTitle("Practica 2 - Ema, Recacha y Jesus");
        setResizable(false);
        setLocationRelativeTo(null);

        panPrincipal = new JPanel();
        panPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panPrincipal);

        // Configuración de Layout
        panPrincipal.setLayout(new CardLayout(0, 0));

        // Inicialización de Paneles
        panLista = new PanLista();
        panPrincipal.add(panLista, "panLista");

        // Configuración del Menú
        addMenu();
        addListeners();
    }

    private void addMenu() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuItemVerLista = new JMenuItem("Ver Lista");
        menuBar.add(menuItemVerLista);
        
        menuItemVerLista = new JMenuItem("Insertar");
        menuBar.add(menuItemVerLista);

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
        // Listener para "Cargar"
        menuItemCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cuentas = ctrlCuenta.leerFichero(); // Leer el fichero
                panLista.actualizarLista(cuentas); // Actualizar el panel con los nuevos datos
            }
        });

        // Listener para "Ver Lista"
        menuItemVerLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panPrincipal.getLayout());
                cl.show(panPrincipal, "panLista");
            }
        });
        
        // Listener para "Guardar"
        menuItemGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlCuenta.escribirFichero(cuentas); // Leer el fichero
            }
        });
        
     // Listener para "Guardar"
        menuItemVaciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cuentas = new Lista<Cuenta>(); // Leer el fichero
                panLista.actualizarLista(cuentas); // Actualizar el panel con los nuevos datos
            }
        });
    }
}
