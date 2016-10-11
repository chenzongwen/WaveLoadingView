package com.example.chenzongwen.myapplication.wave;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.chenzongwen.myapplication.R;

/**
 * Author: Owen Chan
 * DATE: 2016-10-11.
 */
public class WaveActivity extends Activity {
    private WaveLoadingView mWaveLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoading);
        SeekBar mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mWaveLoadingView.setPercent(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
