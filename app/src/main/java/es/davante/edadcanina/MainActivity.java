package es.davante.edadcanina;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.controlador.EdadCaninaControlador;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;


public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

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

        // Creamos el controlador y le pasamos el modelo.
        controlador = new EdadCaninaControlador(modelo);

        // Vinculamos el modelo y el controlador con DataBinding.
        binding.setModelo(modelo);
        binding.setControlador(controlador);
    }

}