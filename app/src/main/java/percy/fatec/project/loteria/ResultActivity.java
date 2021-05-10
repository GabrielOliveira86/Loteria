package percy.fatec.project.loteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ArrayList<Integer> sortedNumbers;

    TextView numerosSorteadosTextView;
    TextView resultadoTextView1;
    TextView resultadoTextView2;
    TextView resultadoTextView3;
    TextView resultadoTextView4;
    TextView resultadoTextView5;

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

        Intent intent = getIntent();
        sortedNumbers = intent.getIntegerArrayListExtra("results");
        setSortedNumbers();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.this.finish();
            }
        });
        }

    private void setSortedNumbers() {
        setSortedNumberOn(resultadoTextView1, sortedNumbers.get(0).intValue());
        setSortedNumberOn(resultadoTextView2, sortedNumbers.get(1).intValue());
        setSortedNumberOn(resultadoTextView3, sortedNumbers.get(2).intValue());
        setSortedNumberOn(resultadoTextView4, sortedNumbers.get(3).intValue());
        setSortedNumberOn(resultadoTextView5, sortedNumbers.get(4).intValue());
    }

    private void setSortedNumberOn(TextView field, Integer value) {
        String stringValue = value.toString();
        field.setText(stringValue);
    }
}
