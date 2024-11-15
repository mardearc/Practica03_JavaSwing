package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlCuenta;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCargar, btnGuardar, btnVaciar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePrincipal(){

		// LLAMADA AL MÉTODO PARA INICIALIZAR LOS COMPONENTES
		addComponents();

		// LLAMADA AL MÉTODO PARA AÑADIR EVENTOS A LOS COMPONENTES
		addListeners();

	}

	private void addComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem menuItemInicio = new JMenuItem("Inicio");
		menuBar.add(menuItemInicio);
		
		JMenuItem menuItemInsertar = new JMenuItem("Insertar");
		menuBar.add(menuItemInsertar);
		
		JMenuItem menuItemVerLista = new JMenuItem("Ver Lista");
		menuBar.add(menuItemVerLista);
		
		JMenuItem menuItemVerCuenta = new JMenuItem("Ver Cuenta");
		menuBar.add(menuItemVerCuenta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCargar = new JButton("Cargar");
		
		btnCargar.setBounds(86, 206, 85, 21);
		contentPane.add(btnCargar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(196, 206, 85, 21);
		contentPane.add(btnGuardar);
		
		btnVaciar = new JButton("Vaciar");
		btnVaciar.setBounds(305, 206, 85, 21);
		contentPane.add(btnVaciar);

	}

	private void addListeners() {
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlCuenta ctrlCuenta = new CtrlCuenta();
				ctrlCuenta.leerFichero();
			}
		});

	}

}