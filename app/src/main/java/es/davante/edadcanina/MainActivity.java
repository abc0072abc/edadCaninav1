package es.davante.edadcanina;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    private EditText ageEdit;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Declaramos variables
        ageEdit = findViewById(R.id.age_input);
        resultText = findViewById(R.id.result_text);
        Button button1 = findViewById(R.id.btn_calc);


        button1.setOnClickListener(view -> calcularEdad());

    }

    private void calcularEdad() {
        // 1. Leer el texto quitando espacios
        String ageString = ageEdit.getText().toString().trim();

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
        ageEdit.setError(null);

        int dogAge = age * FACTOR_CANINO;

        resultText.setText(getString(R.string.tu_edad_format, dogAge));
    }

    private void mostrarError(String mensaje) {
        // 1. Mostrar el mensaje en el EditText (setError)
        ageEdit.setError(mensaje);

        // 2. Poner el foco en el EditText (requestFocus)
        ageEdit.requestFocus();

        // 3. Borrar el resultado anterior del TextView
        resultText.setText("");
    }
}