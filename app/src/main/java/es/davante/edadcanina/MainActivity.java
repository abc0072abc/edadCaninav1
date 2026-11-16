package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnCalc.setOnClickListener(view -> calcularEdad());
    }

    private void calcularEdad() {
        // 1. Leer el texto quitando espacios
        String ageString = binding.ageInput.getText().toString().trim();

        // 2. Comprobar si está vacío
        if (ageString.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        // 3. Convertir el texto a entero
        int age;

        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_no_numero));
            return;
        }

        // 4. Comprobar que la edad está dentro del rango permitido
        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
            return;
        }

        // 5. Calcular y mostrar el resultado
        binding.ageInput.setError(null);

        int dogAge = age * FACTOR_CANINO;

        binding.resultText.setText(getString(R.string.tu_edad_format, dogAge));
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