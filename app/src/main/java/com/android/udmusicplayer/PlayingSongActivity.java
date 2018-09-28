package com.android.udmusicplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PlayingSongActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    ImageView imageView;
    TextView textView;
    Button playPauseButton;
    SeekBar volumeSeekBar;
    SeekBar scrubSeekBar;
    int track;

    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            playPauseButton.setBackground(ContextCompat.getDrawable(PlayingSongActivity.this, R.drawable.ic_play_circle));
        }
    };

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mediaPlayer.pause();
                releaseMediaPlayer();
            }
        }
    };

    private void releaseMediaPlayer(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;

            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    public void play(){
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, track);
        }
        mediaPlayer.start();
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            playPauseButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pause_circle) );
        } else {
            playPauseButton.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_pause_circle));
        }
        mediaPlayer.setOnCompletionListener(mCompletionListener);
    }

    public void pause(){
        mediaPlayer.pause();
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            playPauseButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_play_circle) );
        } else {
            playPauseButton.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_play_circle));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_song);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        final int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        imageView = findViewById(R.id.music_image);
        textView = findViewById(R.id.title_text_view);
        scrubSeekBar = findViewById(R.id.scrubSeekBar);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        playPauseButton = findViewById(R.id.play_pause_button);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            imageView.setImageResource(extras.getInt("imageResourceId"));
            textView.setText(extras.getString("songTitle"));
            track = extras.getInt("track");
        }

        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, track);
        }
        play();

        volumeSeekBar.setMax(maxVolume);
        volumeSeekBar.setProgress(currentVolume);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    pause();
                }else {
                    int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                        play();
                    }
                }
            }
        });

        scrubSeekBar.setMax(mediaPlayer.getDuration());

        scrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    scrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }
        }, 0, 1000);

    }
}
