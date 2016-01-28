package com.example.snow.jayzhou.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.snow.jayzhou.R;
import com.example.snow.jayzhou.music.MusicList;

/**
 * Created by zhouyong on 1/14/16.
 */
public class AlbumDescActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    private final static String TAG = "AlbumDescActivity";

    private String mAlbumName;
    private String mAlbumDesc;
    private TextView tvAlbumDesc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_album_desc);
        Intent intent = getIntent();
        mAlbumName = intent.getStringExtra("album");
        mAlbumDesc = intent.getStringExtra("desc");
        Log.d(TAG, "album name = " + mAlbumName);
        setTitle(mAlbumName);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setLogo(R.drawable.jay);
        AppBarLayout appbar = (AppBarLayout)findViewById(R.id.app_bar);
        appbar.setBackgroundResource(R.drawable.album_jay);
        appbar.addOnOffsetChangedListener(this);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //setTitle("摩羯座");

        tvAlbumDesc = (TextView)findViewById(R.id.album_desc);
        tvAlbumDesc.setText(mAlbumDesc);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent intent = new Intent(AlbumDescActivity.this,MusicList.class);
                intent.putExtra("album",mAlbumName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_jay_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //Log.d(TAG, "verticalOffset = " + verticalOffset);
    }
}
