package controller;

import java.io.Serializable;

public class Node<E> implements Serializable {


    private Node<E> siguiente;
    private E principal;

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

    public E getPrincipal() {
        return principal;
    }

    public void setPrincipal(E p) {
        this.principal = p;
    }
}
