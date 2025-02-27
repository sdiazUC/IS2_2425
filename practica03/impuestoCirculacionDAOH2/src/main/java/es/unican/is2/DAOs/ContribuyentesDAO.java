package es.unican.is2.DAOs;

import es.unican.is2.clases.*;
import es.unican.is2.excepciones.*;
import es.unican.is2.interfaces.*;
import es.unican.is2.mappers.*;
import es.unican.is2.services.H2ServerConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que implementa la capa DAO de acceso a contribuyentes.
 * Utiliza almacenamiento en base de datos H2 en memoria.
 */
public class ContribuyentesDAO implements IContribuyentesDAO {

	@Override
	public Contribuyente creaContribuyente(Contribuyente c) throws DataAccessException {
		String insertStatement = String.format(
				"insert into Contribuyentes(dni, nombre, apellido1, apellido2) values ('%s', '%s', '%s', '%s')",
				c.getDni(),
				c.getNombre(),
				c.getApellido1(),
				c.getApellido2());
		H2ServerConnectionManager.executeSqlStatement(insertStatement);
		return c;
	}

	@Override
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		Contribuyente result = null;
		Connection con = H2ServerConnectionManager.getConnection();
		try {
			Statement statement = con.createStatement();
			String statementText = "select * from contribuyentes where dni = '"+ dni+"'";
			ResultSet results = statement.executeQuery(statementText);
			if (results.next()) {
				result = procesaContribuyente(con,results);
			}
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}
		return result;

	}

	@Override
	public List<Contribuyente> contribuyentes() throws DataAccessException {
		List<Contribuyente> contribuyentes = new LinkedList<Contribuyente>();
		Connection con = H2ServerConnectionManager.getConnection();
		try {
			Statement statement = con.createStatement();
			String statementText = "select * from Contribuyentes";
			ResultSet results = statement.executeQuery(statementText);
			// Procesamos cada fila como vehiculo independiente
			while (results.next()) {
				contribuyentes.add(procesaContribuyente(con, results));
			}
			statement.close();
		} catch (SQLException e) {
			// System.out.println(e);
			throw new DataAccessException();
		}

		return contribuyentes;
	}


	private Contribuyente procesaContribuyente(Connection con, ResultSet results) throws SQLException, DataAccessException {
		Contribuyente result = ContribuyenteMapper.toContribuyente(results);
		// Cargamos los empleados de la tienda
		Statement statement = con.createStatement();
		String statementText = String.format("select * from Vehiculos where contribuyente_FK = '%s'", result.getDni());
		results = statement.executeQuery(statementText);
		while (results.next()) {
			result.getVehiculos().add(VehiculoMapper.toVehiculo(results));
		}
		statement.close();
		return result;
	}


	@Override
	public Contribuyente actualizaContribuyente(Contribuyente nuevo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contribuyente eliminaContribuyente(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
