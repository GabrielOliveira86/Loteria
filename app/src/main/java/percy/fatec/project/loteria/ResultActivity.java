package percy.fatec.project.loteria;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    int MAX_PLAYS = 5;
    ArrayList<Integer> sortedNumbers;
    ArrayList<Integer> playedNumbers;

    TextView numerosSorteadosTextView;
    TextView resultadoTextView1;
    TextView resultadoTextView2;
    TextView resultadoTextView3;
    TextView resultadoTextView4;
    TextView resultadoTextView5;

    ConstraintLayout matchesConstraintLayout;
    TextView hitTextView;
    TextView hitResultTextView;

    Button resetButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        numerosSorteadosTextView = (TextView)findViewById(R.id.numerosSorteadosTextView);
        resetButton = (Button)findViewById(R.id.resetButton);

        resultadoTextView1 = (TextView)findViewById(R.id.resultadoTextView1);
        resultadoTextView2 = (TextView)findViewById(R.id.resultadoTextView2);
        resultadoTextView3 = (TextView)findViewById(R.id.resultadoTextView3);
        resultadoTextView4 = (TextView)findViewById(R.id.resultadoTextView4);
        resultadoTextView5 = (TextView)findViewById(R.id.resultadoTextView5);

        matchesConstraintLayout = (ConstraintLayout)findViewById(R.id.matchesConstraintLayout);
        hitTextView = (TextView)findViewById(R.id.matchesTextView);
        hitResultTextView = (TextView)findViewById(R.id.matchesResultTextView);

        Intent intent = getIntent();
        sortedNumbers = intent.getIntegerArrayListExtra("results");
        playedNumbers = intent.getIntegerArrayListExtra("plays");
        setSortedNumbers();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.this.finish();
            }
        });
    }

    /** Set sorted numbers to view **/
    private void setSortedNumbers() {
        setSortedNumberOn(resultadoTextView1, sortedNumbers.get(0).intValue());
        setSortedNumberOn(resultadoTextView2, sortedNumbers.get(1).intValue());
        setSortedNumberOn(resultadoTextView3, sortedNumbers.get(2).intValue());
        setSortedNumberOn(resultadoTextView4, sortedNumbers.get(3).intValue());
        setSortedNumberOn(resultadoTextView5, sortedNumbers.get(4).intValue());

        setHitQuantity();
    }

    private void setSortedNumberOn(TextView field, Integer value) {
        String stringValue = value.toString();
        field.setText(stringValue);
    }

    private void setHitQuantity() {
        int hitQuantity = matches();

        if (hitQuantity < 4) {
            hitResultTextView.setTextColor(Color.BLUE);
        } else {
            hitResultTextView.setTextColor(Color.GREEN);
        }

        String hitStringHelper = getString(R.string.matches_result);
        String hitString = hitStringHelper.replace("%d", String.valueOf(hitQuantity));
        hitResultTextView.setText(hitString);
    }

    /** Find how many numbers the user matched **/
    private int matches() {
        int match = 0;
        for (int i = 0; i < MAX_PLAYS; i++) {
            int playNumber = playedNumbers.get(i);

            if (numberExist(playNumber)) {
                match++;
            }
        }
        return match;
    }

    private boolean numberExist(int playNumber) {
        for (int i = 0; i < sortedNumbers.size(); i++) {
            int sortedNumberHelper = sortedNumbers.get(i).intValue();
            if (sortedNumberHelper == playNumber) {
                return true;
            }
        }
        return false;
    }
}
