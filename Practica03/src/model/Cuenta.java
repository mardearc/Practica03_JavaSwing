package model;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Cuenta implements Serializable{

	private int numero;
	private transient String titular;
	private double saldo;
	private double saldoMinimo;
	private GregorianCalendar fechaApertura;

	public Cuenta(int numero, String titular,  double saldoMinimo, double saldo, GregorianCalendar fechaApertura) {
		setNumero(numero);
		setTitular(titular);
		setSaldoMinimo(saldoMinimo);
		try {
			setSaldo(saldo);
		} catch (SaldoNoValidoException e) {
			System.out.println(e.getMessage());
		}
		
		setFechaApertura(fechaApertura);
	}

	public int getNumero() {

		return numero;
	}

	public void setNumero(int numero) {
		if (numero >= 1 && numero <= 1000) {
			this.numero = numero;
		} else {
			// throwException
		}

	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	public double getSaldoMinimo() {
		return saldoMinimo;
	}

	public void setSaldoMinimo(double saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) throws SaldoNoValidoException{
		if (saldo >= saldoMinimo) {
			this.saldo = saldo;
		} else {
			throw new SaldoNoValidoException();
		}
		
	}

	public GregorianCalendar getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(GregorianCalendar fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@Override
	public String toString() {
		return "Cuenta [numero=" + numero +  ", saldoMinimo=" + saldoMinimo + ", saldo=" + saldo + ", fechaApertura="
				+ fechaApertura.getTime();
	}
	
	

}
