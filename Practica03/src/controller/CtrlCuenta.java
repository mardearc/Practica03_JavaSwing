package controller;

import java.io.*;
import model.Cuenta;

public class CtrlCuenta {

    private final String FILENAME = "cuentas.dat";

    /**
     * Escribe una Lista de cuentas en un archivo utilizando Serializable.
     *
     * @param cuentas La lista de cuentas a guardar en el archivo.
     */
    public void escribirFichero(Lista<Cuenta> cuentas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(cuentas);
            System.out.println("Cuentas guardadas correctamente en " + FILENAME);
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    /**
     * Lee una Lista de cuentas desde un archivo utilizando Serializable.
     *
     * @return La lista de cuentas leída desde el archivo o una lista vacía si no se puede leer.
     */
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
