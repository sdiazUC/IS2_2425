package es.unican.is2.clases;

import java.time.LocalDate;

/**
 * Clase abstracta que representa un vehiculo.
 * Cada vehiculo tiene una matricula unica.
 */
public abstract class Vehiculo {

	private long id; // Clave primaria autogenerada
	private String matricula;
	private LocalDate fechaMatriculacion;
	private TipoMotor motor;

	/**
	 * Construye un objeto de Vehiculo.
	 * @param id clave primaria autogenerada del objeto
	 * @param matricula matricula del vehiculo
	 * @param fechaMatriculacion fecha de matriculación del vehículo
	 * @param motor motor del vehículo
	 */
	public Vehiculo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor) {
		this.id = id;
		this.matricula = matricula;
		this.fechaMatriculacion = fechaMatriculacion;
		this.motor = motor;
	}

	/**
	 * Retorna la matricula del vehiculo.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna la fecha de primera matriculacion del vehiculo.
	 */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	/**
	 * Retorna el tipo de motor del vehiculo.
	 */
	public TipoMotor getMotor() {
		return motor;
	}

	/**
	 * Retorna el identificador del vehiculo.
	 */
	public long getId() {
		return id;
	}

	public abstract double precioImpuesto();

}
