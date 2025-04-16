package es.unican.is2.clases;

import java.time.LocalDate;

import es.unican.is2.excepciones.datoErroneoException;
import es.unican.is2.excepciones.saldoInsuficienteException;

/*
 * nº de métodos = 1 (excluyendo métodos abstractos)
 * WCM = 1 (excluyendo métodos abstractos)
 * WCM/n = 1 (excluyendo metodos abstractos)
 * CCog = 0
 * CCog/n = 0
 * DIT = 0 (Si no se cuenta la propia clase)
 * NOC = 2
 * CBO EFF = 3; clases: CuentaAhorro, datoErroneoException, saldoInsuficienteException
 * CBO AFF = 3; clases: Cliente, Credito, Debito
 */

public abstract class Tarjeta {

	protected String numero, titular, cvc;
	protected CuentaAhorro cuentaAsociada;
	private LocalDate fechaCaducidad;

	// CC = 1
	// CCog = 0
	public Tarjeta(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, LocalDate fechaCaducidad) {
		this.numero = numero;
		this.titular = titular;
		this.cvc = cvc;
		this.cuentaAsociada = cuentaAsociada;
		this.fechaCaducidad = fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public LocalDate getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws saldoInsuficienteException, datoErroneoException;

}