package es.unican.is2.clases;

import es.unican.is2.excepciones.datoErroneoException;
import es.unican.is2.excepciones.saldoInsuficienteException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/*
 * WMC     = 19
 * WMCn    = 1.46
 * CCog    = 8
 * CCogn   = 0.42
 * DIT     = 1
 * NOC     = 0
 * CBO EFF = 1 Movmiento
 * CBO AFF = 0
 */

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> Movimientos;
	private double limiteDebito;
	private List<Tarjeta> tarjetasAsociadas;

	private static final String CONCEPTO_INGRESO = "Ingreso en efectivo";
	private static final String CONCEPTO_RETIRADA = "Retirada de efectivo";

	/*
	 * CC   = 1
	 * CCog = 1
	 */
	public CuentaAhorro(String numCuenta) throws datoErroneoException {
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
		tarjetasAsociadas = new LinkedList<Tarjeta>();
	}

	/*
	 * CC = 2
	 * CCog = 1
	 */
	public void ingresar(double importe) throws datoErroneoException {
		if (importe <= 0) {
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		}

		Movimiento movimiento = new Movimiento(CONCEPTO_INGRESO, LocalDateTime.now(), importe);
		this.Movimientos.add(movimiento);
	}

	/*
	 * CC = 3
	 * CCog = 2
	 */
	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {
		if (importe <= 0) {
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		}

		if (getSaldo() < importe) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}

		Movimiento movimiento = new Movimiento(CONCEPTO_RETIRADA, LocalDateTime.now(), -importe);
		this.Movimientos.add(movimiento);
	}

	/*
	 * CC = 2
	 * CCog = 1
	 */
	public void ingresar(String concepto, double importe) throws datoErroneoException {
		if (importe <= 0) {
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		}

		Movimiento movimiento = new Movimiento(concepto, LocalDateTime.now(), importe);
		this.Movimientos.add(movimiento);
	}

	/*
	 * CC = 3
	 * CCog = 2
	 */
	public void retirar(String concepto, double importe) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < importe) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}

		if (importe <= 0) {
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		}

		Movimiento movimiento = new Movimiento(concepto, LocalDateTime.now(), -importe);
		this.Movimientos.add(movimiento);
	}

	@Override
	/*
	 * CC = 2
	 * CCog = 1
	 */
	public double getSaldo() {
		double total = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) {
			Movimiento movimiento = (Movimiento) Movimientos.get(i);
			total += movimiento.getImporte();
		}
		return total;
	}

	/*
	 * CC = 1
	 * CCog = 0
	 */
	public void addMovimiento(Movimiento m) {
		Movimientos.add(m);
	}

	/*
	 * CC = 1
	 * CCog = 0
	 */
	public List<Movimiento> getMovimientos() {
		return Movimientos;
	}

	/*
	 * CC = 1
	 * CCog = 0
	 */
	public double getLimiteDebito() {
		return limiteDebito;
	}

	public List<Tarjeta> getTarjetasAsociadas() {
		return this.tarjetasAsociadas;
	}

	/*
	 * CC = 1
	 * CCog = 0
	 */
	public void setTarjetaCredito(Credito tarjeta) {
		this.tarjetasAsociadas.add(tarjeta);
	}

	/*
	 * CC = 1
	 * CCog = 0
	 */
	public void setTarjetaDebito(Debito tarjeta) {
		this.tarjetasAsociadas.add(tarjeta);
	}

}