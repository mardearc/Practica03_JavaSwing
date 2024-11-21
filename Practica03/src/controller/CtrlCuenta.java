package controller;

import java.io.*;
import java.util.GregorianCalendar;

import model.Cuenta;

public class CtrlCuenta {

	private final String FILENAME = "cuentas.dat";
	private static CtrlCuenta mi_controlador;

	//Inicializar controlador
	public static CtrlCuenta GetControlador() {

		if (mi_controlador == null) {
			mi_controlador = new CtrlCuenta();
		}
		return mi_controlador;
	}
	
	public static boolean verificarFechaFutura(String fechaStr) {
        // Convertir la fecha del string a GregorianCalendar gilipollas
        GregorianCalendar fecha = convertirAFecha(fechaStr);

        boolean esFutura = esFechaFutura(fecha);

        return esFutura;
    }
	
	public static boolean esFechaFutura(GregorianCalendar fecha) {
		// Comprobar fecha futura puto niño
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
}
