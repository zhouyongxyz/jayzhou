package com.example.snow.jayzhou;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouyong on 1/7/16.
 */
public class AlbumAdapter extends BaseAdapter {
    private final static String TAG = "CommentsAdapter";
    private Context mContext;
    private List<Map<String,Object>> mDataList;
    private LayoutInflater mLayoutInflater;
    public AlbumAdapter(Context context,List<Map<String,Object>> data) {
        mContext = context;
        mDataList = data;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MusicItemView item = null;
        if(convertView == null) {
            item = new MusicItemView();
            convertView = mLayoutInflater.inflate(R.layout.list_music_item,null);
            item.title = (TextView)convertView.findViewById(R.id.title);
            convertView.setTag(item);
        } else {
            item = (MusicItemView)convertView.getTag();
        }
        Log.d(TAG,"getView position = "+position);
        item.title.setText((String)mDataList.get(position).get("title"));
        return convertView;
    }
}
