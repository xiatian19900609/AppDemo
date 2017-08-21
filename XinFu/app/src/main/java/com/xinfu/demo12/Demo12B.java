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
public class Demo12B extends Demo12A {

    public void a(Context context) {
        super.a(context);
    }

    public void b(Context context) {
        Toast.makeText(context, "Demo12B", Toast.LENGTH_SHORT).show();
    }
}
