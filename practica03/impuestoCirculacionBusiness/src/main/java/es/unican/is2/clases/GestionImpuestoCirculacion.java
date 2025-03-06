package es.unican.is2.clases;

import es.unican.is2.interfaces.*;

/**
 * Clase que implementa la gestión del impuesto de
 * circulación de un ayunamiento.
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
    private IContribuyentesDAO contribuyentesDAO;
    private IVehiculosDao vehiculosDAO;

    public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentesDAO, IVehiculosDao vehiculosDAO) {
        //TODO
    }

    @Override
    public Contribuyente altaContribuyente(Contribuyente c) {
        //TODO
        return null;
    }

    @Override
    public Contribuyente bajaContribuyente(String dni) {
        //TODO
        return null;
    }

    @Override
    public Vehiculo altaVehiculo(Vehiculo v, String matricula) {
        //TODO
        return null;
    }

    @Override
    public Vehiculo bajaVehiculo(String dni, String matricula) {
        //TODO
        return null;
    }

    @Override
    public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) {
        //TODO
        return false;
    }

    @Override
    public Contribuyente contribuyente(String dni) {
        //TODO
        return null;
    }

    @Override
    public Vehiculo vehiculo(String matricula) {
        //TODO
        return null;
    }
}
