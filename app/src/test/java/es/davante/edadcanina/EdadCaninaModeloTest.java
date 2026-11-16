package es.davante.edadcanina;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class EdadCaninaModeloTest {

    @Test
    public void edadCorrecta() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("6");

        assertEquals(Validacion.CORRECTO, modelo.calcular());
        assertEquals(42, modelo.getEdadHumana());
    }

    @Test
    public void edadVacia() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("");

        assertEquals(Validacion.VACIO, modelo.calcular());
    }

    @Test
    public void noEsNumero() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("error");

        assertEquals(Validacion.NO_ES_NUMERO, modelo.calcular());
    }

    @Test
    public void edadFueraDeRangoPorAbajo() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("0");

        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }

    @Test
    public void edadFueraDeRangoPorArriba() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("31");

        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }
}