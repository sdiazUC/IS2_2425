package es.unican.is2.DAOs;

import es.unican.is2.clases.*;
import es.unican.is2.excepciones.*;
import es.unican.is2.interfaces.*;
import es.unican.is2.mappers.*;
import es.unican.is2.services.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que implementa la capa DAO de acceso a vehiculos.
 * Utiliza almacenamiento en base de datos H2 en memoria.
 */
public class VehiculosDAO implements IVehiculosDAO {

	@Override
	public Vehiculo creaVehiculo(Vehiculo v) throws DataAccessException {
		String insertStatement= null;
		if (v instanceof Turismo) {
			Turismo t = (Turismo) v;
			insertStatement = String.format(
				"insert into Vehiculos(type, matricula, fechaMatricula, motor, potencia) values ('%s', '%s', '%s', '%s', %d)",
				t.getClass().getSimpleName(),
				t.getMatricula(),
				t.getFechaMatriculacion().toString(),
				t.getMotor().toString(),
				t.getPotencia());
		} else if (v instanceof Motocicleta) {
			Motocicleta m = (Motocicleta) v;
			insertStatement = String.format(
				"insert into Vehiculos(type, matricula, fechaMatricula, motor, cilindrada) values ('%s', '%s', '%s', '%s', %i)",
				m.getClass().getSimpleName(),
				m.getMatricula(),
				m.getFechaMatriculacion().toString(),
				m.getMotor().toString(),
				m.getCilindrada());
		}
		H2ServerConnectionManager.executeSqlStatement(insertStatement);
		return v;
	}

	@Override
	public Vehiculo vehiculoPorMatricula(String matricula) throws DataAccessException {
		Vehiculo result = null;
		Connection con = H2ServerConnectionManager.getConnection();
		try {
			Statement statement = con.createStatement();
			String statementText = "select * from vehiculos where matricula = '"+ matricula+"'";
			ResultSet results = statement.executeQuery(statementText);
			if (results.next()) {
				result = VehiculoMapper.toVehiculo(results);
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
	public List<Vehiculo> vehiculos() throws DataAccessException {
		List<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
		Connection con = H2ServerConnectionManager.getConnection();
		try {
			Statement statement = con.createStatement();
			String statementText = "select * from Vehiculos";
			ResultSet results = statement.executeQuery(statementText);
			// Procesamos cada fila como vehiculo independiente
			while (results.next()) {
				vehiculos.add(VehiculoMapper.toVehiculo(results));
			}
			statement.close();
		} catch (SQLException e) {
			// System.out.println(e);
			throw new DataAccessException();
		}

		return vehiculos;
	}

	@Override
	public Vehiculo vehiculo(long id) throws DataAccessException {
		Vehiculo result = null;
		Connection con = H2ServerConnectionManager.getConnection();
		try {
			Statement statement = con.createStatement();
			String statementText = "select * from vehiculos where id = "+ id;
			ResultSet results = statement.executeQuery(statementText);
			if (results.next()) {
				result = VehiculoMapper.toVehiculo(results);
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
	public Vehiculo eliminaVehiculo(String matricula) throws DataAccessException {
		Vehiculo vehiculoAEliminar = vehiculoPorMatricula(matricula);
		if (vehiculoAEliminar == null) {
			return null;
		}

		Connection con = H2ServerConnectionManager.getConnection();

		try {
			Statement statement = con.createStatement();
			String deleteStatement = String.format(
				"delete from Vehiculos where matricula = '%s'",
				matricula.toString()
			);
			statement.executeUpdate(deleteStatement);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}

		return vehiculoAEliminar;
	}

	@Override
	public Vehiculo actualizaVehiculo(Vehiculo nuevo) throws DataAccessException {
		if (vehiculoPorMatricula(nuevo.getMatricula()) == null) {
			return null;
		}

		Connection con = H2ServerConnectionManager.getConnection();

		try {
			Statement statement = con.createStatement();
			String updateStatement;
			if (nuevo instanceof Turismo) {
				Turismo t = (Turismo) nuevo;
				updateStatement = String.format(
					"update Vehiculos set fechaMatricula = '%s', motor = '%s', potencia = %d where matricula = '%s'",
					t.getFechaMatriculacion().toString(),
					t.getMotor().toString(),
					t.getPotencia(),
					t.getMatricula()
				);
			} else if (nuevo instanceof Motocicleta) {
				Motocicleta m = (Motocicleta) nuevo;
				updateStatement = String.format(
					"update Vehiculos set fechaMatricula = '%s', motor = '%s', cilindrada = %d where matricula = '%s'",
					m.getFechaMatriculacion().toString(),
					m.getMotor().toString(),
					m.getCilindrada(),
					m.getMatricula()
				);
			} else {
				return null;
			}

			statement.executeUpdate(updateStatement);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}

		return nuevo;
	}
}
