package es.davante.edadcanina.vista;

import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.Validacion;

// Adaptadores que conectan los datos del modelo con cambios visuales de la interfaz.
public class AdaptadoresVista {

    // Recibe el estado de validación y muestra el mensaje de error correspondiente.
    @BindingAdapter("errorValidacion")
    public static void mostrarError(EditText editText, Validacion validacion) {

        // Al principio no mostramos ningún mensaje de error.
        String mensaje = null;

        // Convertimos el estado del modelo en el mensaje que verá el usuario.
        switch (validacion) {

            case VACIO:
                mensaje = editText.getContext().getString(R.string.error_vacio);
                break;

            case NO_ES_NUMERO:
                mensaje = editText.getContext().getString(R.string.error_no_numero);
                break;

            case FUERA_DE_RANGO:
                mensaje = editText.getContext().getString(R.string.error_rango, 1, 30);
                break;

            // Estos estados no necesitan mostrar ningún mensaje de error.
            case CORRECTO:
            case SIN_CALCULAR:
                break;
        }

        // Mostramos el mensaje de error en el campo de texto.
        editText.setError(mensaje);

        // Si existe un error, devolvemos el foco al campo para que el usuario pueda corregirlo.
        if (mensaje != null) {
            editText.requestFocus();
        }
    }
}