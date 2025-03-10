package es.unican.is2.clases;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private final int cilindrada;

	/**
	 * Construye un objeto de Motocicleta.
	 * @param id
	 * @param matricula
	 * @param fechaMatriculacion
	 * @param motor
	 * @param cilindrada
	 */
	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		this.cilindrada = cilindrada;
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
	}


	@Override
	public double precioImpuesto() {
		double precioSubtotal;
		double bonificacion = 0;

		int antiguedad = Period.between(this.getFechaMatriculacion(), LocalDate.now()).getYears();

		if (this.cilindrada < 125) {
			precioSubtotal = 8;
		} else if (this.cilindrada < 250) {
			precioSubtotal = 15;
		} else if (this.cilindrada < 500) {
			precioSubtotal = 30;
		} else if (this.cilindrada < 100) {
			precioSubtotal = 60;
		} else {
			precioSubtotal = 120;
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
