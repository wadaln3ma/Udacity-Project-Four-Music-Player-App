package com.android.udmusicplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayingSongActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_song);

        imageView = findViewById(R.id.music_image);
        textView = findViewById(R.id.title_text_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            imageView.setImageResource(extras.getInt("imageResourceId"));
            textView.setText(extras.getString("songTitle"));
        }
    }
}
