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
import controller.Node;
import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;
import model.NumeroNoValidoException;
import model.SaldoNoValidoException;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    // Creación de Paneles
    private JPanel panPrincipal;
    private PanLista panLista;
    private PanInsertar panInsertar;
    private PanCuenta panCuenta;
    
    // Menú
    private JMenuBar menuBar;
    private JMenu mnuOpciones;
    private JMenuItem menuItemCargar, menuItemVerLista, menuItemInsertar, mnuItemCuenta, menuItemGuardar, menuItemVaciar, menuItemTest, mnuItemOrdenarLista, mnuItemOrdenarCollection;

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
        panCuenta = new PanCuenta();
        panInsertar = new PanInsertar(cuentas, panLista, panCuenta);
        
        panPrincipal.add(panLista, "panLista");
        panPrincipal.add(panInsertar, "panInsertar");
        panPrincipal.add(panCuenta, "panCuenta");
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
        
        mnuItemCuenta = new JMenuItem("Ver Cuenta");
        menuBar.add(mnuItemCuenta);

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
        
        mnuItemOrdenarLista = new JMenuItem("Ordenar Lista");
        mnuOpciones.add(mnuItemOrdenarLista);
        
        mnuItemOrdenarCollection = new JMenuItem("Ordenar Collection");
        mnuOpciones.add(mnuItemOrdenarCollection);
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
    	
     // Listener para "Ver Cuenta"
        mnuItemCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (panPrincipal.getLayout());
                cl.show(panPrincipal, "panCuenta");
            }
        });
        // Listener para "Cargar"
        menuItemCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cuentas = ctrlCuenta.leerFichero(); // Leer el fichero
                panLista.actualizarLista(cuentas); // Actualizar el panel con los nuevos datos
                panCuenta.actualizarLista(cuentas); // Actualizar el panel con los nuevos datos
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
        
        //Listener para "Test"
        menuItemTest.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					CuentaAhorro ca1 = new CuentaAhorro(601, "ca1", 2000, 4000, new GregorianCalendar(), 2, 2);
					CuentaAhorro ca2 = new CuentaAhorro(602, "ca2", 2000, 4000, new GregorianCalendar(), 2, 2);
					CuentaCorriente cc1 = new CuentaCorriente(603, "cc1", 2000, 4000, new GregorianCalendar(), 2, "mensual");
					CuentaCorriente cc2 = new CuentaCorriente(604, "cc2", 2000, 4000, new GregorianCalendar(), 2, "mensual");
					cuentas = new Lista<Cuenta>();
					cuentas.insertarNodo(cc2);
					cuentas.insertarNodo(cc1);
					cuentas.insertarNodo(ca2);
					cuentas.insertarNodo(ca1);
					panLista.actualizarLista(cuentas);
					panCuenta.actualizarLista(cuentas);
					
				} catch (SaldoNoValidoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumeroNoValidoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        //Listener para ordenar por Lista<Cuenta>
        mnuItemOrdenarLista.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 // Generar las cuentas
                cuentas = CtrlCuenta.generarCuentas(10000);

                // Ordenar la lista
                ordenarLista(cuentas);

                // Actualizar los paneles con la lista ordenada
                panLista.actualizarLista(cuentas);
                panCuenta.actualizarLista(cuentas);
        	}
        });
        
      //Listener para ordenar por Collection
        mnuItemOrdenarCollection.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		List<Cuenta> listaCuentas = new ArrayList<>();
        		cuentas = CtrlCuenta.generarCuentas(10000);
        		listaCuentas = cuentas.obtenerElementos();
        		listaCuentas.sort(Comparator.comparingInt(Cuenta::getNumero));
        		panLista.actualizarListaCollection(listaCuentas);
        	}
        });
    }
    
    private void ordenarLista(Lista<Cuenta> lista) {
        if (lista.getInicio() == null || lista.getInicio().getSiguiente() == null) {
            // La lista ya está ordenada (vacía o con un solo elemento)
            return;
        }

        boolean huboIntercambio;
        do {
            Node<Cuenta> actual = lista.getInicio();
            Node<Cuenta> siguiente = actual.getSiguiente();
            huboIntercambio = false;

            while (siguiente != null) {
                // Comparar los números de las cuentas
                if (actual.getPrincipal().getNumero() > siguiente.getPrincipal().getNumero()) {
                    // Intercambiar los valores de los nodos
                    Cuenta temp = actual.getPrincipal();
                    actual.setPrincipal(siguiente.getPrincipal());
                    siguiente.setPrincipal(temp);
                    huboIntercambio = true;
                }
                // Avanzar en la lista
                actual = siguiente;
                siguiente = siguiente.getSiguiente();
            }
        } while (huboIntercambio);
    }
}
