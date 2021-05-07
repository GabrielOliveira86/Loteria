package percy.fatec.project.loteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    EditText apostaEditText1;
    EditText apostaEditText2;
    EditText apostaEditText3;
    EditText apostaEditText4;
    EditText apostaEditText5;

    TextView facaJogoTextView;
    TextView numerosSorteadosTextView;

    Button sortearButton;

    TextView resultadoTextView1;
    TextView resultadoTextView2;
    TextView resultadoTextView3;
    TextView resultadoTextView4;
    TextView resultadoTextView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apostaEditText1 = (EditText)findViewById(R.id.apostaEditText1);
        apostaEditText2 = (EditText)findViewById(R.id.apostaEditText2);
        apostaEditText3 = (EditText)findViewById(R.id.apostaEditText3);
        apostaEditText4 = (EditText)findViewById(R.id.apostaEditText4);
        apostaEditText5 = (EditText)findViewById(R.id.apostaEditText5);

        apostaEditText1.setOnFocusChangeListener(this);
        apostaEditText2.setOnFocusChangeListener(this);
        apostaEditText3.setOnFocusChangeListener(this);
        apostaEditText4.setOnFocusChangeListener(this);
        apostaEditText5.setOnFocusChangeListener(this);

        resultadoTextView1 = (TextView)findViewById(R.id.resultadoTextView1);
        resultadoTextView2 = (TextView)findViewById(R.id.resultadoTextView2);
        resultadoTextView3 = (TextView)findViewById(R.id.resultadoTextView3);
        resultadoTextView4 = (TextView)findViewById(R.id.resultadoTextView4);
        resultadoTextView5 = (TextView)findViewById(R.id.resultadoTextView5);

        facaJogoTextView = (TextView)findViewById(R.id.facaJogoTextView);
        numerosSorteadosTextView = (TextView)findViewById(R.id.numerosSorteadosTextView);
        sortearButton = (Button)findViewById(R.id.sortearButton);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.apostaEditText1:
                String valor = apostaEditText1.getText().toString();
                if (!valor.isEmpty()) {
                    validateEnteredNumber(valor);
                }
                break;
            case R.id.apostaEditText2:
                String valor2 = apostaEditText2.getText().toString();
                if (!valor2.isEmpty()) {
                    validateEnteredNumber(valor2);
                }
                break;
            case R.id.apostaEditText3:
                String valor3 = apostaEditText3.getText().toString();
                if (!valor3.isEmpty()) {
                    validateEnteredNumber(valor3);
                }
                break;
            case R.id.apostaEditText4:
                String valor4 = apostaEditText4.getText().toString();
                if (!valor4.isEmpty()) {
                    validateEnteredNumber(valor4);
                }
                break;
            case R.id.apostaEditText5:
                String valor5 = apostaEditText5.getText().toString();
                if (!valor5.isEmpty()) {
                    validateEnteredNumber(valor5);
                }
                break;
        }
    }

    private void validateEnteredNumber(String numero) {
        Integer valor = Integer.parseInt(numero);
        if (valor > 50 || valor < 0) {
            Toast.makeText(MainActivity.this, "Digite valores entre 1 e 50",
                    Toast.LENGTH_LONG).show();
        }
    }
}