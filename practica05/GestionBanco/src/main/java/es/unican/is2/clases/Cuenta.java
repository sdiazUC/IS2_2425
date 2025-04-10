package es.unican.is2.clases;

/*
 * nº métodos = 2 (contando el constructor)
 * WMC = 2
 * WMC/n = 1
 * CCog = 0
 * CCog/n = 0
 * DIT = 0 (si no se cuenta la propia clase)
 * NOC = 2
 * CBO EFF = 0
 * CBO AFF = 3; clases: Cliente, CuentaAhorro, CuentaValores
 */

public class Cuenta {

	private String numCuenta;

	// CC = 1
	// CCog = 0
	public Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	// CC = 1
	// CCog = 0
	public String getNumCuenta() {
		return numCuenta;
	}

}
