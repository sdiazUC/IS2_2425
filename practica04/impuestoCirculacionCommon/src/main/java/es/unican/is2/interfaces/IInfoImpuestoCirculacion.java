package es.unican.is2.interfaces;

import es.unican.is2.clases.*;
import es.unican.is2.excepciones.*;

/**
 * Interfaz de negocio para consultar informaci�n sobre
 * contribuyentes y vehiculos
 */
public interface IInfoImpuestoCirculacion {

	/**
	 * Retorna el vehiculo cuya matricula se indica
	 * @param matricula Matricula del vehiculo buscado
	 * @return El vehiculo correspondiente a la matricula
	 * 	       null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo vehiculo(String matricula) throws DataAccessException;

	/**
	 * Retorna el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente correspondiente al dni
	 * 		   null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente contribuyente(String dni) throws DataAccessException;


}
