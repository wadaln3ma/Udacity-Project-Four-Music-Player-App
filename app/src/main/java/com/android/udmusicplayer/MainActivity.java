package com.android.udmusicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song("Just the way you Are", "Bruno Mars", R.drawable.bruno_mars));
        songs.add(new Song("Hello", "Adele", R.drawable.adele));
        songs.add(new Song("Let Her Go", "Passenger", R.drawable.passenger));
        songs.add(new Song("Let Me Love You", "DJ Snake"));
        songs.add(new Song("Russian Roulette", "Rihanna", R.drawable.rihanna));
        songs.add(new Song("When I'm Gone", "Eminem", R.drawable.eminem));
        songs.add(new Song("Closer", "The Chainsmokers"));
        songs.add(new Song("Love the way you lie", "Eminem ft. Rihanna"));
        songs.add(new Song("Shape of You", "Ed Sheeran"));
        songs.add(new Song("Gold Digger", "Kayne west", R.drawable.kayne_west));
        songs.add(new Song("Rolling in the Deep", "Adele", R.drawable.adele));

        mRecyclerView = findViewById(R.id.songs_recycler_view);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        SongsRecyclerAdapter adapter = new SongsRecyclerAdapter(songs);

        mRecyclerView.setAdapter(adapter);

        adapter.setListener(this);

    }

    @Override
    public void onItemClick(Song song) {
        Intent intent = new Intent(MainActivity.this, PlayingSongActivity.class);
        intent.putExtra("songTitle", song.getmSongTitle());
        if (song.hasImage()){
            intent.putExtra("imageResourceId", song.getmImageResourceId());
        }else {
            intent.putExtra("imageResourceId", R.drawable.music_ic);
        }
        startActivity(intent);
    }
}
