package com.wesleyreisz.mymusic;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

import com.wesleyreisz.mymusic.model.Song;
import com.wesleyreisz.mymusic.service.MockMusicService;

import java.text.SimpleDateFormat;


public class SongDetailActivity extends Activity {
    private static final String TAG = "MusicList";
    private static final String SONG_TITLE = "song_title";
    private static final SimpleDateFormat df =
            new SimpleDateFormat("MMM d, yyyy (EEE)");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        Intent intent = getIntent();
        String name = intent.getStringExtra(SONG_TITLE);
        Song song = new MockMusicService().findOne(name);
        Log.d(TAG, "Passed In: " + song.getArtistName());
        Log.d(TAG, "Passed In: " + song.getAlbumTitle());

        TextView songTitle = (TextView) findViewById(R.id.textView1);
        songTitle.setText(song.getSongTitle());

        TextView songArtist = (TextView) findViewById(R.id.textView3);
        songArtist.setText(song.getArtistName());

        TextView songAlbum= (TextView) findViewById(R.id.textView10);
        songAlbum.setText(song.getAlbumTitle());

        TextView songDate= (TextView) findViewById(R.id.textView5);
        songDate.setText(df.format(song.getSongPublishedDate()));



        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.song_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_song_detail, container, false);
            return rootView;
        }
    }
}
