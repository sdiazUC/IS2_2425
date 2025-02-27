package es.unican.is2.main;

import es.unican.is2.DAOs.*;
import es.unican.is2.vistas.*;

/**
 * Clase principal que construye la aplicaci�n de tres capas y lanza su ejecuci�n
 */
public class Runner {

	public static void main(String[] args) {
		// Componentes capa DAO
		ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();

		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);

		// Componentes casa presentacion
		VistaFuncionario vista = new VistaFuncionario(negocio);

		// Lanza ejecuci�n
		vista.setVisible(true);
	}

}
