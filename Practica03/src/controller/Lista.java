package controller;

public class Lista<E> {

	private Node<E> inicio;
	
	public Lista() {
		this.inicio = null;
	}
	
	public void mostrarLista() {
		Node<E> aux = this.inicio;
		while(aux != null) {
			System.out.println(aux.getPrincipal().toString());
			aux = aux.getSiguiente();
		}
	}
	
	public void insertarNodo(E p) {
		Node<E> nuevoNodo = new Node(p);
		nuevoNodo.setSiguiente(this.inicio);awa
		this.inicio = nuevoNodo;
	}
}
