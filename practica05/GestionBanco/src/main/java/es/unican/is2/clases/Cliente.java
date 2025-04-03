package es.unican.is2.clases;

import java.util.LinkedList;
import java.util.List;

/*
 * nº métodos = 11 (contando el constructor)
 * WMC = 17
 * WMC/n = 17/11 = 1.55
 * CCog = 8
 * CCog/n = 8/11 = 0.73
 * DIT = 0 (si no se cuenta la propia clase)
 * NOC = 0
 * CBO EFF = 6
 * CBO AFF = 0
 */

public class Cliente {

	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;

    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

	// CC = 1
	// CCog = 0
 	public Cliente(String titular, String calle, String zip, String localidad,
 			String telefono, String dni) {
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}

	// CC = 1
	// CCog = 0
	public void cambiaDireccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}

	// CC = 1
	// CCog = 0
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}

	// CC = 3
	// CCog = 2
	public void anhadeTarjeta(Tarjeta t) {
		tarjetas.add(t);
		if (t instanceof Debito) {
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else {
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}

	// CC = 5
	// CCog = 6
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) { // CCog + 1
			if (c instanceof CuentaAhorro) { // CCog + 2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { // CCog + 1
				for (Valor v: ((CuentaValores) c).getValores()) { // CCog + 2
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}

	// CC = 1
	// CCog = 0
	public String getNombre() {
		return nombre;
	}

	// CC = 1
	// CCog = 0
	public String getCalle() {
		return calle;
	}

	// CC = 1
	// CCog = 0
	public String getZip() {
		return zip;
	}

	// CC = 1
	// CCog = 0
	public String getLocalidad() {
		return localidad;
	}

	// CC = 1
	// CCog = 0
	public String getTelefono() {
		return telefono;
	}

	// CC = 1
	// CCog = 0
	public String getDni() {
		return dni;
	}

}