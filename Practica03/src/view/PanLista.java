package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.CtrlCuenta;
import controller.Lista;
import controller.Node;
import model.Cuenta;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class PanLista extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JList<Cuenta> list;
    private DefaultListModel<Cuenta> modelo;
    
    CtrlCuenta ctrlCuenta = CtrlCuenta.GetControlador();

    /**
     * Crear el panel.
     */
    public PanLista() {
        // Establecer el layout
        setLayout(null);

        addComponents();
    }

	private void addComponents() {
		// Inicializar el modelo y el JList
        modelo = new DefaultListModel<>();
        list = new JList<>(modelo);
        list.setBounds(10, 10, 280, 200);

        // Añadir JList dentro de un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 10, 280, 200);
        add(scrollPane);
	}

    /**
     * Método para actualizar los datos en el JList.
     */
    public void actualizarLista(Lista<Cuenta> cuentas) {
        modelo.clear(); // Limpiar el modelo actual
        Node<Cuenta> actual = cuentas.getInicio();

        // Añadir los elementos de la lista al modelo
        while (actual != null) {
            modelo.addElement(actual.getPrincipal());
            actual = actual.getSiguiente();
        }
    }

	public void actualizarListaCollection(List<Cuenta> listaCuentas) {
			modelo.addAll(listaCuentas);
	}
}
