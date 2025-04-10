package es.unican.is2.clases;

/*
 * WCM: 16 (Contando el contructor)
 * WCMn: 1.778 (Contando el contructor)
 * CCog: 8
 * CCogn: 0.889
 * CBO AFF: 2
 * CBO EFF: 4
 * CBO: 2
 * DIT: 1
 * NOC: 0
 */

 import java.time.LocalDate;
 import java.time.LocalDateTime;
 import java.util.List;

import es.unican.is2.excepciones.datoErroneoException;
import es.unican.is2.excepciones.saldoInsuficienteException;
 
 
 public class Credito extends Tarjeta {
	 
	 private double credito;
	 private List<Movimiento> MovimientosMensuales;
	 private List<Movimiento> historicoMovimientos;
 
	 /*
	  * CC: 1
	  * CCog: 0
	  */
	 public Credito(String numero, String titular, String cvc,
			 CuentaAhorro cuentaAsociada, double credito) {
		 super(numero, titular, cvc, cuentaAsociada);
		 this.credito = credito;
	 }
 
	 /*
	  * CC: 3
	  * CCog: 3
	  */
	 /**
	  * Retirada de dinero en cajero con la tarjeta
	  * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	  * @throws saldoInsuficienteException
	  * @throws datoErroneoException
	  */
	 @Override
	 public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		 if (x<0)
			 throw new datoErroneoException("No se puede retirar una cantidad negativa");
		 
		 Movimiento m = new Movimiento();
		 LocalDateTime now = LocalDateTime.now();
		 m.setF(now);
		 m.setC("Retirada en cajero");
		 x += x * 0.05; // Comision por operacion con tarjetas credito
		 m.setI(-x);
		 
		 if (getGastosAcumulados()+x > credito)
			 throw new saldoInsuficienteException("Credito insuficiente");
		 else {
			 MovimientosMensuales.add(m);
		 }
	 }
 
	 /*
	  * CC: 3
	  * CCog: 2
	  */
	 @Override
	 public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		 if (x<0)
			 throw new datoErroneoException("No se puede retirar una cantidad negativa");
		 
		 if (getGastosAcumulados() + x > credito)
			 throw new saldoInsuficienteException("Saldo insuficiente");
		 
		 Movimiento m = new Movimiento();
		 LocalDateTime now = LocalDateTime.now();
		 m.setF(now);
		 m.setC("Compra a credito en: " + datos);
		 m.setI(-x);
		 MovimientosMensuales.add(m);
	 }
	 
	 /*
	  * CC: 2
	  * CCog: 1
	  */
	 private double getGastosAcumulados() {
		 double r = 0.0;
		 for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			 Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			 r += m.getI();
		 }
		 return r;
	 }
	 
	 
	 /*
	  * CC: 1
	  * CCog: 0
	  */
	 public LocalDate getCaducidadCredito() {
		 return this.cuentaAsociada.getCaducidadCredito();
	 }
 
	 /*
	  * CC: 3
	  * CCog: 2
	  */
	 /**
	  * Metodo que se invoca automaticamente el dia 1 de cada mes
	  */
	 public void liquidar() {
		 Movimiento liq = new Movimiento();
		 LocalDateTime now = LocalDateTime.now();
		 liq.setF(now);
		 liq.setC("Liquidacion de operaciones tarjeta credito");
		 double r = 0.0;
		 for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			 Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			 r += m.getI();
		 }
		 liq.setI(-r);
	 
		 if (r != 0)
			 cuentaAsociada.addMovimiento(liq);
		 
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
 }