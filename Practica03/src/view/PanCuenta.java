package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import controller.Lista;
import controller.Node;
import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;
import model.SaldoNoValidoException;

public class PanCuenta extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTipoCuenta, lblNumero, lblSaldoMinimo, lblSaldo, lblFecha, lblInfo, lblExtra1, lblExtra2;
	private JButton btnAnterior, btnSiguiente, btnCalcular;
	private Lista<Cuenta> listaCuentas;
	private Node<Cuenta> nodoActual;
	private int indiceActual, totalCuentas;

	private Cuenta cuentaActual;

	public PanCuenta() {
		this.listaCuentas = FrmPrincipal.cuentas;
		this.nodoActual = FrmPrincipal.cuentas.getInicio();
		this.indiceActual = 1;
		this.totalCuentas = calcularTotalCuentas();

		setLayout(null);
		addComponents();
		addListeners();
		mostrarCuenta();
	}

	private void addComponents() {
		lblTipoCuenta = new JLabel("Tipo de Cuenta:");
		lblTipoCuenta.setBounds(10, 10, 300, 25);
		add(lblTipoCuenta);

		lblNumero = new JLabel("Número:");
		lblNumero.setBounds(10, 40, 300, 25);
		add(lblNumero);

		lblSaldoMinimo = new JLabel("Saldo Mínimo:");
		lblSaldoMinimo.setBounds(10, 63, 300, 25);
		add(lblSaldoMinimo);

		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(10, 89, 300, 25);
		add(lblSaldo);

		lblFecha = new JLabel("Fecha de Apertura:");
		lblFecha.setBounds(10, 111, 300, 25);
		add(lblFecha);

		lblExtra1 = new JLabel(); // Campo adicional según tipo de cuenta
		lblExtra1.setBounds(248, 45, 202, 25);
		add(lblExtra1);

		lblExtra2 = new JLabel(); // Campo adicional según tipo de cuenta
		lblExtra2.setBounds(248, 75, 202, 25);
		add(lblExtra2);

		lblInfo = new JLabel("1 de " + totalCuentas);
		lblInfo.setBounds(172, 201, 100, 25);
		add(lblInfo);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(58, 166, 100, 25);
		add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(172, 166, 100, 25);
		add(btnSiguiente);

		btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(290, 166, 100, 25);
		add(btnCalcular);
	}

	private void addListeners() {
		btnAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moverAnterior();
			}
		});

		btnSiguiente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moverSiguiente();
			}
		});

		btnCalcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cuentaActual instanceof CuentaAhorro) {
					// Es una CuentaAhorro
					CuentaAhorro cuentaAhorro = (CuentaAhorro) cuentaActual;
					double nuevoSaldo = cuentaAhorro.getSaldo() * (1 + cuentaAhorro.getInteresAnual() / 100);

					// Verificar que el nuevo saldo no es menor al saldo mínimo
					if (nuevoSaldo >= cuentaAhorro.getSaldoMinimo()) {
						try {
							cuentaAhorro.setSaldo(nuevoSaldo);
							lblSaldo.setText(cuentaAhorro.getSaldo() + "");
							JOptionPane.showMessageDialog(null, "Saldo actualizado correctamente.");
						} catch (SaldoNoValidoException e1) {
							JOptionPane.showMessageDialog(null, "El saldo no puede ser inferior al saldo mínimo.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else if (cuentaActual instanceof CuentaCorriente) {
					// Es una CuentaCorriente
					CuentaCorriente cuentaCorriente = (CuentaCorriente) cuentaActual;
					double nuevoSaldo = cuentaCorriente.getSaldo() - (cuentaCorriente.getSaldo() * (cuentaCorriente.getComision() / 100.0));

					// Verificar que el nuevo saldo no es menor al saldo mínimo
					if (nuevoSaldo >= cuentaCorriente.getSaldoMinimo()) {
						try {
							cuentaCorriente.setSaldo(nuevoSaldo);
							lblSaldo.setText(cuentaCorriente.getSaldo() + "");
							JOptionPane.showMessageDialog(null, "Saldo actualizado correctamente.");
						} catch (SaldoNoValidoException e1) {
							JOptionPane.showMessageDialog(null, "El saldo no puede ser inferior al saldo mínimo.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					// Si la cuenta no es de un tipo válido
					JOptionPane.showMessageDialog(null, "Tipo de cuenta no reconocido.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void mostrarCuenta() {
		if (nodoActual == null) {
			limpiarCampos();
			lblInfo.setText("0 de 0");
			return;
		}

		Cuenta cuenta = nodoActual.getPrincipal();
		lblNumero.setText("Número: " + cuenta.getNumero());
		lblSaldoMinimo.setText("Saldo Mínimo: " + cuenta.getSaldoMinimo());
		lblSaldo.setText("Saldo: " + cuenta.getSaldo());
		lblFecha.setText("Fecha de Apertura: " + cuenta.getFechaApertura().getTime());

		if (cuenta instanceof CuentaCorriente) {
			lblTipoCuenta.setText("Tipo de Cuenta: Corriente");
			CuentaCorriente corriente = (CuentaCorriente) cuenta;
			lblExtra1.setText("Comisión: " + corriente.getComision());
			lblExtra2.setText("Tipo de Comisión: " + corriente.getTipoComision());
			actualizarCalcular(corriente, btnCalcular);
			cuentaActual = corriente;
		} else if (cuenta instanceof CuentaAhorro) {
			lblTipoCuenta.setText("Tipo de Cuenta: Ahorro");
			CuentaAhorro ahorro = (CuentaAhorro) cuenta;
			lblExtra1.setText("Interés Anual: " + ahorro.getInteresAnual());
			lblExtra2.setText("Rentabilidad: " + ahorro.getRentabilidad());
			actualizarCalcular(ahorro, btnCalcular);
			cuentaActual = ahorro;
		} else {
			lblTipoCuenta.setText("Tipo de Cuenta: Desconocido");
			lblExtra1.setText("");
			lblExtra2.setText("");
		}

		lblInfo.setText(indiceActual + " de " + totalCuentas);
	}

	private void moverSiguiente() {
		if (nodoActual != null && nodoActual.getSiguiente() != null) {
			nodoActual = nodoActual.getSiguiente();
			indiceActual++;
			mostrarCuenta();
		}
	}

	private void moverAnterior() {
		if (nodoActual != null && indiceActual > 1) {
			nodoActual = obtenerNodoAnterior(nodoActual);
			indiceActual--;
			mostrarCuenta();
		}
	}

	private Node<Cuenta> obtenerNodoAnterior(Node<Cuenta> nodo) {
		Node<Cuenta> actual = listaCuentas.getInicio();
		Node<Cuenta> anterior = null;

		while (actual != null && actual != nodo) {
			anterior = actual;
			actual = actual.getSiguiente();
		}

		return anterior;
	}

	private int calcularTotalCuentas() {
		int total = 0;
		Node<Cuenta> actual = listaCuentas.getInicio();
		while (actual != null) {
			total++;
			actual = actual.getSiguiente();
		}
		return total;
	}

	private void limpiarCampos() {
		lblTipoCuenta.setText("Tipo de Cuenta:");
		lblNumero.setText("Número:");
		lblSaldoMinimo.setText("Saldo Mínimo:");
		lblSaldo.setText("Saldo:");
		lblFecha.setText("Fecha de Apertura:");
		lblExtra1.setText("");
		lblExtra2.setText("");
	}

	public void actualizarLista(Lista<Cuenta> cuentas) {
		this.listaCuentas = cuentas; // Actualiza la lista de cuentas
		this.nodoActual = cuentas.getInicio(); // Apunta al inicio de la lista
		this.indiceActual = 1; // Reinicia el índice actual
		this.totalCuentas = calcularTotalCuentas(); // Recalcula el total de cuentas
		mostrarCuenta(); // Muestra la cuenta actualizada
	}

	public boolean cumpleMes(CuentaCorriente c) {
	    Calendar fechaApertura = c.getFechaApertura();
	    Calendar actual = Calendar.getInstance();

	    // Comprobar si ha pasado al menos un mes
	    actual.add(Calendar.MONTH, -1); // Retrocede un mes
	    return !fechaApertura.after(actual); // Si la fecha de apertura es igual o anterior, cumple
	}

	public boolean cumpleAnio(CuentaAhorro c) {
	    Calendar fechaApertura = c.getFechaApertura();
	    Calendar actual = Calendar.getInstance();

	    // Comprobar si ha pasado al menos un año
	    actual.add(Calendar.YEAR, -1); // Retrocede un año
	    return !fechaApertura.after(actual); // Si la fecha de apertura es igual o anterior, cumple
	}

	public void actualizarCalcular(Cuenta cuenta, JButton botonCalcular) {
		if (cuenta instanceof CuentaAhorro) {
			// Verificar si es una CuentaAhorro y cumple el año
			if (cumpleAnio((CuentaAhorro) cuenta)) {
				botonCalcular.setEnabled(true);
			} else {
				botonCalcular.setEnabled(false);
			}
		} else if (cuenta instanceof CuentaCorriente) {
			// Verificar si es una CuentaCorriente y cumple el mes
			if (cumpleMes((CuentaCorriente) cuenta)) {
				botonCalcular.setEnabled(true);
			} else {
				botonCalcular.setEnabled(false);
			}
		} else {
			// En caso de que no sea una cuenta válida, deshabilitar el botón
			botonCalcular.setEnabled(false);
		}
	}
}
