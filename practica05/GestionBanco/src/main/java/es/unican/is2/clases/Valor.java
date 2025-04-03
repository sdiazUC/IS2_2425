package es.unican.is2.clases;

/*
 * nº métodos = 7 (contando el constructor)
 * WMC = 7
 * WMC/n = 1
 * CCog = 0
 * CCog/n = 0
 * DIT = 0 (si no se cuenta la propia clase)
 * NOC = 0
 * CBO EFF = 0
 * CBO AFF = 2
 */

/**
 * Clase que representa un valor en bolsa (paquete de acciones).
 * Cada valor contiene un n�mero de acciones
 * de una de las entidades del IBEX 35
 */
public class Valor {

	private String entidad;
	private int numAcciones;
	private double cotizacion;

	// CC = 1
	// CCog = 0
	public Valor(String entidad, int numAcciones, double cotizacionActual) {
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}

	// CC = 1
	// CCog = 0
	public int getNumValores() {
		return numAcciones;
	}

	// CC = 1
	// CCog = 0
	public void setNumValores(int numValores) {
		this.numAcciones = numValores;
	}

	// CC = 1
	// CCog = 0
	public double getCotizacion() {
		return cotizacion;
	}

	// CC = 1
	// CCog = 0
	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}

	// CC = 1
	// CCog = 0
	public String getEntidad() {
		return entidad;
	}

	@Override
	// CC = 1
	// CCog = 0
	public boolean equals(Object obj) {
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones);

	}

}