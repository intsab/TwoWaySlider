package com.seekbar.twoway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.intandif.twowayseekbar.RangeSeekBar;
import com.masdxwsgein.arashede.tesr.R;

public class MainActivity extends AppCompatActivity {
    RangeSeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar= (RangeSeekBar)findViewById(R.id.rangeSliderView);
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
