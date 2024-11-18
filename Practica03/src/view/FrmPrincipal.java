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
	// CREACIÓN PANELES
	private JPanel panPrincipal, panLista, panCuenta, panInsertar;
	
	//CREACIÓN MENU
	private JMenuBar menuBar;
	
	//CREACIÓN JMENU
	private JMenu mnuOpciones;

	// CREACION MENUITEM
	private JMenuItem menuItemInsertar, menuItemVerLista, menuItemCargar, 
	menuItemGuardar, menuItemVaciar, menuItemTest;
	
	//LISTA DE CUENTAS
	private Lista<Cuenta> cuentas = new Lista<Cuenta>();

	// CREACIÓN CONTROLLER
	private CtrlCuenta ctrlCuenta;


	/**
	 * CREACIÓN FRAME PRINCIPAL
	 */
	public FrmPrincipal() {

		// INICIALIZACIÓN CONTROLLER EMPLEADO
		ctrlCuenta = new CtrlCuenta();

		// ATRIBUTOS FRAME PRINCIPAL
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 323);
		setTitle("Practica 2 - Ema, Recacha y Jesus");
		setResizable(false);
		setLocationRelativeTo(null);

		panPrincipal = new JPanel();
		panPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panPrincipal);

		// LLAMADA AL MÉTODO PARA INICIALIZAR LOS COMPONENTES
		addComponents();

	}

	private void addComponents() {
		panPrincipal.setLayout(new CardLayout(0, 0));
		// INCIALIZACION PANVER
		panLista = new PanLista(cuentas);
		panPrincipal.add(panLista, "panLista");

		// INICIALIZACION PALTA
		panCuenta = new PanCuenta(ctrlCuenta);
		panCuenta.setVisible(false);
		panPrincipal.add(panCuenta, "panCuenta");

		// INCICIALIZACION PANACERCADE
		panInsertar = new PanInsertar();
		panInsertar.setVisible(false);
		panPrincipal.add(panInsertar, "panInsertar");
		
		//MENU
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
		menuItemCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentas = ctrlCuenta.leerFichero();
			}
		});
	}

}