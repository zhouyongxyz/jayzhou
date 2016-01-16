package com.example.snow.jayzhou.bosutils;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;

/**
 * Created by zhouyong on 1/13/16.
 */
public class BosClientFactory {
    private static BosClient client = null;
    public final static String BOS_BUCKET = "snow-jayzhou";

    public static BosClient getClient() {
        if(client != null) {
            return client;
        }
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials("51b9d3f413974d40a4e476ff524a771d", "0c32e200367848d2b07f7e7a091cd40f"));   //您的AK/SK
        config.setEndpoint("http://gz.bcebos.com");    //传入Bucket所在区域域名
        config.setConnectionTimeoutInMillis(1000 * 60 * 30); // 设置全局网络连接超时时间，默认30s
        config.setSocketTimeoutInMillis(1000 * 60 * 30); // 设置全局socket超时时间，默认30s
        config.setMaxConnections(15); // 替换设置最大连接数接口，设置全局最大并发任务数，默认为6
        BosClient client = new BosClient(config);

        return client;
    }
}
