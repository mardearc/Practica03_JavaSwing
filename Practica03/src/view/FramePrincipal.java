package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
	public FramePrincipal() {
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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInicio = new JPanelInsertar();
		contentPane.add(panelInicio, BorderLayout.CENTER);
		panelInicio.setLayout(null);
		
		
	}
}
