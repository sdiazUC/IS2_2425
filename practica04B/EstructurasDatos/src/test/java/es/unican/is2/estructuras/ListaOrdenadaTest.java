package es.unican.is2.estructuras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListaOrdenadaTest {
    private ListaOrdenada<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new ListaOrdenada<>();
    }

    @Test
    public void testGet() {
        // get() con lista vacía
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));

        // get() con 1 elemento
        sut.add(3); // [3]
        assertEquals(3, sut.get(0));

        // get() con n elementos (n > 1)
        sut.add(5); // [3, 5]
        sut.add(9); // [3, 5, 9]
        sut.add(6); // [3, 5, 6, 9]
        sut.add(8); // [3, 5, 6, 8, 9]
        sut.add(13); // [3, 5, 6, 8, 9, 13]
        assertEquals(9, sut.get(4));
        assertEquals(13, sut.get(5));

        // get() con índice negativo
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));

        // get() con índice mayor que el tamaño de la lista
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(7));
    }

    @Test
    public void testAdd() {
        // add() con lista vacía
        sut.add(1);
        assertEquals(1, sut.get(0));

        // add() con 1 elemento
        sut.add(3);
        assertEquals(3, sut.get(1));


        // add() con n elementos
        sut.add(5);
        sut.add(9);
        assertEquals(5, sut.get(2));

        // add() de un elemento null
        assertThrows(NullPointerException.class, () -> sut.add(null));
    }

    @Test
    public void testRemove() {
        // remove() con lista vacía
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(0));

        // remove() con 1 elemento
        sut.add(3);
        assertEquals(3, sut.remove(0));
        assertEquals(0, sut.size());

        // remove() con n elementos
        sut.add(3);
        sut.add(5);
        sut.add(9);
        assertEquals(5, sut.remove(1));
        assertEquals(2, sut.size());
    }

    @Test
    public void testSize() {
        // size() con lista vacía
        assertEquals(0, sut.size());

        // size() con 1 elemento
        sut.add(3);
        assertEquals(1, sut.size());

        // size() con n elementos
        sut.add(5);
        sut.add(9);
        assertEquals(3, sut.size());
    }

    @Test
    public void testClear() {
        // clear() con lista vacía
        sut.clear();
        assertEquals(0, sut.size());

        // clear() con 1 elemento
        sut.add(3);
        sut.clear();
        assertEquals(0, sut.size());

        // clear() con n elementos
        sut.add(3);
        sut.add(5);
        sut.add(9);
        sut.clear();
        assertEquals(0, sut.size());
    }
}
