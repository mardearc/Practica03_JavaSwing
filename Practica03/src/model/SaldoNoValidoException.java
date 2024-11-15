package model;

public class SaldoNoValidoException extends Exception{

	
	public SaldoNoValidoException() {
		
	}
	
	public SaldoNoValidoException(String mensaje){
		super(mensaje);
	}

}
