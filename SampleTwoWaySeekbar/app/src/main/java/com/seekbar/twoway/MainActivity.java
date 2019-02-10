package com.seekbar.twoway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.intandif.twowayseekbar.RangeSeekBar;
import com.masdxwsgein.arashede.tesr.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RangeSeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar= (RangeSeekBar)findViewById(R.id.rangeSliderView);
        ArrayList<Integer> mValues = new ArrayList();
        mValues.add(1);
        mValues.add(20000);
        mValues.add(40000);
        mValues.add(60000);
        mValues.add(80000);
        mValues.add(100000);
        mValues.add(120000);
        mValues.add(140000);
        mValues.add(160000);
        mValues.add(180000);
        mValues.add(200000);
        mValues.add(220000);
        mValues.add(240000);
        mValues.add(260000);
        mValues.add(280000);
        mValues.add(300000);
        mValues.add(320000);
        mValues.add(340000);
        mValues.add(360000);
        mValues.add(380000);
        mValues.add(400000);
        mValues.add(420000);
        mValues.add(440000);
        mValues.add(460000);
        mValues.add(480000);
        mValues.add(500000);

        seekBar.setDefaultMinMax(10,5000);

        seekBar.setRange(mValues);
        seekBar.setOnValueChangedListener(new RangeSeekBar.onValueChangedListener() {
            @Override
            public void onValueChanged(int minimumValue, int maximumValue) {
                Log.d("","");
            }

            @Override
            public void onFinishScrolling(int minimumValue, int maximumValue) {
                Log.d("","");
            }
        });
    }
}
