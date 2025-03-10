package es.unican.is2.clases;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private final double potencia;

	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
		this.potencia = potencia;
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() {
		double precioSubtotal;
		double bonificacion = 0;

		int antiguedad = Period.between(this.getFechaMatriculacion(), LocalDate.now()).getYears();

		if (this.potencia < 8) {
			precioSubtotal = 25;
		} else if (this.potencia < 12) {
			precioSubtotal = 67;
		} else if (this.potencia < 16) {
			precioSubtotal = 143;
		} else if (this.potencia < 20) {
			precioSubtotal = 178;
		} else {
			precioSubtotal = 223;
		}

		if (antiguedad > 25) {
			bonificacion = 1;
		} else if (this.getMotor() == TipoMotor.ELECTRICO) {
			bonificacion = 0.75;
		} else if (this.getMotor() == TipoMotor.HIBRIDO && antiguedad < 4) {
			bonificacion = 0.75;
		} else if (this.getMotor() == TipoMotor.GAS && antiguedad < 1) {
			bonificacion = 0.5;
		}

		return precioSubtotal * (1 - bonificacion);
	}

}
