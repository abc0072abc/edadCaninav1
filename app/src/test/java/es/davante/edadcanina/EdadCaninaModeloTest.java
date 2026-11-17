package es.davante.edadcanina;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class EdadCaninaModeloTest {

    // Comprueba que una edad válida se calcula correctamente.
    @Test
    public void edadCorrecta() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("6");

        assertEquals(Validacion.CORRECTO, modelo.calcular());
        assertEquals(42, modelo.getEdadHumana());
    }

    // Comprueba que una edad vacía devuelve el estado correspondiente.
    @Test
    public void edadVacia() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("");

        assertEquals(Validacion.VACIO, modelo.calcular());
    }

    // Comprueba que un texto que no es un número devuelve el estado correspondiente.
    @Test
    public void noEsNumero() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("error");

        assertEquals(Validacion.NO_ES_NUMERO, modelo.calcular());
    }

    // Comprueba que una edad menor que el mínimo no es válida.
    @Test
    public void edadFueraDeRangoPorAbajo() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("0");

        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }

    // Comprueba que una edad mayor que el máximo no es válida.
    @Test
    public void edadFueraDeRangoPorArriba() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();

        modelo.setEdadTexto("31");

        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }
}