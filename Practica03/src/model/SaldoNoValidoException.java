package model;

public class SaldoNoValidoException extends Exception{

	private static String MENSAJE = "El saldo no puede ser inferior al minimo";
	
	public SaldoNoValidoException() {
		super(MENSAJE);
	}
	

}
