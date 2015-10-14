package com.halcyon.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class CanvasView extends View {
    Context mContext;
    private Paint mPaint;
    int typeCount = 10;
    int mDrawType = typeCount - 1;

    public CanvasView(Context context) {
        super(context);
        mContext = context;
        mPaint = new Paint();
    }

    public int getTypeCount() {
        return typeCount;
    }


    public void setDrawType(int type) {
        mDrawType = type;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawWhat(canvas);
    }

    private void drawWhat(Canvas canvas) {
        switch (mDrawType) {
            case 0://画圆
                drawCirlce(canvas);
                break;
            case 1://画线
                drawLine(canvas);
                break;
            case 2://画多条线
                drawLines(canvas);
                break;
            case 3://画点
                drawPoint(canvas);
                break;
            case 4://画多个点
                drawPoints(canvas);
                break;
            case 5://画矩形
                drawRect(canvas);
                break;
            case 6://画圆角矩形
                drawRoundRect(canvas);
                break;
            case 7://画椭圆
                drawOval(canvas);
                break;
            case 8://画弧
                drawArc(canvas);
                break;
            case 9://画直线路径
                drawPath(canvas);
                break;
        }
    }

    private void drawPath(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);//填充样式改为描边
        mPaint.setStrokeWidth(5);//设置画笔宽度

        Path path = new Path();

        path.moveTo(10, 10); //设定起始点
        path.lineTo(10, 100);//第一条直线的终点，也是第二条直线的起点
        path.lineTo(300, 100);//画第二条直线
        path.lineTo(500, 100);//第三条直线
        path.close();//闭环

        canvas.drawPath(path, mPaint);
    }

    private void drawArc(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);//填充样式改为描边
        mPaint.setStrokeWidth(5);//设置画笔宽度
        mPaint.setAntiAlias(true);
        RectF rect1 = new RectF(100, 10, 300, 100);
        canvas.drawArc(rect1, 0, 180, true, mPaint);

        RectF rect2 = new RectF(400, 10, 600, 100);
        canvas.drawArc(rect2, 0, 90, true, mPaint);

    }

    private void drawOval(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);//填充样式改为描边
        mPaint.setStrokeWidth(5);//设置画笔宽度

        RectF rect = new RectF(100, 10, 300, 100);
        canvas.drawRect(rect, mPaint);//画矩形

        mPaint.setColor(Color.GREEN);//更改画笔颜色
        canvas.drawOval(rect, mPaint);//同一个矩形画椭圆
    }

    private void drawRoundRect(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置填充样式
        mPaint.setStrokeWidth(15);//设置画笔宽度

        RectF rect = new RectF(100, 10, 300, 100);
        canvas.drawRoundRect(rect, 20, 10, mPaint);
    }

    private void drawRect(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置填充样式
        mPaint.setStrokeWidth(15);//设置画笔宽度

        canvas.drawRect(10, 10, 100, 100, mPaint);//直接构造

        RectF rect = new RectF(120, 10, 210, 100);
        canvas.drawRect(rect, mPaint);//使用RectF构造

        Rect rect2 =  new Rect(230, 10, 320, 100);
        canvas.drawRect(rect2, mPaint);//使用Rect构造
    }

    private void drawPoints(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置填充样式
        mPaint.setStrokeWidth(15);//设置画笔宽度

        float []pts={10,10,100,100,200,200,400,400};
        canvas.drawPoints(pts, 2, 4, mPaint);
    }

    private void drawPoint(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置填充样式
        mPaint.setStrokeWidth(15);//设置画笔宽度

        canvas.drawPoint(100, 100, mPaint);
    }

    private void drawLine(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置填充样式
        mPaint.setStrokeWidth(5);//设置画笔宽度

        canvas.drawLine(100, 100, 200, 200, mPaint);
    }

    private void drawLines(Canvas canvas) {
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置填充样式
        mPaint.setStrokeWidth(5);//设置画笔宽度
        //每两个点形成一条线
        float[] pts = {10, 10, 100, 100, 200, 200, 400, 400};
        canvas.drawLines(pts, mPaint);
    }

    private void drawCirlce(Canvas canvas) {
        //设置画笔基本属性
        mPaint.setAntiAlias(true);//抗锯齿功能
        mPaint.setColor(Color.RED);  //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        mPaint.setStrokeWidth(5);//设置画笔宽度

        mPaint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影 对字体有效

        //设置画布背景颜色
//        canvas.drawRGB(255, 255, 255);
        canvas.drawColor(Color.WHITE);
        //画圆
        canvas.drawCircle(190, 200, 150, mPaint);
    }
}
