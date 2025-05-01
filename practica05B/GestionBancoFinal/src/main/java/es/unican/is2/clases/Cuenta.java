package es.unican.is2.clases;

/*
 * nº métodos = 3 (contando el constructor)
 * WMC = 3
 * WMC/n = 3/3 = 1
 * CCog = 0
 * CCog/n = 0
 * DIT = 0 (si no se cuenta la propia clase)
 * NOC = 2
 * CBO EFF = 0
 * CBO AFF = 3; clases: Cliente, CuentaAhorro, CuentaValores
 * CBO = 3
 */

public abstract class Cuenta {

	private String numCuenta;

	// CC = 1
	// CCog = 0
	protected Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	// CC = 1
	// CCog = 0
	public String getNumCuenta() {
		return numCuenta;
	}

	// CC = 1
	// CCog = 0
	public abstract double getSaldo();

}
