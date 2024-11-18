package model;

public class NumeroNoValidoException extends Exception{

	private static String MENSAJE = "Número no válido, debe estar entre 0 y 1000";
	
	public NumeroNoValidoException() {
		super(MENSAJE);
	}
	
}
