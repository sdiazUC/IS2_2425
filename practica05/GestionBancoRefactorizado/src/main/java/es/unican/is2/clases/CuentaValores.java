package es.unican.is2.clases;

import java.util.LinkedList;
import java.util.List;

/*
 * WMC     = 4
 * WMCn    = 1.33
 * CCog    = 4
 * CCogn   = 1.33
 * DIT     = 2
 * NOC     = 0 
 * CBO EFF = 2 Valor, Cuenta
 * CBO AFF = 0
 */

public class CuentaValores extends Cuenta {

	private List<Valor> valores;

	// CC   = 1
	// CCog = 1
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
		for (Valor v:valores) {
			if (v.getEntidad().equals(valor.getEntidad()))
				return false;
		}
		valores.add(valor);
		return true;
	}

}
