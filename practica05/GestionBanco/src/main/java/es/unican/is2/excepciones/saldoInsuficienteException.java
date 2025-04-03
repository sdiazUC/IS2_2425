package es.unican.is2.excepciones;

@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException {

	public saldoInsuficienteException (String mensaje) {
		super(mensaje);
	}
}
