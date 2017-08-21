package com.xinfu.demo12;

import android.content.Context;
import android.widget.Toast;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/07/27
 *     desc   :
 * </pre>
 */
public class Demo12A {
    public void a(Context context) {
        b(context);
    }

    public void b(Context context) {
        Toast.makeText(context, "Demo12A", Toast.LENGTH_SHORT).show();
    }
}
