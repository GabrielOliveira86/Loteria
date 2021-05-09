package percy.fatec.project.loteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    int NUMERO_DEZENAS = 5;

    TextView facaJogoTextView;
    EditText apostaEditText1;
    EditText apostaEditText2;
    EditText apostaEditText3;
    EditText apostaEditText4;
    EditText apostaEditText5;
    Button sortearButton;

    ConstraintLayout numerosSorteadosLayout;
    TextView numerosSorteadosTextView;

    TextView resultadoTextView1;
    TextView resultadoTextView2;
    TextView resultadoTextView3;
    TextView resultadoTextView4;
    TextView resultadoTextView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setGameViewElements();
        setResultViewElements();
    }

    private void setGameViewElements() {
        facaJogoTextView = (TextView)findViewById(R.id.facaJogoTextView);
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

        sortearButton = (Button)findViewById(R.id.sortearButton);
        sortearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numerosSorteadosLayout.getVisibility() == View.GONE) {
                    ArrayList<Integer> sortedNumbers = lotterySort();
                    setSortedNumberOn(resultadoTextView1, sortedNumbers.get(0).intValue());
                    setSortedNumberOn(resultadoTextView2, sortedNumbers.get(1).intValue());
                    setSortedNumberOn(resultadoTextView3, sortedNumbers.get(2).intValue());
                    setSortedNumberOn(resultadoTextView4, sortedNumbers.get(3).intValue());
                    setSortedNumberOn(resultadoTextView5, sortedNumbers.get(4).intValue());
                    numerosSorteadosLayout.setVisibility(View.VISIBLE);

                    sortearButton.setText(R.string.resetar);
                } else {
                    numerosSorteadosLayout.setVisibility(View.GONE);
                    sortearButton.setText(R.string.sortear);
                }
            }
        });
    }

    private void setResultViewElements() {
        numerosSorteadosLayout = (ConstraintLayout)findViewById(R.id.sortedNumbersConstraintLayout);
        numerosSorteadosTextView = (TextView)findViewById(R.id.numerosSorteadosTextView);

        resultadoTextView1 = (TextView)findViewById(R.id.resultadoTextView1);
        resultadoTextView2 = (TextView)findViewById(R.id.resultadoTextView2);
        resultadoTextView3 = (TextView)findViewById(R.id.resultadoTextView3);
        resultadoTextView4 = (TextView)findViewById(R.id.resultadoTextView4);
        resultadoTextView5 = (TextView)findViewById(R.id.resultadoTextView5);

        numerosSorteadosLayout.setVisibility(View.GONE);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.apostaEditText1:
                validateEnteredNumber(apostaEditText1);
                break;
            case R.id.apostaEditText2:
                validateEnteredNumber(apostaEditText2);
                break;
            case R.id.apostaEditText3:
                validateEnteredNumber(apostaEditText3);
                break;
            case R.id.apostaEditText4:
                validateEnteredNumber(apostaEditText4);
                break;
            case R.id.apostaEditText5:
                validateEnteredNumber(apostaEditText5);
                break;
        }
    }

    private void validateEnteredNumber(EditText field) {
        String stringValue = field.getText().toString();
        if (!stringValue.isEmpty()) {
            Integer value = Integer.parseInt(stringValue);
            if (value > 50 || value <= 0) {
                Toast.makeText(MainActivity.this, "Digite valores entre 1 e 50", Toast.LENGTH_LONG).show();
                field.setText("");
            }
        }
    }

    private void setSortedNumberOn(TextView field, Integer value) {
        String stringValue = value.toString();
        field.setText(stringValue);
    }

    /*
     * Função retorna uma array com 5 números gerados randomicamente
     * sem duplicidade, representando o sorteio da megasena.
     */
    private ArrayList<Integer> lotterySort() {
        ArrayList<Integer> result = new ArrayList();

        for (int i = 0; i < NUMERO_DEZENAS; i++) {
            int sorted;
            boolean repeated = false;

            do {
                sorted = (int) (Math.random()*50)+1;
                repeated = numberExist(result, sorted);
            } while(repeated);

            result.add(sorted);
        }
        return result;
    }

    private boolean numberExist(ArrayList<Integer> numbers, int n) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == n) {
                return true;
            }
        }
        return false;
    }
}