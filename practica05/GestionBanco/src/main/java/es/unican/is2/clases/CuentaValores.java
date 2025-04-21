package es.unican.is2.clases;

import java.util.LinkedList;
import java.util.List;

/*
 * WMC     = 4
 * WMCn    = 1.33
 * CCog    = 3
 * CCogn   = 1
 * DIT     = 2
 * NOC     = 0
 * CBO EFF = 2 Valor, Cuenta
 * CBO AFF = 0
 */

public class CuentaValores extends Cuenta {

	private List<Valor> valores;

	// CC   = 1
	// CCog = 0
	public CuentaValores(String numCuenta) {
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}

	// CC   = 1
	// CCog = 0
	public List<Valor> getValores() {
		return valores;
	}

	// CC   = 2
	// CCog = 3
	public boolean anhadeValor(Valor valor) {
		for (Valor v:valores) { // CCog + 1
			if (v.getEntidad().equals(valor.getEntidad())) // CCog + 2
				return false;
		}
		valores.add(valor);
		return true;
	}

}
