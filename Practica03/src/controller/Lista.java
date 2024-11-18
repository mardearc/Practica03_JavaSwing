package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lista<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Node<E> inicio;

    public Lista() {
        this.inicio = null;
    }

    public void mostrarLista() {
        Node<E> aux = this.inicio;
        while (aux != null) {
            System.out.println(aux.getPrincipal().toString());
            aux = aux.getSiguiente();
        }
    }

    public void insertarNodo(E p) {
        Node<E> nuevoNodo = new Node<>(p);
        nuevoNodo.setSiguiente(this.inicio);
        this.inicio = nuevoNodo;
    }

    
    public Node<E> getInicio() {
        return inicio;
    }
    
    public List<E> obtenerElementos() {
        List<E> elementos = new ArrayList<>();
        Node<E> aux = this.inicio;
        while (aux != null) {
            elementos.add(aux.getPrincipal());
            aux = aux.getSiguiente();
        }
        return elementos;
    }
}
