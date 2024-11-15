package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Cuenta;

public class CtrlCuenta {

	private final String FILENAME = "cuentas.dat";

	public void escribirFichero(List<Cuenta> cuentas) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
			oos.writeObject(cuentas);
			System.out.println("Cuentas guardadas correctamente en " + FILENAME);
		} catch (IOException e) {
			System.err.println("Error al escribir en el fichero: " + e.getMessage());
		}
	}

	public List<Cuenta> leerFichero() {
		List<Cuenta> cuentas = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
			cuentas = (List<Cuenta>) ois.readObject();
			System.out.println("Cuentas leídas correctamente desde " + FILENAME);
		} catch (FileNotFoundException e) {
			System.err.println("Archivo no encontrado: " + FILENAME);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer del fichero: " + e.getMessage());
		}
		return cuentas;
	}
}
