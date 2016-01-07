package com.example.snow.jayzhou;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouyong on 1/7/16.
 */
public class MusicPlayer extends Activity implements ListView.OnItemClickListener,Button.OnClickListener {
    private final static String TAG = "MusicPlayer";
    private ListView listMusic;
    private Button btnPlay;
    private Button btnNext;
    private Button btnStop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_music_player);
        //getActionBar().show();
        Intent intent = getIntent();
        Log.d(TAG,"album = "+intent.getIntExtra("album", 3));
        listMusic = (ListView)findViewById(R.id.list_music);
        btnPlay = (Button)findViewById(R.id.play);
        btnNext = (Button)findViewById(R.id.next);
        btnStop = (Button)findViewById(R.id.stop);
        btnPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("title", "开不了口");
        data.add(map);
        listMusic.setAdapter(new MusicAdapter(this, data));
        listMusic.setOnItemClickListener(this);
        mp = new MediaPlayer();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.play) {
            if(mp == null)
                return;
            mp.reset();
            try {
                mp.setDataSource("/sdcard/test.mp3");
                mp.prepare();
            }catch (Exception e) {
                e.printStackTrace();
            }
            mp.start();
        } else if(v.getId() == R.id.stop) {
            if(mp == null)
                return;
            mp.stop();
            //mp.release();
        }

    }
}
