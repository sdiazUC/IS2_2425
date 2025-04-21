package es.unican.is2.clases;

import es.unican.is2.excepciones.datoErroneoException;
import es.unican.is2.excepciones.saldoInsuficienteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/*
 * WMC     = 19
 * WMCn    = 1.46
 * CCog    = 7
 * CCogn   = 0.54
 * DIT     = 2
 * NOC     = 0
 * CBO EFF = 1 Movmiento
 * CBO AFF = 0
 */

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> Movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	/**
	 * CC   = 1
	 * CCog = 0
	 */
	public CuentaAhorro(String numCuenta)  throws datoErroneoException {
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	/**
	 * CC = 2
	 * CCog = 1
	 */
	public void ingresar(double x) throws datoErroneoException {
		if (x <= 0) // CCog + 1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.Movimientos.add(m);
	}

	/**
	 * CC = 3
	 * CCog = 2
	 */
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x <= 0) // CCog + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x) // CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.Movimientos.add(m);
	}

	/**
	 * CC = 2
	 * CCog = 1
	 */
	public void ingresar(String concepto, double x) throws datoErroneoException {
		if (x <= 0) // CCog + 1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.Movimientos.add(m);
	}

	/**
	 * CC = 3
	 * CCog = 2
	 */
	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < x) // CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0) // CCog + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.Movimientos.add(m);
	}

	/**
	 * CC = 2
	 * CCog = 1
	 */
	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) { // CCog + 1
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	/**
	 * CC = 1
	 * CCog = 0
	 */
	public void addMovimiento(Movimiento m) {
		Movimientos.add(m);
	}

	/**
	 * CC = 1
	 * CCog = 0
	 */
	public List<Movimiento> getMovimientos() {
		return Movimientos;
	}

	/**
	 * CC = 1
	 * CCog = 0
	 */
	public LocalDate getCaducidadDebito() {
		return caducidadDebito;
	}

	/**
	 * CC = 1
	 * CCog = 0
	 */
	public void setCaducidadDebito(LocalDate caducidadDebito) {
		this.caducidadDebito = caducidadDebito;
	}

	/**
	 * CC = 1
	 * CCog = 0
	 */
	public LocalDate getCaducidadCredito() {
		return caducidadCredito;
	}

	/**
	 * CC = 1
	 * CCog = 0
	 */
	public void setCaducidadCredito(LocalDate caducidadCredito) {
		this.caducidadCredito = caducidadCredito;
	}

	/**
	 * CC = 1
	 * CCog = 0
	 */
	public double getLimiteDebito() {
		return limiteDebito;
	}

}