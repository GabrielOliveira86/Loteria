package percy.fatec.project.loteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    int NUMERO_DEZENAS = 5;

    TextView facaJogoTextView;
    EditText apostaEditText1;
    EditText apostaEditText2;
    EditText apostaEditText3;
    EditText apostaEditText4;
    EditText apostaEditText5;
    Button sortearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setGameViewElements();
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
                if (validateAllNumbers()) {
                    ArrayList<Integer> sortedNumbers = lotterySort();
                    Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                    resultIntent.putIntegerArrayListExtra("results", sortedNumbers);

                    MainActivity.this.startActivity(resultIntent);
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        resetLayout();
    }

    private void resetLayout() {
        apostaEditText1.setText("");
        apostaEditText2.setText("");
        apostaEditText3.setText("");
        apostaEditText4.setText("");
        apostaEditText5.setText("");
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

    private Boolean validateAllNumbers() {
        return true;
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