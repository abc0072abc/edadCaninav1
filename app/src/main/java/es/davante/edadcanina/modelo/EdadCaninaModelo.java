package es.davante.edadcanina.modelo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import es.davante.edadcanina.BR;

public class EdadCaninaModelo extends BaseObservable {
    // Constantes usadas para validar y calcular la edad
    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    // Datos que utiliza el modelo para realizar el cálculo y guardar su estado.
    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;

    // Devuelve la edad introducida por el usuario.
    @Bindable
    public String getEdadTexto() {
        return edadTexto;
    }

    // Actualiza la edad introducida y reinicia el resultado anterior.
    public void setEdadTexto(String edadTexto) {
        // Si el texto no ha cambiado, no notificamos nada
        if (this.edadTexto.equals(edadTexto)) {
            return;
        }

        this.edadTexto = edadTexto;
        // Al cambiar la edad introducida, el resultado anterior deja de ser válido
        setEdadHumana(0);
        setValidacion(Validacion.SIN_CALCULAR);
        notifyPropertyChanged(BR.edadTexto);
    }

    // Devuelve la edad humana calculada.
    @Bindable
    public int getEdadHumana() {
        return edadHumana;
    }

    // Devuelve el estado actual de la validación.
    @Bindable
    public Validacion getValidacion() {
        return validacion;
    }

    // Actualiza el resultado y notifica a DataBinding si ha cambiado.
    private void setEdadHumana(int edadHumana) {
        if (this.edadHumana == edadHumana) {
            return;
        }

        this.edadHumana = edadHumana;
        notifyPropertyChanged(BR.edadHumana);
    }

    // Actualiza el estado de validación y notifica a DataBinding si ha cambiado.
    private void setValidacion(Validacion validacion) {
        // Evitamos notificaciones innecesarias si el valor es el mismo.
        if (this.validacion == validacion) {
            return;
        }

        this.validacion = validacion;
        notifyPropertyChanged(BR.validacion);
    }

    public Validacion calcular() {
        // Quitamos los espacios antes de validar el texto
        String texto = edadTexto.trim();

        // Comprobamos si el campo está vacío
        if (texto.isEmpty()) {
            setValidacion(Validacion.VACIO);
            return validacion;
        }

        int edad;

        // Intentamos convertir el texto introducido en un número entero.
        try {
            edad = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            setValidacion(Validacion.NO_ES_NUMERO);
            return validacion;
        }

        // Comprobamos que la edad está dentro del rango permitido
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            setValidacion(Validacion.FUERA_DE_RANGO);
            return validacion;
        }

        // Si todo es correcto, calculamos la edad humana
        setEdadHumana(edad * FACTOR_CANINO);
        setValidacion(Validacion.CORRECTO);

        return validacion;
    }
}