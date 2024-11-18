package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Lista;
import controller.Node;
import model.Cuenta;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class PanLista extends JPanel {

    private static final long serialVersionUID = 1L;
    
    // CREACIÓN DEL JLIST
    private JList<Cuenta> list;

    /**
     * Crear el panel.
     */
    public PanLista(Lista<Cuenta> cuentas) {
        // Establecer el layout
        setLayout(null);

        // Inicializar el modelo de lista
        DefaultListModel<Cuenta> modelo = new DefaultListModel<>();
        Node<Cuenta> actual = cuentas.getInicio();

        // Rellenar el modelo con los datos de la lista enlazada
        while (actual != null) {
            modelo.addElement(actual.getPrincipal());
            actual = actual.getSiguiente();
        }

        // Crear e inicializar el JList
        list = new JList<>(modelo);
        list.setBounds(10, 10, 280, 200);

        // Añadir JList dentro de un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 10, 280, 200);
        add(scrollPane);
    }
}
