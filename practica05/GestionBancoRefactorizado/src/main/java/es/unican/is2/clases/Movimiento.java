package es.unican.is2.clases;

import java.time.LocalDateTime;

/*
 * nº métodos = 7 (contando el constructor)
 * WCM = 8 (contando el contructor)
 * WCM/n = 1 (contando el contructor)
 * CCog = 0
 * CCog/n = 0
 * DIT = 0
 * NOC = 0
 * CBO EFF = 0
 * CBO AFF = 2; clases: Movimiento, CuentaAhorro
 */

public class Movimiento {

	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public Movimiento(String concepto, LocalDateTime fecha, double importe) {
		this.concepto = concepto;
		this.fecha = fecha;
		this.importe = importe;
	}

	// CC = 1
	// CCog = 0
	public double getImporte() {
		return importe;
	}

	// CC = 1
	// CCog = 0
	public void setImporte(double newMImporte) {
		importe = newMImporte;
	}

	// CC = 1
	// CCog = 0
	public String getConcepto() {
		return concepto;
	}

	// CC = 1
	// CCog = 0
	public void setConcepto(String newMConcepto) {
		concepto = newMConcepto;
	}

	// CC = 1
	// CCog = 0
	public LocalDateTime getFecha() {
		return fecha;
	}

	// CC = 1
	// CCog = 0
	public void setFecha(LocalDateTime newMFecha) {
		fecha = newMFecha;
	}

	// CC = 1
	// CCog = 0
	@Override
	public boolean equals(Object obj) {
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe);
	}

}
