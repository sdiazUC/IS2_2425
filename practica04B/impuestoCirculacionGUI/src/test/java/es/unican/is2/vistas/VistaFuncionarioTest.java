package es.unican.is2.vistas;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import es.unican.is2.DAOs.*;
import es.unican.is2.clases.GestionImpuestoCirculacion;

public class VistaFuncionarioTest {

    private VistaFuncionario vista;
    private GestionImpuestoCirculacion negocio;
    private ContribuyentesDAO contribuyentesDAO;
    private VehiculosDAO vehiculosDAO;
    private FrameFixture ventana;

    @BeforeEach
    public void setUp() {
        // Inicializamos las capas
        contribuyentesDAO = new ContribuyentesDAO();
        vehiculosDAO = new VehiculosDAO();
        negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
        vista = new VistaFuncionario(negocio);
        ventana = new FrameFixture(BasicRobot.robotWithCurrentAwtHierarchy(), vista);
        vista.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        ventana.cleanUp();
    }

    @Test
    public void testExisteContribuyente() {
        ventana.textBox("txtDniContribuyente").setText("11111111A");
        ventana.button("btnBuscar").click();

        assertEquals("Juan Lopez Perez", ventana.textBox("txtNombreContribuyente").text());
        assertEquals("206,75", ventana.textBox("txtTotalContribuyente").text());
    }

    @Test
    public void testNoExisteContribuyente() {
        ventana.textBox("txtDniContribuyente").setText("11111111Z"); // Este DNI no existe en la BBDD
        ventana.button("btnBuscar").click();

        assertEquals("DNI Incorrecto", ventana.textBox("txtNombreContribuyente").text());
        assertEquals("0", ventana.textBox("txtTotalContribuyente").text());
    }

    @Test 
    public void testBuscarSinDNI() {
        ventana.textBox("txtDniContribuyente").setText(""); // No indicamos ningún DNI
        ventana.button("btnBuscar").click();

        assertEquals("DNI Incorrecto", ventana.textBox("txtNombreContribuyente").text());
        assertEquals("0", ventana.textBox("txtTotalContribuyente").text());
    }

    @Test
    public void testFormatoIncorrecto() {
        ventana.textBox("txtDniContribuyente").setText("123XYZ"); //El formato de este DNI es incorrecto
        ventana.button("btnBuscar").click();

        assertEquals("DNI Incorrecto", ventana.textBox("txtNombreContribuyente").text());
        assertEquals("0", ventana.textBox("txtTotalContribuyente").text());
    }


}
