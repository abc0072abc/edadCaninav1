package es.davante.edadcanina.controlador;

import es.davante.edadcanina.modelo.EdadCaninaModelo;

// Controlador que se encarga de gestionar las acciones que realiza el usuario.
public class EdadCaninaControlador {

    // Modelo sobre el que actuará el controlador.
    private final EdadCaninaModelo modelo;

    // Recibimos el modelo que utilizará el controlador.
    public EdadCaninaControlador(EdadCaninaModelo modelo) {
        this.modelo = modelo;
    }

    // Ordena al modelo realizar la validación y el cálculo.
    public void calcular() {
        modelo.calcular();
    }

    // Borra los datos del modelo y devuelve la aplicación a su estado inicial.
    public void limpiar() {
        modelo.setEdadTexto("");
    }
}