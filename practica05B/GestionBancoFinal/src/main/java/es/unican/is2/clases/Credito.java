package es.unican.is2.clases;

/*
 * nº métodos = 9
 * WCM: 15
 * WCMn: 15/9 = 1.66
 * CCog: 6
 * CCogn: 6/9 = 0.66
 * CBO AFF: 1; clases: CuentaAhorro
 * CBO EFF: 5; clases: Movimiento, CuentaAhoro, Tarjeta, saldoInsuficienteException, datoErroneoException
 * CBO: 6
 * DIT: 1
 * NOC: 0
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import es.unican.is2.excepciones.datoErroneoException;
import es.unican.is2.excepciones.saldoInsuficienteException;

 public class Credito extends Tarjeta {

	private double limiteCredito;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	private static final String CONCEPTO_LIQUIDACION_CREDITO = "Liquidación de operaciones tarjeta credito";
	private static final double COMISION_RETIRADA_CREDITO = 0.05; // 5% de comision por operacion con tarjeta credito

	/*
	* CC: 1
	* CCog: 0
	*/
	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double limiteCredito, LocalDate fechaCaducidad) {
		super(numero, titular, cvc, cuentaAsociada, fechaCaducidad);
		this.limiteCredito = limiteCredito;
	}

	/*
	* CC: 3
	* CCog: 2
	*/
	/**
	* Retirada de dinero en cajero con la tarjeta
	* @param importe Cantidad a retirar. Se aplica una comision del 5%.
	* @throws saldoInsuficienteException
	* @throws datoErroneoException
	*/
	@Override
	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {
		if (importe < 0) {
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		}

		importe += importe * COMISION_RETIRADA_CREDITO;

		if (getGastosAcumulados() + importe > limiteCredito)
			throw new saldoInsuficienteException("Credito insuficiente");

		addMovimiento("Retirada en cajero", -importe);
	}

	/*
	* CC: 3
	* CCog: 2
	*/
	@Override
	public void pagoEnEstablecimiento(String datos, double importe) throws saldoInsuficienteException, datoErroneoException {
		if (importe < 0) {
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		}

		if (this.getGastosAcumulados() + importe > limiteCredito) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}

		addMovimiento("Compra a credito en: " + datos, -importe);
	}

	/*
	* CC: 2
	* CCog: 1
	*/
	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	*/
	public void liquidar() {
		double total = this.getGastosAcumulados();

		if (total != 0) {
			Movimiento liquidacion = new Movimiento(
				CONCEPTO_LIQUIDACION_CREDITO,
				LocalDateTime.now(),
				total
			);

			cuentaAsociada.addMovimiento(liquidacion);
		}

		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	}

	/*
	* CC: 1
	* CCog: 0
	*/
	public List<Movimiento> getMovimientosMensuales() {
		return MovimientosMensuales;
	}

	/*
	* CC: 1
	* CCog: 0
	*/
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}

	/*
	* CC: 1
	* CCog: 0
	*/
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	}

	/*
	* CC: 2
	* CCog: 1
	*/
	private double getGastosAcumulados() {
		double total = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			Movimiento movimiento = (Movimiento) MovimientosMensuales.get(i);
			total += movimiento.getImporte();
		}
		return total;
	}


	/*
	* CC: 1
	* CCog: 0
	*/
	private void addMovimiento(String concepto, double importe) {
		Movimiento movimiento = new Movimiento(concepto, LocalDateTime.now(), importe);
		MovimientosMensuales.add(movimiento);
	}
}