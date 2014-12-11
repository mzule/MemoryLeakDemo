package com.mzule.demo;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lennnna on 12/11/14.
 */
public class HexagonView extends View {
    private static final int DEFAULT_WIDTH = 540;
    private static final int DEFAULT_BG_COLOR = Color.parseColor("#22ffffff");
    private static final int DEFAULT_LINE_COLOR = Color.parseColor("#44ffffff");
    private static final int DEFAULT_FG_COLOR = Color.parseColor("#8823beff");
    private int width;
    private int height;
    private int bgColor = DEFAULT_BG_COLOR;
    private int lineColor = DEFAULT_LINE_COLOR;
    private int fgColor = DEFAULT_FG_COLOR;
    private float values[] = new float[6];
    private float fakes[] = new float[]{0.22f, 0.24f, 0.26f, 0.28f, 0.30f, 0.32f, 0.34f, 0.36f, 0.38f, 0.40f, 0.42f, 0.44f, 0.46f, 0.48f, 0.50f, 0.52f, 0.54f, 0.56f, 0.58f, 0.60f, 0.62f, 0.64f, 0.66f, 0.68f, 0.70f, 0.72f, 0.74f, 0.76f, 0.78f, 0.80f, 0.82f, 0.84f, 0.86f, 0.88f, 0.86f, 0.84f, 0.82f, 0.80f, 0.78f, 0.76f, 0.74f, 0.72f, 0.70f, 0.68f, 0.66f, 0.64f, 0.62f, 0.60f, 0.58f, 0.56f, 0.54f, 0.52f, 0.50f, 0.48f, 0.46f, 0.44f, 0.42f, 0.40f, 0.38f, 0.36f, 0.34f, 0.32f, 0.30f, 0.28f, 0.26f, 0.24f};
    private int fakesLen = fakes.length;
    private float data[] = new float[6];
    private boolean valueSetted = false;
    private Point[] points = new Point[6];
    private Point[] valuePoints = new Point[6];
    private Paint bgPaint;
    private Paint linePaint;
    private Paint fgPaint;
    private Path bgPath;
    private Path fgPath;
    private int hw;
    private int hh;
    private int cw;
    private int ch;

    public void setData(float[] data) {
        valueSetted = data != null;
        this.data = data;
    }

    private void init() {
        this.width = DEFAULT_WIDTH;
        this.height = (int) (width * 0.88f);
        hw = (int) (width / 4f);
        hh = (int) (height / 2f);
        cw = (int) (width / 2f);
        ch = (int) (height / 2f);
        points[0] = new Point((int) (3f / 4 * width), 0);
        points[1] = new Point(width, height / 2);
        points[2] = new Point((int) (3f / 4 * width), height);
        points[3] = new Point((int) (1f / 4 * width), height);
        points[4] = new Point(0, height / 2);
        points[5] = new Point((int) (1f / 4 * width), 0);
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        fgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fgPaint.setColor(fgColor);
        fgPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bgPath = new Path();
        bgPath.moveTo(points[0].x, points[0].y);
        bgPath.lineTo(points[1].x, points[1].y);
        bgPath.lineTo(points[2].x, points[2].y);
        bgPath.lineTo(points[3].x, points[3].y);
        bgPath.lineTo(points[4].x, points[4].y);
        bgPath.lineTo(points[5].x, points[5].y);
        bgPath.lineTo(points[0].x, points[0].y);
        bgPath.close();
        for (int i = 0; i < 6; i++) {
            valuePoints[i] = new Point();
        }
        fgPath = new Path();
    }

    private void initFGPath() {
        valuePoints[0].set((int) (cw + hw * values[0]), (int) (ch - hh * values[0]));
        valuePoints[1].set((int) (cw + hw * 2 * values[1]), ch);
        valuePoints[2].set((int) (cw + hw * values[2]), (int) (ch + hh * values[2]));
        valuePoints[3].set((int) (cw - hw * values[3]), (int) (ch + hh * values[3]));
        valuePoints[4].set((int) (cw - hw * 2 * values[4]), ch);
        valuePoints[5].set((int) (cw - hw * values[5]), (int) (ch - hh * values[5]));
        fgPath.reset();
        fgPath.moveTo(valuePoints[0].x, valuePoints[0].y);
        fgPath.lineTo(valuePoints[1].x, valuePoints[1].y);
        fgPath.lineTo(valuePoints[2].x, valuePoints[2].y);
        fgPath.lineTo(valuePoints[3].x, valuePoints[3].y);
        fgPath.lineTo(valuePoints[4].x, valuePoints[4].y);
        fgPath.lineTo(valuePoints[5].x, valuePoints[5].y);
        fgPath.lineTo(valuePoints[0].x, valuePoints[0].y);
        fgPath.close();
    }

    public HexagonView(Context context) {
        super(context);
        init();
    }

    public HexagonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HexagonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    int i = 0;
    int j = 0;
    float diff[];
    int frame = 15;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        i++;
        // draw bg
        canvas.drawPath(bgPath, bgPaint);
        // draw line
        for (int i = 0; i < 3; i++) {
            canvas.drawLine(points[i].x, points[i].y, points[i + 3].x, points[i + 3].y, linePaint);
        }
        // draw fg
        if (!valueSetted) {
            // draw fake value
            values[0] = fakes[i % fakesLen];
            values[1] = fakes[(i + 10) % fakesLen];
            values[2] = fakes[(i + 20) % fakesLen];
            values[3] = fakes[(i + 30) % fakesLen];
            values[4] = fakes[(i + 20) % fakesLen];
            values[5] = fakes[(i + 10) % fakesLen];
            initFGPath();
            canvas.drawPath(fgPath, fgPaint);
            postInvalidate();
        } else {
            // animate to final value
            if (diff == null) {
                diff = new float[6];
                for (int i = 0; i < 6; i ++) {
                    diff[i] = data[i] - values[i];
                }
            }
            j ++;
            for (int i = 0; i < 6; i++) {
                values[i] += diff[i] / frame;
            }
            initFGPath();
            canvas.drawPath(fgPath, fgPaint);
            if (j <= frame) {
                postInvalidate();
            }
        }
    }
}
