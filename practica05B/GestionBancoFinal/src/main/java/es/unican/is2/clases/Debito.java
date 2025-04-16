package es.unican.is2.clases;

import java.time.LocalDate;

import es.unican.is2.excepciones.datoErroneoException;
import es.unican.is2.excepciones.saldoInsuficienteException;

/*
 * nº métodos = 5
 * WMC     = 7
 * WMCn    = 7/5 = 1.4
 * CCog    = 3
 * CCogn   = 3/5 = 0.6
 * DIT     = 1
 * NOC     = 0
 * CBO EFF = 4: Tarjeta, CuentaAhorro, saldoInsuficienteException, datoErroneoException
 * CBO AFF = 1: CuentaAhorro
 * CBO     = 5
 */

public class Debito extends Tarjeta {

	private double saldoDiarioDisponible;

	// CC   = 1
	// CCog = 1
	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada, LocalDate fechaCaducidad) {
		super(numero, titular, cvc, cuentaAsociada, fechaCaducidad);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}

	// CC   = 2
	// CCog = 1
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}

	// CC   = 2
	// CCog = 1
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
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