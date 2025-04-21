package es.unican.is2.clases;

import java.time.LocalDate;

import es.unican.is2.excepciones.datoErroneoException;
import es.unican.is2.excepciones.saldoInsuficienteException;

/*
 * WMC     = 8
 * WMCn    = 1.33
 * CCog    = 2
 * CCogn   = 0.33
 * DIT     = 2
 * NOC     = 0
 * CBO EFF = 2 Tarjeta, CuentaAhorro
 * CBO AFF = 0
 */

public class Debito extends Tarjeta {

	private double saldoDiarioDisponible;

	// CC   = 1
	// CCog = 0
	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}

	// CC   = 2
	// CCog = 1
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) { // CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}

	// CC   = 2
	// CCog = 1
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) { // CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}

	// CC   = 1
	// CCog = 0
	public LocalDate getCaducidadDebito() {
		return this.cuentaAsociada.getCaducidadDebito();
	}

	// CC   = 1
	// CCog = 0
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}

	// CC   = 1
	// CCog = 0
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}

}