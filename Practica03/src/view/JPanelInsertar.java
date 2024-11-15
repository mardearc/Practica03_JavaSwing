package view;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelInsertar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public JPanelInsertar() {
		setLayout(null);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.setBounds(108, 191, 85, 21);
		add(btnCargar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(203, 191, 85, 21);
		add(btnGuardar);
		
		JButton btnVaciar = new JButton("Vaciar");
		btnVaciar.setBounds(300, 191, 85, 21);
		add(btnVaciar);

		
	}

}
