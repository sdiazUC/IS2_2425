package es.unican.is2.estructuras;

import java.util.ArrayList;
import java.util.List;
import es.unican.is2.adt.IListaOrdenada;

/**
 * Clase que implementa el ADT ListaOrdenada
 */
public class ListaOrdenada<E extends Comparable<E>> implements IListaOrdenada<E> {

	private List<E> lista = new ArrayList<E>();

	public E get(int indice) {
		return lista.get(indice);
	}

	public void add(E elemento) {
		// esto no es necesario porque el método add de la clase List ya lanza una excepción
		// si el elemento es null
		if (elemento == null) {
			throw new NullPointerException();
		}

		int indice = 0;
		if (lista.size() != 0) {
			while (indice < lista.size() && elemento.compareTo(lista.get(indice)) > 0) {
				indice++;
			}
		}
		lista.add(indice, elemento);
	}

	public E remove(int indice) {
		E borrado = lista.remove(indice);
		return borrado;
	}

	public int size() {
		return lista.size();
	}

	public void clear() {
		// for (int i=0; i<lista.size(); i++) {
		// 	lista.remove(i);
		// }

		// método corregido
		for (int i=lista.size() - 1; i >= 0; i--) {
			lista.remove(i);
		}

		// otra opción sería usar el método propio de la clase List
		// lista.clear();
	}
}
