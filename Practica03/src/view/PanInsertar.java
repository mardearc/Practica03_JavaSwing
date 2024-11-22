package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import controller.CtrlCuenta;
import controller.Lista;
import model.*;

public class PanInsertar extends JPanel {

	private static final long serialVersionUID = 1L;

	// Campos comunes
	private JTextField txtNumero, txtTitular, txtSaldoMinimo, txtSaldo, txtFecha;
	private JLabel lblNumero, lblTitular, lblSaldoMinimo, lblSaldo, lblFecha;

	// Campos para CuentaCorriente
	private JTextField txtComision, txtTipoComision;
	private JLabel lblComision, lblTipoComision;

	// Campos para CuentaAhorro
	private JTextField txtInteresAnual, txtRentabilidad;
	private JLabel lblInteresAnual, lblRentabilidad;

	// Botones
	private JRadioButton rbtnCorriente, rbtnAhorro;
	private ButtonGroup tipoCuentaGroup;
	private JButton btnInsertar;

	// Lista de cuentas
	private Lista<Cuenta> cuentas;

	//Conbtrolador
	CtrlCuenta ctrlCuenta = CtrlCuenta.GetControlador();
	
	//Panel Lista y Cuenta
	PanLista panLista;
	PanCuenta panCuenta;
	
	public PanInsertar(Lista<Cuenta> listaCuentas, PanLista panelLista, PanCuenta panelCuenta) {
		
		cuentas = listaCuentas;
		panLista = panelLista;
		panCuenta = panelCuenta;
		setLayout(null);

		addComponents();
		addListeners();

	}

	private void addListeners() {
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarCuenta();
			}
		});
	}

	private void addComponents() {
		// Inicialización de campos comunes
		lblNumero = new JLabel("Número:");
		lblNumero.setBounds(10, 10, 100, 25);
		add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setBounds(120, 10, 150, 25);
		add(txtNumero);

		lblTitular = new JLabel("Titular:");
		lblTitular.setBounds(10, 40, 100, 25);
		add(lblTitular);

		txtTitular = new JTextField();
		txtTitular.setBounds(120, 40, 150, 25);
		add(txtTitular);

		lblSaldoMinimo = new JLabel("Saldo Mínimo:");
		lblSaldoMinimo.setBounds(10, 70, 100, 25);
		add(lblSaldoMinimo);

		txtSaldoMinimo = new JTextField();
		txtSaldoMinimo.setBounds(120, 70, 150, 25);
		add(txtSaldoMinimo);

		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(10, 100, 100, 25);
		add(lblSaldo);

		txtSaldo = new JTextField();
		txtSaldo.setBounds(120, 100, 150, 25);
		add(txtSaldo);

		lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
		lblFecha.setBounds(10, 130, 150, 25);
		add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(170, 130, 100, 25);
		add(txtFecha);

		// Campos para CuentaCorriente
		lblComision = new JLabel("Comisión:");
		lblComision.setBounds(10, 160, 100, 25);
		lblComision.setVisible(false);
		add(lblComision);

		txtComision = new JTextField();
		txtComision.setBounds(120, 160, 150, 25);
		txtComision.setVisible(false);
		add(txtComision);

		lblTipoComision = new JLabel("Tipo Comisión:");
		lblTipoComision.setBounds(10, 190, 100, 25);
		lblTipoComision.setVisible(false);
		add(lblTipoComision);

		txtTipoComision = new JTextField();
		txtTipoComision.setBounds(120, 190, 150, 25);
		txtTipoComision.setVisible(false);
		add(txtTipoComision);

		// Campos para CuentaAhorro
		lblInteresAnual = new JLabel("Interés Anual:");
		lblInteresAnual.setBounds(10, 160, 100, 25);
		lblInteresAnual.setVisible(false);
		add(lblInteresAnual);

		txtInteresAnual = new JTextField();
		txtInteresAnual.setBounds(120, 160, 150, 25);
		txtInteresAnual.setVisible(false);
		add(txtInteresAnual);

		lblRentabilidad = new JLabel("Rentabilidad:");
		lblRentabilidad.setBounds(10, 190, 100, 25);
		lblRentabilidad.setVisible(false);
		add(lblRentabilidad);

		txtRentabilidad = new JTextField();
		txtRentabilidad.setBounds(120, 190, 150, 25);
		txtRentabilidad.setVisible(false);
		add(txtRentabilidad);

		// Radio Buttons para tipo de cuenta
		rbtnCorriente = new JRadioButton("Cuenta Corriente");
		rbtnCorriente.setBounds(10, 220, 150, 25);
		rbtnCorriente.addActionListener(e -> toggleCampos(true));
		add(rbtnCorriente);

		rbtnAhorro = new JRadioButton("Cuenta Ahorro");
		rbtnAhorro.setBounds(170, 220, 115, 25);
		rbtnAhorro.addActionListener(e -> toggleCampos(false));
		add(rbtnAhorro);

		tipoCuentaGroup = new ButtonGroup();
		tipoCuentaGroup.add(rbtnCorriente);
		tipoCuentaGroup.add(rbtnAhorro);

		// Botón Insertar
		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(318, 220, 100, 25);
		add(btnInsertar);
	}

	private void toggleCampos(boolean esCorriente) {
		// Mostrar/ocultar campos de CuentaCorriente
		lblComision.setVisible(esCorriente);
		txtComision.setVisible(esCorriente);
		lblTipoComision.setVisible(esCorriente);
		txtTipoComision.setVisible(esCorriente);

		// Mostrar/ocultar campos de CuentaAhorro
		lblInteresAnual.setVisible(!esCorriente);
		txtInteresAnual.setVisible(!esCorriente);
		lblRentabilidad.setVisible(!esCorriente);
		txtRentabilidad.setVisible(!esCorriente);
	}

	private void insertarCuenta() {
		try {
			int numero = Integer.parseInt(txtNumero.getText());
			String titular = txtTitular.getText();
			double saldoMinimo = Double.parseDouble(txtSaldoMinimo.getText());
			double saldo = Double.parseDouble(txtSaldo.getText());
			String[] fechaParts = txtFecha.getText().split("-");
			GregorianCalendar fechaApertura = new GregorianCalendar(Integer.parseInt(fechaParts[0]),
					Integer.parseInt(fechaParts[1]) - 1, Integer.parseInt(fechaParts[2]));

			if (rbtnCorriente.isSelected()) {
				double comision = Double.parseDouble(txtComision.getText());
				String tipoComision = txtTipoComision.getText();
				
				//Actualizar la lista estática de cuentas
				FrmPrincipal.cuentas.insertarNodo(new CuentaCorriente(numero, titular, saldoMinimo, saldo, fechaApertura, comision,
						tipoComision));
				JOptionPane.showMessageDialog(this, "Cuenta insertada correctamente.");
				
				//Actualizar la lista del panelLista y panelCuenta
				panLista.actualizarLista(FrmPrincipal.cuentas);
				panCuenta.actualizarLista(FrmPrincipal.cuentas);
				limpiarCampos();
			} else if (rbtnAhorro.isSelected()) {
				double interesAnual = Double.parseDouble(txtInteresAnual.getText());
				double rentabilidad = Double.parseDouble(txtRentabilidad.getText());
				
				//Actualizar la lista estática de cuentas
				FrmPrincipal.cuentas.insertarNodo(new CuentaAhorro(numero, titular, saldoMinimo, saldo, fechaApertura, interesAnual,
						rentabilidad));
				JOptionPane.showMessageDialog(this, "Cuenta insertada correctamente.");
				
				//Actualizar la lista del panelLista
				panLista.actualizarLista(FrmPrincipal.cuentas);
				panCuenta.actualizarLista(FrmPrincipal.cuentas);
				limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(this, "Debe elegir un tipo de cuenta.");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error al insertar la cuenta: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCampos() {
		txtNumero.setText("");
		txtTitular.setText("");
		txtSaldoMinimo.setText("");
		txtSaldo.setText("");
		txtFecha.setText("");
		txtComision.setText("");
		txtTipoComision.setText("");
		txtInteresAnual.setText("");
		txtRentabilidad.setText("");
	}
}
