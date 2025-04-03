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
 * CBO EFF = 1
 * CBO AFF = 2
 */

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	// CC = 1
	// CCog = 0
	public double getI() {
		return importe;
	}

	// CC = 1
	// CCog = 0
	public void setI(double newMImporte) {
		importe = newMImporte;
	}

	// CC = 1
	// CCog = 0
	public String getC() {
		return concepto;
	}

	// CC = 1
	// CCog = 0
	public void setC(String newMConcepto) {
		concepto = newMConcepto;
	}

	// CC = 1
	// CCog = 0
	public LocalDateTime getF() {
		return fecha;
	}

	// CC = 1
	// CCog = 0
	public void setF(LocalDateTime newMFecha) {
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