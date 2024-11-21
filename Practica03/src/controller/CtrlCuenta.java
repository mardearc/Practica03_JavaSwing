package controller;

import java.io.*;
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
