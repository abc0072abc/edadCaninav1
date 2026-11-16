package es.davante.edadcanina.modelo;

public class EdadCaninaModelo {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;

    public String getEdadTexto() {
        return edadTexto;
    }

    public void setEdadTexto(String edadTexto) {
        this.edadTexto = edadTexto;
    }

    public int getEdadHumana() {
        return edadHumana;
    }

    public Validacion getValidacion() {
        return validacion;
    }

    public Validacion calcular() {

        String texto = edadTexto.trim();

        if (texto.isEmpty()) {
            validacion = Validacion.VACIO;
            return validacion;
        }

        int edad;

        try {
            edad = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            validacion = Validacion.NO_ES_NUMERO;
            return validacion;
        }

        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            validacion = Validacion.FUERA_DE_RANGO;
            return validacion;
        }

        edadHumana = edad * FACTOR_CANINO;
        validacion = Validacion.CORRECTO;

        return validacion;
    }
}