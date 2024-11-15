package view;

import java.util.GregorianCalendar;
import controller.CtrlCuenta;
import controller.Lista;
import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;

public class Principal {
    public static void main(String[] args) {
        CtrlCuenta ctrl = new CtrlCuenta();
        Lista<Cuenta> cuentas = new Lista<>();

        // Crear cuentas y añadirlas a la lista
        cuentas.insertarNodo(new CuentaCorriente(1, "Juan Pérez", 1000, 100, new GregorianCalendar(2023, 1, 15), 5, "Mensual"));
        cuentas.insertarNodo(new CuentaAhorro(2, "Ana Gómez", 2000, 200, new GregorianCalendar(2022, 5, 20), 3.5, 50));

        // Guardar las cuentas en el archivo
        ctrl.escribirFichero(cuentas);

        // Leer las cuentas desde el archivo
        Lista<Cuenta> cuentasLeidas = ctrl.leerFichero();
        cuentasLeidas.mostrarLista();
    }
}
