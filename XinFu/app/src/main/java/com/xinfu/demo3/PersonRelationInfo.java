package com.xinfu.demo3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/05
 *     desc   :
 * </pre>
 */
public class PersonRelationInfo {
    public String Name;
    public String Face;
    public String Pid;
    public String Id;
    public Bitmap bitmap;
    public ArrayList<PersonRelationInfo> son;


    /**
     * 图片URL生成Bitmap
     */
    public Bitmap convertUrlToBitmap() {
        try {
            URL url = new URL(Face);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
