package controller;

public class Node<E> {
	private Node<E> siguiente;
	E principal;

	public Node(E p) {
		this.siguiente = null;
		this.principal = p;
	}

	public Node<E> getSiguiente() {
		return this.siguiente;
	}

	public void setSiguiente(Node<E> siguiente) {
		this.siguiente = siguiente;
	}

	public E getPricipal() {
		return principal;
	}

	public void setPrincipal(E p) {
		this.principal = p;
	}

}
