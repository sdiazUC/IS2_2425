package es.unican.is2.clases;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.excepciones.datoErroneoException;
import es.unican.is2.excepciones.saldoInsuficienteException;



public class CuentaAhorroTest {
	private CuentaAhorro sut;
	private static Movimiento m1, m2, m3;

	@BeforeAll
	public static void inicializaAuxiliares() {
		m1 = new Movimiento("Concepto1", LocalDateTime.now(), 100);
		m2 = new Movimiento("Concepto2", LocalDateTime.now(), 200);
		m3 = new Movimiento("Concepto3", LocalDateTime.now(), 1500);
	}

	@BeforeEach
	public void inicializa() {
		sut = new CuentaAhorro("794311");
	}

	@Test
	public void testConstructor() {
	    assertEquals(1000, sut.getLimiteDebito());
	    assertEquals(0, sut.getMovimientos().size());
	    assertEquals("794311", sut.getNumCuenta());
	}

	@Test
	public void testGetSaldoYAddMovimiento() {
	    assertEquals(0, sut.getSaldo());

	    sut.addMovimiento(m1);
	    assertEquals(100, sut.getSaldo());
	    assertEquals(1, sut.getMovimientos().size());

	    sut.addMovimiento(m2);
	    sut.addMovimiento(m3);
	    assertEquals(1800, sut.getSaldo());
	    assertEquals(3, sut.getMovimientos().size());
	}

	@Test
	public void testRetirarSinConcepto() {
	    try {
	        sut.retirar(-10);
	        fail("Debe lanzar DatoErroneoException");
	    } catch (datoErroneoException e) {
	    } catch (saldoInsuficienteException e) {
	        fail("Debe lanzar DatoErroneoException");
	    }

	    sut.addMovimiento(m1);

	    try {
	        sut.retirar(50);
	        assertEquals(50, sut.getSaldo());
	        assertEquals(2, sut.getMovimientos().size());
	        assertEquals("Retirada de efectivo", sut.getMovimientos().get(1).getConcepto());
	    } catch (datoErroneoException e) {
	        fail("No debe lanzar DatoErroneoException");
	    } catch (saldoInsuficienteException e) {
	        fail("No debe lanzar SaldoInsuficienteException");
	    }

	    try {
	        sut.retirar(100);
	        fail("Debe lanzar SaldoInsuficienteException");
	    } catch (datoErroneoException e) {
	        fail("Debe lanzar SaldoInsuficienteException");
	    } catch (saldoInsuficienteException e) {
	    }
	}

	@Test
	public void testIngresarSinConcepto() {
	    try {
	        sut.ingresar(-1);
	        fail("Debe lanzar DatoErroneoException");
	    } catch (datoErroneoException e) {
	    }

	    try {
	        sut.ingresar(0.01);
	        assertEquals(0.01, sut.getSaldo());
	        assertEquals(1, sut.getMovimientos().size());
	        assertEquals("Ingreso en efectivo", sut.getMovimientos().get(0).getConcepto());

	        sut.ingresar(100);
	        assertEquals(100.01, sut.getSaldo());
	        assertEquals(2, sut.getMovimientos().size());
	    } catch (datoErroneoException e) {
	        fail("No debe lanzar la excepción");
	    }
	}

	@Test
	public void testIngresarConConcepto() {
	    try {
	        sut.ingresar("Ingreso", -1);
	        fail("Debe lanzar DatoErroneoException");
	    } catch (datoErroneoException e) {
	    }

	    try {
	        sut.ingresar("Ingreso1", 0.01);
	        assertEquals(0.01, sut.getSaldo());
	        assertEquals(1, sut.getMovimientos().size());
	        assertEquals("Ingreso1", sut.getMovimientos().get(0).getConcepto());

	        sut.ingresar("Ingreso2", 100);
	        assertEquals(100.01, sut.getSaldo());
	        assertEquals(2, sut.getMovimientos().size());
	        assertEquals("Ingreso2", sut.getMovimientos().get(1).getConcepto());
	    } catch (datoErroneoException e) {
	        fail("No debe lanzar la excepción");
	    }
	}

	@Test
	public void testRetirarConConcepto() {
	    try {
	        sut.retirar("Retirada", -10);
	        fail("Debe lanzar DatoErroneoException");
	    } catch (datoErroneoException e) {
	    } catch (saldoInsuficienteException e) {
	        fail("Debería lanzar DatoErroneoException");
	    }

	    sut.addMovimiento(m1);

	    try {
	        sut.retirar("Retirada1", 50);
	        assertEquals(50, sut.getSaldo());
	        assertEquals(2, sut.getMovimientos().size());
	        assertEquals("Retirada1", sut.getMovimientos().get(1).getConcepto());
	    } catch (datoErroneoException e) {
	        fail("No debe lanzar DatoErroneoException");
	    } catch (saldoInsuficienteException e) {
	        fail("No debe lanzar SaldoInsuficienteException");
	    }

	    try {
	        sut.retirar("Retirada2", 100);
	        fail("Debe lanzar SaldoInsuficienteException");
	    } catch (datoErroneoException e) {
	        fail("Debe lanzar SaldoInsuficienteException");
	    } catch (saldoInsuficienteException e) {
	    }
	}

	@Test
	public void testAñadirTarjetas() {
	    sut = new CuentaAhorro("794311");
	    assertEquals(0, sut.getTarjetasAsociadas().size());

	    Debito tarjeta1 = new Debito("1234567890123456", "Titular1", "123", sut, LocalDate.of(2025, 12, 31));
	    Credito tarjeta2 = new Credito("1234567890123457", "Titular2", "123", sut, 1000, LocalDate.of(2025, 12, 31));

	    sut.setTarjetaDebito(tarjeta1);
	    assertEquals(1, sut.getTarjetasAsociadas().size());

	    sut.setTarjetaCredito(tarjeta2);
	    assertEquals(2, sut.getTarjetasAsociadas().size());
	}
}
