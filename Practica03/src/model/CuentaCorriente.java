package model;

import java.util.GregorianCalendar;

public class CuentaCorriente extends Cuenta{

	private double comision;
	private String tipoComision;
	
	
	
	public CuentaCorriente(int numero, String titular, double saldoMinimo, double saldo, 
			GregorianCalendar fechaApertura, double comision, String tipoComision) throws SaldoNoValidoException, NumeroNoValidoException {
		super(numero, titular, saldoMinimo, saldo, fechaApertura);
		setComision(comision);
		setTipoComision(tipoComision);
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	public String getTipoComision() {
		return tipoComision;
	}
	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}
	
	@Override
	public String toString() {
		return super.toString() + " ,comision=" + comision + ", tipoComision=" + tipoComision + "]";
	}
	
	
	
}
