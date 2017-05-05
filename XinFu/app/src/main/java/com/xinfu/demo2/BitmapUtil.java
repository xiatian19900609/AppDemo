package com.xinfu.demo2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class BitmapUtil {

    /**
     * 保存图片到相册
     *
     * @param context
     * @param url     图片url
     */
    public static void saveImageToGallery(Context context, String url) {
        Bitmap bitmap = BitmapUtil.convertUrlToBitmap(url);
        File appDir = new File(Environment.getExternalStorageDirectory(), "xinfu");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = url.replace("/", "_").replace(":", "&");

        File file = new File(appDir, fileName);
        if (file.exists()) {
            showToast(context, "图片已经存在");
        } else {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                // 其次把文件插入到系统图库
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
                // 最后通知图库更新
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath()));
                context.sendBroadcast(intent);
                showToast(context, "图片保存成功");
            } catch (Exception e) {
                e.printStackTrace();
                showToast(context, "图片保存失败");
            }
        }
    }

    /**
     * 图片URL生成Bitmap
     *
     * @param urlpath
     * @return Bitmap
     * 根据图片url获取图片对象
     */
    private static Bitmap convertUrlToBitmap(String urlpath) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private static void showToast(Context context, String des) {
        Looper.prepare();
        Toast.makeText(context, des, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
}
