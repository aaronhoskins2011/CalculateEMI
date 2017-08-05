package com.example.aaron.calculateemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "HELLO";
    TextView tvDisplayEMI;

    TextView tvDisplayIRate;
    SeekBar sbSetIRate;

    TextView tvDisplayPrinciple;
    SeekBar sbSetPrinciple;

    TextView tvDisplayDuration;
    SeekBar sbSetDuration;
    double interestRate;
    double duration;
    double principle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplayIRate = (TextView)findViewById(R.id.tvDisplayInterestRate);
        tvDisplayDuration = (TextView)findViewById(R.id.tvDisplayDuration);
        tvDisplayPrinciple = (TextView)findViewById(R.id.tvDisplayPrinciple);
        tvDisplayEMI = (TextView)findViewById(R.id.tvDisplayEMI);

        sbSetIRate = (SeekBar)findViewById(R.id.sbSetInterestRate);
        sbSetPrinciple = (SeekBar)findViewById(R.id.sbSetPrinciple);
        sbSetDuration = (SeekBar)findViewById(R.id.sbSetDuration);


        sbSetIRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            double progressChange = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChange = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                interestRate = progressChange/100;
                tvDisplayIRate.setText("InterestRate : " + interestRate);
                tvDisplayEMI.setText(calculateEMI());
            }
        });

        sbSetPrinciple.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            double progressChange = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChange = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                principle = progressChange;
                tvDisplayPrinciple.setText("Principle : " + principle);
                tvDisplayEMI.setText(calculateEMI());

            }
        });

        sbSetDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            double progressChange = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChange = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                duration = progressChange;
                tvDisplayDuration.setText("Duration : " + duration + " years");
                tvDisplayEMI.setText(calculateEMI());
            }
        });
    }

    public String calculateEMI(){
        double interestPerMonth;
        double numberOfMonths;
        double emi;

        interestPerMonth = (interestRate * 100)/(12 * 100);
        numberOfMonths = duration * 12;
        emi = (principle * interestPerMonth * Math.pow((1 + interestPerMonth), numberOfMonths))/Math.pow((1 + interestPerMonth),(numberOfMonths - 1));

        return "" + emi;
    }
}
