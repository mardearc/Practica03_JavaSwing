package controller;

import java.io.*;
import java.util.GregorianCalendar;


import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;
import model.NumeroNoValidoException;
import model.SaldoNoValidoException;

public class CtrlCuenta {

	private final String FILENAME = "cuentas.dat";
	private static CtrlCuenta mi_controlador;

	// Inicializar controlador
	public static CtrlCuenta GetControlador() {

		if (mi_controlador == null) {
			mi_controlador = new CtrlCuenta();
		}
		return mi_controlador;
	}

	public static boolean verificarFechaFutura(String fechaStr) {
		// Convertir la fecha del string a GregorianCalendar
		GregorianCalendar fecha = convertirAFecha(fechaStr);

		boolean esFutura = esFechaFutura(fecha);

		return esFutura; // Hay que pillarlo en el Panel Insertar
	}

	public static boolean esFechaFutura(GregorianCalendar fecha) {
		// Comprobar fecha futura
		GregorianCalendar fechaActual = new GregorianCalendar();

		return fecha.after(fechaActual);
	}

	public static GregorianCalendar convertirAFecha(String fechaStr) {
		String[] partes = fechaStr.split("-");
		if (partes.length != 3) {
			throw new IllegalArgumentException("Formato de fecha inválido. Use YYYY-MM-DD.");
		}

		try {
			int anio = Integer.parseInt(partes[0]);
			int mes = Integer.parseInt(partes[1]) - 1;
			int dia = Integer.parseInt(partes[2]);
			return new GregorianCalendar(anio, mes, dia);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("La fecha contiene valores no numéricos.");
		}
	}

	public void escribirFichero(Lista<Cuenta> cuentas) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
			oos.writeObject(cuentas);
			System.out.println("Cuentas guardadas correctamente en " + FILENAME);
		} catch (IOException e) {
			System.err.println("Error al escribir en el fichero: " + e.getMessage());
		}
	}

	public Lista<Cuenta> leerFichero() {
		Lista<Cuenta> cuentas = new Lista<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
			cuentas = (Lista<Cuenta>) ois.readObject();
			System.out.println("Cuentas leídas correctamente desde " + FILENAME);
		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado: " + FILENAME);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer del fichero: " + e.getMessage());
		}
		return cuentas;
	}

	public static Lista<Cuenta> generarCuentas(int n) {
		Lista<Cuenta> lista = new Lista<>();

		for (int i = 0; i < n; i++) {
			double tipo = Math.random(); // Determina si será CuentaAhorro o CuentaCorriente
			int numero = (int) (Math.random() * 1000) + 1; // Genera un número aleatorio para el número de cuenta
			String titular = "Titular" + (i + 1); // Genera un titular único
			double saldoMinimo = Math.random() * 500; // Genera un saldo mínimo aleatorio entre 0 y 500
			double saldo = Math.random() * 10000 + saldoMinimo; // Genera un saldo aleatorio entre 0 y 10000

			if (tipo < 0.5) {
				// Crear CuentaAhorro
				try {
					double interesAnual = Math.random() * 10; // Genera un interés anual aleatorio
					double rentabilidad = Math.random() * 1000; // Genera una rentabilidad aleatoria
					CuentaAhorro ca = new CuentaAhorro(numero, titular, saldoMinimo, saldo, new GregorianCalendar(),
							interesAnual, rentabilidad);
					lista.insertarNodo(ca); // Inserta la cuenta en la lista
				} catch (SaldoNoValidoException | NumeroNoValidoException e) {
					e.printStackTrace();
				}
			} else {
				// Crear CuentaCorriente
				try {
					double comision = Math.random() * 50; // Genera una comisión aleatoria
					String tipoComision = "Tipo" + (i % 3 + 1); // Genera un tipo de comisión pseudoaleatorio
					CuentaCorriente cc = new CuentaCorriente(numero, titular, saldoMinimo, saldo,
							new GregorianCalendar(), comision, tipoComision);
					lista.insertarNodo(cc); // Inserta la cuenta en la lista
				} catch (SaldoNoValidoException | NumeroNoValidoException e) {
					e.printStackTrace();
				}
			}
		}

		return lista;
	}
	
	
}
