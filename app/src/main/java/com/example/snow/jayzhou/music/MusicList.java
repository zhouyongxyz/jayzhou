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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.snow.jayzhou.R;
import com.example.snow.jayzhou.bosutils.BosTask;


/**
 * Created by zhouyong on 1/7/16.
 */
public class MusicList extends AppCompatActivity implements ListView.OnItemClickListener,Button.OnClickListener {
    private final static String TAG = "MusicList";
    private ListView listMusic;
    private Button btnPlay;
    private Button btnNext;
    private Button btnStop;
    private MediaPlayer mp;
    private String mAlbumName;
    private BosTask bosTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_music_list);
        Intent intent = getIntent();
        mAlbumName = intent.getStringExtra("album");
        Log.d(TAG, "album name = " + mAlbumName);
        setTitle(mAlbumName);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        listMusic = (ListView)findViewById(R.id.list_music);
        btnPlay = (Button)findViewById(R.id.play);
        btnNext = (Button)findViewById(R.id.next);
        btnStop = (Button)findViewById(R.id.stop);
        btnPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        initMusicList();
        //List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
        //Map<String,Object> map = new HashMap<String, Object>();
        //map.put("title", "开不了口");
        //data.add(map);
        //listMusic.setAdapter(new MusicAdapter(this, data));
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
        Intent intent = new Intent(this,MusicPlayer.class);
        intent.putExtra("album",mAlbumName);
        intent.putExtra("music",bosTask.getItemStr(position));
        startActivity(intent);
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

    private void initMusicList() {
        bosTask = new BosTask(this,mAlbumName,listMusic,BosTask.OP_GETSONGS);
        bosTask.execute();
    }
}
