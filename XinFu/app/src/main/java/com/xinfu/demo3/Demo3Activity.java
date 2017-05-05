package com.xinfu.demo3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/05
 *     desc   :
 * </pre>
 */
public class Demo3Activity extends AppCompatActivity {
    @InjectView(R.id.pr)
    PersonRelationView pr;

    private String json = "{\"result\":1,\"data\":[{\"Name\":\"ttf \",\"Pid\":\"2\",\"Id\":\"3\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\",\"son\":[{\"Name\":\"\\u54e6\\u54e6\\u54e6\",\"Pid\":\"3\",\"Id\":\"4\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"},{\"Name\":\"\\u54c8\\u54c8\",\"Pid\":\"3\",\"Id\":\"5\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"},{\"Name\":\"\\u5c0f\\u5c0f3\",\"Pid\":\"3\",\"Id\":\"8\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"}]},{\"Name\":\"\\u5c0f\\u5c0f1\",\"Pid\":\"2\",\"Id\":\"6\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\",\"son\":[{\"Name\":\"\\u5c0f\\u5c0f4\",\"Pid\":\"6\",\"Id\":\"9\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"},{\"Name\":\"\\u5c0f\\u5c0f5\",\"Pid\":\"6\",\"Id\":\"10\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"},{\"Name\":\"\\u5c0f\\u5c0f6\",\"Pid\":\"6\",\"Id\":\"11\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"}]},{\"Name\":\"\\u5c0f\\u5c0f2\",\"Pid\":\"2\",\"Id\":\"7\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\",\"son\":[{\"Name\":\"\\u5c0f\\u5c0f7\",\"Pid\":\"7\",\"Id\":\"12\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"},{\"Name\":\"\\u5c0f\\u5c0f8\",\"Pid\":\"7\",\"Id\":\"13\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"},{\"Name\":\"\\u5c0f\\u5c0f9\",\"Pid\":\"7\",\"Id\":\"14\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"}]}],\"my\":{\"Id\":\"2\",\"Name\":\"\\u5361\\u5361\\u897f\",\"Pwd\":\"e10adc3949ba59abbe56e057f20f883e\",\"Addtime\":\"1493870096\",\"Pid\":\"1\",\"Isshow\":\"1\",\"Phone\":\"1100\",\"Userno\":\"xf2017050411545643131\",\"Sex\":\"1\",\"Tel\":\"0\",\"Updatetime\":null,\"Ty\":\"1\",\"Qrcode\":\"Uploads\\/xf2017050411545643131.png\",\"Face\":\"http://pic.8win.com/data/image/all/2016/11/21/bb_team_1479707609731.png\"},\"msg\":\"\\u83b7\\u53d6\\u6210\\u529f\"}";

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                pr.setData(mData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private PersonRelationResponse mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        ButterKnife.inject(this);
        mData = JsonUtil.getInstance().convertString2Bean(json, PersonRelationResponse.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mData.createBitmap();
                handler.sendEmptyMessage(0);
            }
        }).start();

    }
}
