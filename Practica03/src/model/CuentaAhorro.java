package model;

import java.util.GregorianCalendar;

public class CuentaAhorro extends Cuenta{

	private double interesAnual;
	private double rentabilidad;
	
	
	public CuentaAhorro(int numero, String titular, double saldo, double saldoMinimo, GregorianCalendar fechaApertura,
			double interesAnual, double rentabilidad) throws SaldoNoValidoException, NumeroNoValidoException {
		super(numero, titular, saldo, saldoMinimo, fechaApertura);
		setInteresAnual(interesAnual);
		setRentabilidad(rentabilidad);
	}
	
	public double getInteresAnual() {
		return interesAnual;
	}
	public void setInteresAnual(double interesAnual) {
		this.interesAnual = interesAnual;
	}
	public double getRentabilidad() {
		return rentabilidad;
	}
	public void setRentabilidad(double rentabilidad) {
		this.rentabilidad = rentabilidad;
	}

	@Override
	public String toString() {
		return super.toString() + "CuentaAhorro [interesAnual=" + interesAnual + ", rentabilidad=" + rentabilidad + "]";
	}

	
	
}
