package es.unican.is2.clases;

import es.unican.is2.excepciones.DataAccessException;
import es.unican.is2.interfaces.IContribuyentesDAO;
import es.unican.is2.interfaces.IGestionContribuyentes;
import es.unican.is2.interfaces.IGestionVehiculos;
import es.unican.is2.interfaces.IInfoImpuestoCirculacion;
import es.unican.is2.interfaces.IVehiculosDAO;

/**
 * Clase que implementa la gestión del impuesto de
 * circulación de un ayunamiento.
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
    private final IContribuyentesDAO contribuyentesDAO;
    private final IVehiculosDAO vehiculosDAO;

    public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentesDAO, IVehiculosDAO vehiculosDAO) {
        this.contribuyentesDAO = contribuyentesDAO;
        this.vehiculosDAO = vehiculosDAO;
    }

    @Override
    public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
        if (contribuyentesDAO.contribuyentes().contains(c)) {
            throw new DataAccessException();
        }

        contribuyentesDAO.creaContribuyente(c);

        return c;
    }

    @Override
    public Contribuyente bajaContribuyente(String dni) throws DataAccessException {
        Contribuyente c = contribuyente(dni);

        if (c.getVehiculos().isEmpty()) {
            throw new DataAccessException();
        }

        contribuyentesDAO.eliminaContribuyente(dni);

        return c;
    }

    @Override
    public Vehiculo altaVehiculo(Vehiculo v, String dni) throws DataAccessException {
        if (vehiculosDAO.vehiculos().contains(v)) {
            throw new DataAccessException();
        }

        Contribuyente c = contribuyente(dni);
        vehiculosDAO.creaVehiculo(v);
        c.getVehiculos().add(v);

        return v;
    }

    @Override
    public Vehiculo bajaVehiculo(String dni, String matricula) throws DataAccessException {
        Contribuyente c = contribuyente(dni);
        Vehiculo v = c.buscaVehiculo(matricula);
        c.getVehiculos().remove(v);
        vehiculosDAO.eliminaVehiculo(matricula);

        return v;
    }

    @Override
    public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) throws DataAccessException {
        Contribuyente c = contribuyente(dniActual);
        if (c == null) {
            throw new DataAccessException();

        }
        Vehiculo v = c.buscaVehiculo(matricula);
        if (v == null) {
            throw new DataAccessException();
        }
        Contribuyente cn = contribuyente(dniNuevo);
        if (cn == null) {
            throw new DataAccessException();
        }

        c.getVehiculos().remove(v);
        cn.getVehiculos().add(v);

        return true;
    }

    @Override
    public Contribuyente contribuyente(String dni) throws DataAccessException {
        Contribuyente c = contribuyentesDAO.contribuyente(dni);
        /** 
        if (c == null) {
            throw new DataAccessException();
        }
        */
        return c;
    }

    @Override
    public Vehiculo vehiculo(String matricula) throws DataAccessException {
        Vehiculo v = vehiculosDAO.vehiculoPorMatricula(matricula);
        if (v == null) {
            throw new DataAccessException();
        }
        return v;
    }
}