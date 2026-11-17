package es.davante.edadcanina;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.controlador.EdadCaninaControlador;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;


public class MainActivity extends AppCompatActivity {

    // Claves utilizadas para guardar y recuperar el estado de la pantalla.
    private static final String KEY_EDAD_TEXTO = "edad_texto";
    private static final String KEY_CALCULADA = "calculada";

    private ActivityMainBinding binding;
    // Modelo que contiene los datos y la lógica de cálculo.
    private EdadCaninaModelo modelo;

    // Controlador que gestiona las acciones de la interfaz.
    private EdadCaninaControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // DataBinding conecta la Activity con los elementos definidos en el XML.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Creamos el modelo y lo vinculamos al layout para que DataBinding pueda observar sus cambios.
        modelo = new EdadCaninaModelo();

        // Comprobamos si Android nos ha entregado un estado anterior.
        if (savedInstanceState != null) {

            // Recuperamos el texto que tenía introducido el usuario.
            String edadTexto = savedInstanceState.getString(KEY_EDAD_TEXTO, "");

            // Restauramos el texto en el modelo.
            modelo.setEdadTexto(edadTexto);

            // Recuperamos si el usuario había realizado correctamente el cálculo.
            boolean calculada = savedInstanceState.getBoolean(KEY_CALCULADA, false);

            // Si había un cálculo válido, volvemos a calcularlo para reconstruir el resultado.
            if (calculada) {
                modelo.calcular();
            }
        }

        // Creamos el controlador y le pasamos el modelo.
        controlador = new EdadCaninaControlador(modelo);

        // Vinculamos el modelo y el controlador con DataBinding.
        binding.setModelo(modelo);
        binding.setControlador(controlador);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Guardamos el texto que tenía introducido el usuario.
        outState.putString(KEY_EDAD_TEXTO, modelo.getEdadTexto());

        // Guardamos si el usuario ya había realizado el cálculo.
        outState.putBoolean(KEY_CALCULADA, modelo.getValidacion() == Validacion.CORRECTO);
    }

}