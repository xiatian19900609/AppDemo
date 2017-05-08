package com.xinfu.demo3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xinfu.R;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/08
 *     desc   :
 * </pre>
 */
public class PersonRelativeLayout extends RelativeLayout {
    //一级角度
    private int mPrimaryAngle = 120;
    //二级角度
    private int mSecondaryAngle = 50;
    //线长
    private float lineLengths = dp_px(80);
    //中心X坐标
    private int centerX;
    //中心Y坐标
    private int centerY;
    // 主线画笔
    private Paint primaryLinePaint;
    // 副线画笔
    private Paint secondaryLinePaint;

    private PersonRelationResponse mData;

    public PersonRelativeLayout(Context context) {
        super(context);
        init();
    }

    public PersonRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PersonRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        primaryLinePaint = new Paint();
        primaryLinePaint.setAntiAlias(true);
        primaryLinePaint.setStrokeWidth(1f);
        primaryLinePaint.setColor(getResources().getColor(R.color.colorAccent));
        primaryLinePaint.setStyle(Paint.Style.STROKE);

        secondaryLinePaint = new Paint();
        secondaryLinePaint.setAntiAlias(true);
        secondaryLinePaint.setStrokeWidth(1f);
        secondaryLinePaint.setColor(getResources().getColor(R.color.colorPrimary));
        secondaryLinePaint.setStyle(Paint.Style.STROKE);
    }

    public void setData(PersonRelationResponse data) {
        this.mData = data;
        this.postInvalidate();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData != null && mData.my.bitmap != null) {
            Bitmap bitmap = mData.my.bitmap;
            createImageView(mData.my, centerY - bitmap.getHeight() / 2, centerX - bitmap.getHeight() / 2);
            drawPrimaryLines(canvas);
        }
    }


    /**
     * 绘制一级连接线
     *
     * @param canvas 画布
     */
    private void drawPrimaryLines(Canvas canvas) {
        for (int i = 0; i < mData.data.size(); i++) {
            PersonRelationInfo info = mData.data.get(i);
            int height = info.bitmap.getHeight();

            Path path = new Path();
            path.reset();
            path.moveTo(getStartPoint(i, height).x, getStartPoint(i, height).y);
            path.lineTo(getEndPoint(i).x, getEndPoint(i).y);
            canvas.drawPath(path, primaryLinePaint);

            createImageView(mData.my, getEndPoint(i).y - info.bitmap.getHeight() / 2, getEndPoint(i).x - info.bitmap.getHeight() / 2);

            drawSecondaryLines(i, canvas, getEndPoint(i).x, getEndPoint(i).y);
        }

    }

    /**
     * 绘制二级连接线
     *
     * @param canvas 画布
     */
    private void drawSecondaryLines(int num, Canvas canvas, int x, int y) {
        for (int i = 0; i < mData.data.get(num).son.size(); i++) {
            PersonRelationInfo info = mData.data.get(num).son.get(i);
            int height = info.bitmap.getHeight();

            Path path = new Path();
            path.reset();
            path.moveTo(getSecondaryStartPoint(num, i, x, y, height).x, getSecondaryStartPoint(num, i, x, y, height).y);
            path.lineTo(getSecondaryEndPoint(num, i, x, y).x, getSecondaryEndPoint(num, i, x, y).y);
            canvas.drawPath(path, secondaryLinePaint);

            createImageView(info, getSecondaryEndPoint(num, i, x, y).y - info.bitmap.getHeight() / 2, getSecondaryEndPoint(num, i, x, y).x - info.bitmap.getHeight() / 2);
        }
    }

    /**
     * 绘制图片
     *
     * @param info
     * @param top
     * @param left
     */
    private void createImageView(final PersonRelationInfo info, int top, int left) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(info.bitmap);
        LayoutParams params = new LayoutParams(info.bitmap.getHeight(), info.bitmap.getWidth());
        params.topMargin = top;
        params.leftMargin = left;
        addView(imageView, params);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Toast.makeText(getContext(), "点击" + info.Id, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 获取各个起始点的坐标
     */
    private Point getStartPoint(int position, int height) {
        float radian = (float) ((2 * Math.PI / 360) * mPrimaryAngle * position);
        int x;
        int y;
        x = (int) (centerX + Math.sin(radian) * height / 2);
        y = (int) (centerY - Math.cos(radian) * height / 2);
        return new Point(x, y);
    }


    /**
     * 获取各个结束点的坐标
     */
    private Point getEndPoint(int position) {
        float radian = (float) ((2 * Math.PI / 360) * mPrimaryAngle * position);
        int x = (int) (centerX + Math.sin(radian) * lineLengths);
        int y = (int) (centerY - Math.cos(radian) * lineLengths);
        return new Point(x, y);
    }

    /**
     * 获取各个起始点的坐标
     */
    private Point getSecondaryStartPoint(int num, int position, int startX, int startY, int height) {
        float radian = 0f;
        radian = (float) ((2 * Math.PI / 360) * mPrimaryAngle * num);
        if (num == 0) {
            if (position == 1) {
                radian = (float) ((2 * Math.PI / 360) * mSecondaryAngle);
            } else if (position == 2) {
                radian = (float) ((2 * Math.PI / 360) * (360 - mSecondaryAngle));
            }
        } else if (num == 1) {
            if (position == 1) {
                radian = (float) ((2 * Math.PI / 360) * (360 / 3 - mSecondaryAngle));
            } else if (position == 2) {
                radian = (float) ((2 * Math.PI / 360) * (360 / 3 + mSecondaryAngle));
            }
        } else if (num == 2) {
            if (position == 1) {
                radian = (float) ((2 * Math.PI / 360) * (360 / 1.5 - mSecondaryAngle));
            } else if (position == 2) {
                radian = (float) ((2 * Math.PI / 360) * (360 / 1.5 + mSecondaryAngle));
            }
        }

        int x;
        int y;
        x = (int) (startX + Math.sin(radian) * height / 2);
        y = (int) (startY - Math.cos(radian) * height / 2);
        return new Point(x, y);
    }


    /**
     * 获取各个结束点的坐标
     */
    private Point getSecondaryEndPoint(int num, int position, int endX, int endY) {
        float radian = 0f;
        radian = (float) ((2 * Math.PI / 360) * mPrimaryAngle * num);
        if (num == 0) {
            if (position == 1) {
                radian = (float) ((2 * Math.PI / 360) * mSecondaryAngle);
            } else if (position == 2) {
                radian = (float) ((2 * Math.PI / 360) * (360 - mSecondaryAngle));
            }
        } else if (num == 1) {
            if (position == 1) {
                radian = (float) ((2 * Math.PI / 360) * (360 / 3 - mSecondaryAngle));
            } else if (position == 2) {
                radian = (float) ((2 * Math.PI / 360) * (360 / 3 + mSecondaryAngle));
            }
        } else if (num == 2) {
            if (position == 1) {
                radian = (float) ((2 * Math.PI / 360) * (360 / 1.5 - mSecondaryAngle));
            } else if (position == 2) {
                radian = (float) ((2 * Math.PI / 360) * (360 / 1.5 + mSecondaryAngle));
            }
        }

        int x = (int) (endX + Math.sin(radian) * lineLengths);
        int y = (int) (endY - Math.cos(radian) * lineLengths);
        return new Point(x, y);
    }

    /**
     * dp转px
     *
     * @param values
     * @return
     */
    public int dp_px(int values) {

        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }
}
