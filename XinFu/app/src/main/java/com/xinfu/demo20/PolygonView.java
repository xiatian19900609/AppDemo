package com.xinfu.demo20;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.xinfu.R;

/**
 * Created by xiatian on 2016/10/20.
 */

public class PolygonView extends View {


    //雷达图半径
    private float radius;
    //多边形之间的间距
    private int distance;
    //中心X坐标
    private int centerX;
    //中心Y坐标
    private int centerY;
    //各维度标题
    private String[] titles;
    //各维度分值
    private float[] data;
    //数据最大值
    private float maxValue = 100;
    //雷达图与标题的间距
    private int radarMargin = dp_px(15);
    //雷达区画笔
    private Paint twoPaint;
    //雷达区画笔
    private Paint mainPaint;
    //数据区画笔
    private Paint valuePaint;
    //分数画笔
    private Paint scorePaint;
    //标题画笔
    private Paint titlePaint;
    //标题文字大小
    private int titleSize = dp_px(13);

    public PolygonView(Context context) {
        this(context, null);
    }

    public PolygonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setData(String[] titles, float[] data) {
        this.titles = titles;
        this.data = data;
        this.postInvalidate();
    }

    public void setData(float[] data) {
        this.data = data;
        this.postInvalidate();
    }

    private void init() {
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setStrokeWidth(1f);
        mainPaint.setColor(getResources().getColor(R.color.color_666666));
        mainPaint.setStyle(Paint.Style.STROKE);

        //初始化第二层多边形画笔
        twoPaint = new Paint();
        PathEffect effects = new DashPathEffect(new float[]{4, 4, 4, 4}, 1);
        twoPaint.setPathEffect(effects);
        twoPaint.setAntiAlias(true);
        twoPaint.setStrokeWidth(1f);
        twoPaint.setColor(getResources().getColor(R.color.color_666666));
        twoPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.RED);
        valuePaint.setAlpha(120);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        scorePaint = new Paint();
        scorePaint.setAntiAlias(true);
        scorePaint.setTextSize(titleSize);
        scorePaint.setColor(Color.RED);
        scorePaint.setStyle(Paint.Style.FILL);

        titlePaint = new Paint();
        titlePaint.setAntiAlias(true);
        titlePaint.setTextSize(titleSize);
        titlePaint.setColor(Color.RED);
        titlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        //雷达图半径
        radius = Math.min(h, w) / 2 * 0.5f;
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawPolygon(canvas);
        drawOthersPolygon(canvas);
        drawLines(canvas);
        drawRegion(canvas);
        drawTitle(canvas);
    }

    /**
     * 绘制连接线
     *
     * @param canvas 画布
     */
    private void drawLines(Canvas canvas) {
        if (titles != null) {
            Path path = new Path();
            for (int i = 0; i < titles.length; i++) {
                path.reset();
                path.moveTo(centerX, centerY);
                path.lineTo(getPoint(i).x, getPoint(i).y);
                canvas.drawPath(path, mainPaint);
            }
        }
    }

    /**
     * 绘制多边形
     *
     * @param canvas 画布
     */
    private void drawPolygon(Canvas canvas) {
        if (titles != null) {
            Path path = new Path();
            for (int i = 0; i < titles.length; i++) {
                if (i == 0) {
                    path.moveTo(getPoint(i).x, getPoint(i).y);
                } else {
                    path.lineTo(getPoint(i).x, getPoint(i).y);
                }
            }

            //闭合路径
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制第2层多边形
     *
     * @param canvas 画布
     */
    private void drawOthersPolygon(Canvas canvas) {
        if (titles != null) {
            Path path = new Path();
            for (int j = 1; j < 6; j++) {
                distance = (int) (radius - radius / 6 * j);
                for (int i = 0; i < titles.length; i++) {
                    if (i == 0) {
                        path.moveTo(getPoint(i, -distance, 1).x, getPoint(i, -distance, 1).y);
                    } else {
                        path.lineTo(getPoint(i, -distance, 1).x, getPoint(i, -distance, 1).y);
                    }
                }
                path.close();
            }
            //闭合路径
            canvas.drawPath(path, twoPaint);
        }
    }


    /**
     * 绘制覆盖区域
     *
     * @param canvas 画布
     */
    private void drawRegion(Canvas canvas) {
        if (data != null) {
            Path path = new Path();

            for (int i = 0; i < data.length; i++) {
                //计算百分比
                float percent = data[i] / maxValue;
                int x = getPoint(i, 0, percent).x;
                int y = getPoint(i, 0, percent).y;
                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }

                Bitmap bitmap = null;
                bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.point_red)).getBitmap();
                int height = bitmap.getHeight();
                canvas.drawBitmap(bitmap, x - height / 2, y - height / 2, null);
            }

            //绘制填充区域的边界
            path.close();
            valuePaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, valuePaint);

            //绘制填充区域
            valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawPath(path, valuePaint);
        }

    }


    /**
     * 绘制标题
     *
     * @param canvas 画布
     */
    private void drawTitle(Canvas canvas) {
        if (titles != null && data != null) {
            for (int i = 0; i < titles.length; i++) {
                int x = getPoint(i, radarMargin, 1).x;
                int y = getPoint(i, radarMargin, 1).y;
                float titleWidth = titlePaint.measureText(titles[i]);
                float scoreWidth = scorePaint.measureText(data[i] + "");
//            底下两个角的坐标需要向下移动半个图片的位置（1、2）
                if (i == 2) {
                    x -= titleWidth;
                } else if (i == 3) {
                    x -= titleWidth;
                } else if (i == 4) {
                    x -= titleWidth / 2;
                    y -= 20;
                }

                Rect rect = new Rect();
                scorePaint.getTextBounds(data[i] + "", 0, 1, rect);

                canvas.drawText(titles[i], x, y, titlePaint);
                canvas.drawText(data[i] + "", x + (titleWidth / 2) - (scoreWidth / 2), y + rect.height() + 10, scorePaint);
            }
        }

    }


    /**
     * 获取雷达图上各个点的坐标
     *
     * @param position 坐标位置（右上角为0，顺时针递增）
     * @return 坐标
     */
    private Point getPoint(int position) {
        return getPoint(position, 0, 1);
    }

    /**
     * 获取雷达图上各个点的坐标（包括维度标题与图标的坐标）
     *
     * @param position    坐标位置
     * @param radarMargin 雷达图与维度标题的间距
     * @param percent     覆盖区的的百分比
     * @return 坐标
     */
    private Point getPoint(int position, int radarMargin, float percent) {
        int x = 0;
        int y = 0;
        if (titles != null) {
            float radian = (float) (Math.PI * 2 / titles.length);
            if (position == 0) {
                x = (int) (centerX + (radius + radarMargin) * Math.sin(radian) * percent);
                y = (int) (centerY - (radius + radarMargin) * Math.cos(radian) * percent);

            } else if (position == 1) {
                x = (int) (centerX + (radius + radarMargin) * Math.sin(radian / 2) * percent);
                y = (int) (centerY + (radius + radarMargin) * Math.cos(radian / 2) * percent);

            } else if (position == 2) {
                x = (int) (centerX - (radius + radarMargin) * Math.sin(radian / 2) * percent);
                y = (int) (centerY + (radius + radarMargin) * Math.cos(radian / 2) * percent);

            } else if (position == 3) {
                x = (int) (centerX - (radius + radarMargin) * Math.sin(radian) * percent);
                y = (int) (centerY - (radius + radarMargin) * Math.cos(radian) * percent);

            } else if (position == 4) {
                x = centerX;
                y = (int) (centerY - (radius + radarMargin) * percent);
            }

            return new Point(x, y);
        }
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
