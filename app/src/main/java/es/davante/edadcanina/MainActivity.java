package es.davante.edadcanina;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;


public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;
    private ActivityMainBinding binding;
    private EdadCaninaModelo modelo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        modelo = new EdadCaninaModelo();

        binding.btnCalc.setOnClickListener(view -> calcularEdad());
    }

    private void calcularEdad() {

        String ageString = binding.ageInput.getText().toString().trim();

        modelo.setEdadTexto(ageString);

        Validacion validacion = modelo.calcular();

        switch (validacion) {
            case VACIO:
                mostrarError(getString(R.string.error_vacio));
                break;

            case NO_ES_NUMERO:
                mostrarError(getString(R.string.error_no_numero));
                break;

            case FUERA_DE_RANGO:
                mostrarError(getString(R.string.error_rango, 1, 30));
                break;

            case CORRECTO:
                binding.ageInput.setError(null);
                binding.resultText.setText(getString(R.string.tu_edad_format, modelo.getEdadHumana()));
                break;

            case SIN_CALCULAR:
                break;
        }
    }

    private void mostrarError(String mensaje) {
        // 1. Mostrar el mensaje en el EditText (setError)
        binding.ageInput.setError(mensaje);

        // 2. Poner el foco en el EditText (requestFocus)
        binding.ageInput.requestFocus();

        // 3. Borrar el resultado anterior del TextView
        binding.resultText.setText("");

    }
}