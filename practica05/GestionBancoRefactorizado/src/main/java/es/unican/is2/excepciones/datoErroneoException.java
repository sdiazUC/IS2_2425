package es.unican.is2.excepciones;

@SuppressWarnings("serial")
public class datoErroneoException extends RuntimeException {
	
	public datoErroneoException (String mensaje) {
		super(mensaje);
	}

}
