package percy.fatec.project.loteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    int MAX_PLAYS = 5;
    ArrayList<Integer> playedNumbers;

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
        playedNumbers = new ArrayList<Integer>();
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
                playedNumbers.clear();
                addAllItemsToArray();
                if (playedNumbers.size() < 5) {
                    Toast.makeText(MainActivity.this, R.string.fill_all_fields, Toast.LENGTH_LONG).show();
                } else {
                    presentResultActivity();
                }
            }
        });
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

    private void addAllItemsToArray() {
        validateEnteredNumber(apostaEditText1);
        validateEnteredNumber(apostaEditText2);
        validateEnteredNumber(apostaEditText3);
        validateEnteredNumber(apostaEditText4);
        validateEnteredNumber(apostaEditText5);
    }

    private void validateEnteredNumber(EditText field) {
        String stringValue = field.getText().toString();
        if (!stringValue.isEmpty()) {
            Integer value = Integer.parseInt(stringValue);
            if (value > 50 || value <= 0) {
                Toast.makeText(MainActivity.this, R.string.fill_right_value, Toast.LENGTH_LONG).show();
                field.setText("");
            } else {
                playedNumbers.add(Integer.parseInt(stringValue));
            }
        }
    }

    private Boolean validateAllNumbers(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbersHelper = numbers;
        Collections.sort(numbersHelper);
        for (int i = 1; i < numbersHelper.size(); i++) {
            if (numbersHelper.get(i) == numbersHelper.get(i-1)) {
                return false;
            }
        }
        return true;
    }

    private void presentResultActivity() {
        if (validateAllNumbers(playedNumbers)) {
            ArrayList<Integer> sortedNumbers = lotterySort();
            Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
            resultIntent.putIntegerArrayListExtra("results", sortedNumbers);
            resultIntent.putIntegerArrayListExtra("plays", playedNumbers);

            MainActivity.this.startActivity(resultIntent);
        } else {
            Toast.makeText(MainActivity.this, R.string.repeated_field, Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Integer> lotterySort() {
        ArrayList<Integer> result = new ArrayList();

        for (int i = 0; i < MAX_PLAYS; i++) {
            int sorted;
            boolean repeated = false;

            do {
                sorted = (int) (Math.random()*50)+1;
                repeated = numberExist(result, sorted);
            } while(repeated);

            result.add(sorted);
        }
        Collections.sort(result);
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