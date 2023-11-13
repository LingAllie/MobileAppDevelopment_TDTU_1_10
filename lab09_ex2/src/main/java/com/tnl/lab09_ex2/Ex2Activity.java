package com.tnl.lab09_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Ex2Activity extends AppCompatActivity
        implements MediaPlayer.OnPreparedListener, SeekBar.OnSeekBarChangeListener {

    private List<File> songs;
    private SeekBar seekBar;
    private TextView tvStart, tvEnd;
    private Button btnPlay, btnPause, btnPlay1, btnPlay2;
    private MediaPlayer player;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        songs = Mp3Provider.getAll();

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        tvStart = findViewById(R.id.tvStart);
        tvEnd = findViewById(R.id.tvEnd);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnPlay1 = findViewById(R.id.btnPlay1);
        btnPlay2 = findViewById(R.id.btnPlay2);

        player = new MediaPlayer();
        player.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
        );
        player.setOnPreparedListener(this);

        btnPlay.setOnClickListener(this::start);
        btnPause.setOnClickListener(this::pause);
        btnPlay1.setOnClickListener(this::playFirst);
        btnPlay2.setOnClickListener(this::playSecond);
    }

    public void log(String message) {
        Log.e("TAG", message);
    }

    public void start (View view) {
        player.start();
        log("Resume");
    }

    public void pause(View view) {
        player.pause();
        log("Paused");
    }

    public void playFirst(View view) {
        if (songs != null && !songs.isEmpty()) {
            File file = songs.get(0);
            playSong(file);
        } else {
            log("No songs available.");
        }
    }

    public void playSecond(View view) {
        if (songs != null && songs.size() > 1) {
            File file = songs.get(1);
            playSong(file);
        } else {
            log("Not enough songs available to play the second one.");
        }
    }

    private void playSong(File file) {
        log("Start playing: " + file.getName());

        Uri uri = Uri.fromFile(file);

        try {
            if (player.isPlaying()) {
                player.stop();
                player.reset();
                log("Stop playing and start new song");
            }
            player.setDataSource(this, uri);
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        player.start();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int current = player.getCurrentPosition();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvStart.setText(display(current));
                        seekBar.setProgress(current);
                    }
                });
            }
        }, 100, 1000);

        seekBar.setOnSeekBarChangeListener(null);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(this);

        int duration = player.getDuration();

        seekBar.setMax(duration);
        tvEnd.setText(display(duration));
    }

    public String display(int milisec) {
        int min = (int) TimeUnit.MILLISECONDS.toMinutes(milisec);
        milisec -= min * 60 * 1000;
        int secs = (int) TimeUnit.MILLISECONDS.toSeconds(milisec);

        return String.format("%02d:%02d", min, secs);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        player.seekTo(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}