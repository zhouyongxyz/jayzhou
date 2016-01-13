package com.example.snow.jayzhou;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.ListObjectsRequest;
import com.baidubce.services.bos.model.ListObjectsResponse;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouyong on 1/13/16.
 */
public class BosTask extends AsyncTask<Object,Object,Object> {
    private static final String TAG = "BosTask";
    public static final String OP_GETALBUM = "get_album";
    public static final String OP_GETSONGS = "get_songs";
    public static final String OP_GETURL = "get_url";
    private Context mContext;
    private String mPrefix;
    private ListView mList;
    private List<Map<String,Object>> mData;
    private String mUrl;
    private String mName;
    private String mOp;
    private static BosClient client = BosClientFactory.getClient();
    public BosTask(Context context,String prefix,ListView list,String operator) {
        mContext = context;
        mPrefix = prefix;
        mList = list;
        mOp = operator;
    }
    public BosTask(Context context,String prefix,String name,String operator) {
        mContext = context;
        mPrefix = prefix;
        mName = name;
        mOp = operator;
    }
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPostExecute(Object data) {
        if(mOp.equals(OP_GETSONGS)) {
            if (mList != null) {
                mData = (List<Map<String, Object>>) data;
                mList.setAdapter(new MusicAdapter(mContext, (List<Map<String, Object>>) data));
            }
        }else if(mOp.equals(OP_GETURL)) {
            mUrl = data.toString();
            ((MusicPlayer)mContext).initMusicPlayer(mUrl);
        }else if(mOp.equals(OP_GETALBUM)) {

        }
    }

    @Override
    protected Object doInBackground(Object... params) {
        if(mOp.equals(OP_GETSONGS)) {
            try {
                ListObjectsRequest request = new ListObjectsRequest("snow-jayzhou");
                request.setPrefix(mPrefix + "/");
                ListObjectsResponse listing = client.listObjects(request);

                List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                // 遍历所有Object
                for (BosObjectSummary object : listing.getContents()) {
                    Log.d(TAG, "ObjectKey: " + object.getKey());
                    if (object.getKey().endsWith("mp3")) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("title", object.getKey().replace(mPrefix + "/", ""));
                        data.add(map);
                    }
                }
                return data;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(mOp.equals(OP_GETURL)) {
            URL url = client.generatePresignedUrl("snow-jayzhou", mPrefix+"/"+mName, 2000);
            return url.toString();
        }else if(mOp.equals(OP_GETALBUM)) {

        }
        return null;
    }

    public String getItemStr(int pos) {
        return mData.get(pos).get("title").toString();
    }

    public String getUrl() {
        return mUrl;
    }
}
