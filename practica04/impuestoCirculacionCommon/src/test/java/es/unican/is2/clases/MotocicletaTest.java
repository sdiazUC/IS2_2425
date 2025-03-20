package es.unican.is2.clases;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.excepciones.OperacionNoValidaException;


class MotocicletaTest {

    private Motocicleta moto1, moto2, moto3, moto4, moto5, moto6, moto7, moto8, moto9, moto10, moto11, moto12, moto13;
    
    @BeforeEach
    void setUp() {
        moto1 = new Motocicleta(1, "1234ABC", LocalDate.now().minusYears(26), TipoMotor.GASOLINA, 100);
        
    }

    @Test
    void testImpuesto() {
        
        moto2 = new Motocicleta(2, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 100);
        moto3 = new Motocicleta(3, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 200);
        moto4 = new Motocicleta(4, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 400);
        moto5 = new Motocicleta(5, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 750);
        moto6 = new Motocicleta(6, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 1200);
        moto7 = new Motocicleta(7, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.ELECTRICO, 100);
        moto8 = new Motocicleta(8, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.HIBRIDO, 100);
        moto9 = new Motocicleta(9, "1234ABC", LocalDate.now().minusYears(0), TipoMotor.GAS, 100);
        moto10 = new Motocicleta(10, "1234ABC", LocalDate.now().minusYears(2), TipoMotor.GAS, 100);
        moto11 = new Motocicleta(11, "1234ABC", LocalDate.now().minusYears(5), TipoMotor.HIBRIDO, 100);
        
        assertEquals(0.0, moto1.precioImpuesto(), 0.01);
        assertEquals(8.0, moto2.precioImpuesto(), 0.01); 
        assertEquals(15.0, moto3.precioImpuesto(), 0.01);
        assertEquals(30.0, moto4.precioImpuesto(), 0.01);
        assertEquals(60.0, moto5.precioImpuesto(), 0.01);
        assertEquals(120.0, moto6.precioImpuesto(), 0.01); 
        assertEquals(2.0, moto7.precioImpuesto(), 0.01);
        assertEquals(2.0, moto8.precioImpuesto(), 0.01);
        assertEquals(4.0, moto9.precioImpuesto(), 0.01); 
        assertEquals(8.0, moto10.precioImpuesto(), 0.01); 
        assertEquals(8.0, moto11.precioImpuesto(), 0.01); 
    }


    @Test
    void testConstructor() {
        assertEquals(100, moto1.getCilindrada());
        assertEquals(TipoMotor.GASOLINA, moto1.getMotor());
        assertEquals("1234ABC", moto1.getMatricula());
        assertEquals(LocalDate.now().minusYears(26), moto1.getFechaMatriculacion());
        assertEquals(1, moto1.getId());
        assertThrows(OperacionNoValidaException.class, ()-> moto12 = new Motocicleta(12, "1234ABC", LocalDate.now().minusYears(5), TipoMotor.HIBRIDO, -100));
        assertThrows(OperacionNoValidaException.class, ()-> moto13 = new Motocicleta(12, "1234ABC", LocalDate.now().minusYears(-5), TipoMotor.HIBRIDO, 100));
    }
}
