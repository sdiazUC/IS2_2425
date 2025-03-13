package es.unican.is2.clases;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TurismoTest {
    @Test
    public void testGetPotencia() {
        Turismo t1 = new Turismo(1, "5678DEF", LocalDate.now().minusYears(1), TipoMotor.GASOLINA, 5);
        assertEquals(5, t1.getPotencia());
    }

    @Test
    public void testPrecioImpuesto() {
        LocalDate currentDate = LocalDate.now();

        // Casos de potencia baja
        Turismo t1 = new Turismo(1, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, -3);
        assertEquals(25, t1.precioImpuesto());

        Turismo t2 = new Turismo(2, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, 0);
        assertEquals(25, t2.precioImpuesto());

        Turismo t3 = new Turismo(3, "5678DEF", currentDate.minusYears(1), TipoMotor.ELECTRICO, 3);
        assertEquals(6.25, t3.precioImpuesto());

        Turismo t4 = new Turismo(4, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, 7.99);
        assertEquals(25, t4.precioImpuesto());

        // Casos de potencia entre 8 y 12
        Turismo t5 = new Turismo(5, "5678DEF", currentDate.minusYears(1), TipoMotor.GAS, 8);
        assertEquals(67, t5.precioImpuesto());

        Turismo t6 = new Turismo(6, "5678DEF", currentDate.minusYears(27), TipoMotor.GASOLINA, 10);
        assertEquals(0, t6.precioImpuesto());

        Turismo t7 = new Turismo(7, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, 11.99);
        assertEquals(67, t7.precioImpuesto());

        // Casos de potencia entre 12 y 16
        Turismo t8 = new Turismo(8, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, 12);
        assertEquals(143, t8.precioImpuesto());

        Turismo t9 = new Turismo(9, "5678DEF", currentDate.minusYears(3), TipoMotor.HIBRIDO, 14);
        assertEquals(35.75, t9.precioImpuesto());

        Turismo t10 = new Turismo(10, "5678DEF", currentDate.minusYears(6), TipoMotor.HIBRIDO, 15.99);
        assertEquals(143, t10.precioImpuesto());

        // Casos de potencia entre 16 y 20
        Turismo t11 = new Turismo(11, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, 16);
        assertEquals(178, t11.precioImpuesto());

        Turismo t12 = new Turismo(12, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, 18);
        assertEquals(178, t12.precioImpuesto());

        Turismo t13 = new Turismo(13, "5678DEF", currentDate.minusMonths(6), TipoMotor.GAS, 19.99);
        assertEquals(89, t13.precioImpuesto());

        // Casos de potencia mayor a 20
        Turismo t14 = new Turismo(14, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, 20);
        assertEquals(223, t14.precioImpuesto());

        Turismo t15 = new Turismo(15, "5678DEF", currentDate.minusYears(1), TipoMotor.GASOLINA, 25);
        assertEquals(223, t15.precioImpuesto());
    }
}
