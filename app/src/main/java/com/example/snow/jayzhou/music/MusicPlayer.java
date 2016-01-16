package com.example.snow.jayzhou.music;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.snow.jayzhou.R;
import com.example.snow.jayzhou.bosutils.BosTask;

import co.mobiwise.library.MusicPlayerView;

/**
 * Created by zhouyong on 1/7/16.
 */
public class MusicPlayer extends AppCompatActivity implements Button.OnClickListener ,MediaPlayer.OnPreparedListener{
    private final static String TAG = "MusicPlayer";
    private MediaPlayer mp;
    private MusicPlayerView mpv;
    private String mAlbumName;
    private String mMusicName;
    private BosTask bosTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_music_player);
        Intent intent = getIntent();
        Log.d(TAG, "music = " + intent.getStringExtra("music"));
        mAlbumName = intent.getStringExtra("album");
        mMusicName = intent.getStringExtra("music");
        setTitle(mMusicName);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        bosTask = new BosTask(this,mAlbumName+"/",mMusicName,BosTask.OP_GETURL);
        bosTask.execute();
        mp = new MediaPlayer();
        mp.setOnPreparedListener(this);
        mpv = (MusicPlayerView) findViewById(R.id.music_player_view);
        //mpv.setCoverURL("https://upload.wikimedia.org/wikipedia/en/b/b3/MichaelsNumberOnes.JPG");
        mpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mpv.isRotating()) {
                    mp.pause();
                    mpv.stop();
                } else {
                    mpv.start();
                    mp.start();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp != null) {
            mp.release();
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.play) {
            if(mp == null)
                return;
            mp.start();
        } else if(v.getId() == R.id.stop) {
            if(mp == null)
                return;
            mp.stop();
            //mp.release();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jay, menu);
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG,"mp prepare is done..");
        if(mp != null) {
            mp.start();
            mpv.setMax(mp.getDuration() / 1000);
            mpv.start();
        }
    }
    //invoke when BosTask get URL done
    public void initMusicPlayer(String url) {
        Log.d(TAG, "url = " + url);
        mp.reset();
        try {
            mp.setDataSource(url);
            mp.prepareAsync();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
