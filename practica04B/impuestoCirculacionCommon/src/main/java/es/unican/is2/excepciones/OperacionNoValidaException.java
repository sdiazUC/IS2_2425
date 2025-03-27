package es.unican.is2.excepciones;

@SuppressWarnings("serial")
public class OperacionNoValidaException extends RuntimeException {

	public OperacionNoValidaException(String string) {
		super(string);
	}

}
