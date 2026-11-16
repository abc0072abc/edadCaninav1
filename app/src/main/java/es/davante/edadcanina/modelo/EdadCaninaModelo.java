package es.davante.edadcanina.modelo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import es.davante.edadcanina.BR;

public class EdadCaninaModelo extends BaseObservable {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;
    @Bindable
    public String getEdadTexto() {
        return edadTexto;
    }

    public void setEdadTexto(String edadTexto) {
        if (this.edadTexto.equals(edadTexto)) {
            return;
        }

        this.edadTexto = edadTexto;
        setEdadHumana(0);
        setValidacion(Validacion.SIN_CALCULAR);
        notifyPropertyChanged(BR.edadTexto);
    }
    @Bindable
    public int getEdadHumana() {
        return edadHumana;
    }
    @Bindable
    public Validacion getValidacion() {
        return validacion;
    }

    private void setEdadHumana(int edadHumana) {
        if (this.edadHumana == edadHumana) {
            return;
        }

        this.edadHumana = edadHumana;
        notifyPropertyChanged(BR.edadHumana);
    }

    private void setValidacion(Validacion validacion) {
        if (this.validacion == validacion) {
            return;
        }

        this.validacion = validacion;
        notifyPropertyChanged(BR.validacion);
    }

    public Validacion calcular() {

        String texto = edadTexto.trim();

        if (texto.isEmpty()) {
            setValidacion(Validacion.VACIO);
            return validacion;
        }

        int edad;

        try {
            edad = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            setValidacion(Validacion.NO_ES_NUMERO);
            return validacion;
        }

        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            setValidacion(Validacion.FUERA_DE_RANGO);
            return validacion;
        }

        setEdadHumana(edad * FACTOR_CANINO);
        setValidacion(Validacion.CORRECTO);

        return validacion;
    }
}