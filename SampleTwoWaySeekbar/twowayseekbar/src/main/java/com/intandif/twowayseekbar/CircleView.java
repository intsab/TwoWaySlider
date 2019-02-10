package com.intandif.twowayseekbar;

import android.graphics.Canvas;
import android.graphics.Paint;

public class CircleView {
    private float circleRadius;
    private float circleBorderHeight;
    private int highliteColor;
    private Paint circleBorderPaint;
    private Paint circleFillPaint;
    boolean isHighlight = false;

    CircleView(float radius, float outlineSize, int outlineColor, int highlightColor) {
        this.circleRadius = radius;
        this.circleBorderHeight = outlineSize;
        this.highliteColor = highlightColor;
        this.circleBorderPaint = new Paint();
        this.circleBorderPaint.setAntiAlias(true);
        this.circleBorderPaint.setColor(outlineColor);
        this.circleBorderPaint.setStyle(Paint.Style.STROKE);
        this.circleBorderPaint.setStrokeWidth(outlineSize);
        this.circleFillPaint = new Paint();
        this.circleFillPaint.setAntiAlias(true);
        this.circleFillPaint.setStyle(Paint.Style.FILL);
        this.circleFillPaint.setStrokeWidth(0.0F);
    }

    void draw(Canvas canvas, float cx, float cy) {
        canvas.drawCircle(cx, cy, this.circleRadius, this.circleBorderPaint);
        if (this.isHighlight) {
            this.circleFillPaint.setColor(this.highliteColor);
        } else {
            this.circleFillPaint.setColor(-1);
        }

        canvas.drawCircle(cx, cy, this.circleRadius - this.circleBorderHeight / 2.0F, this.circleFillPaint);
    }
}
