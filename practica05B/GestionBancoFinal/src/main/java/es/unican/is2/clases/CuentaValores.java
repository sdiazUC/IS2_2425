package es.unican.is2.clases;

import java.util.LinkedList;
import java.util.List;

/*
 * nº métodos = 4
 * WMC     = 5
 * WMCn    = 5/4 = 1.25
 * CCog    = 4
 * CCogn   = 4/4 = 1
 * DIT     = 1
 * NOC     = 0
 * CBO EFF = 2 Valor, Cuenta
 * CBO AFF = 0
 * CBO     = 2
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

	// CC   = 1
	// CCog = 0
	@Override
	public double getSaldo() {
		return 0;
	}
}
