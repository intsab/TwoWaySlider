package com.intandif.twowayseekbar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class BarView {
    private float barHeight;
    private float barRadius;
    private Paint barLeftStrip;
    private Paint barCenterStrip;
    private Paint barRightStrip;

    BarView(float height, int color, int highlightColor) {
        this.barHeight = height;
        this.barRadius = this.barHeight / 2.0F;

        this.barLeftStrip = new Paint();
        this.barLeftStrip.setAntiAlias(true);
        this.barLeftStrip.setColor(color);
        this.barLeftStrip.setStyle(Paint.Style.FILL);


        this.barCenterStrip = new Paint();
        this.barCenterStrip.setAntiAlias(true);
        this.barCenterStrip.setColor(highlightColor);
        this.barCenterStrip.setStyle(Paint.Style.FILL);


        this.barRightStrip = new Paint();
        this.barRightStrip.setAntiAlias(true);
        this.barRightStrip.setColor(color);
        this.barRightStrip.setStyle(Paint.Style.FILL);
    }


    void draw(Canvas canvas, int width, float offsetLeft, float offsetRight, float offsetY) {
        if (offsetLeft > 0.0F) {
            this.draw(canvas, this.barLeftStrip, 0.0F, offsetLeft, offsetY);
        }

        if (offsetRight - offsetLeft > 0.0F) {
            this.draw(canvas, this.barCenterStrip, offsetLeft, offsetRight - offsetLeft, offsetY);
        }

        if ((float) width - offsetRight > 0.0F) {
            this.draw(canvas, this.barRightStrip, offsetRight, (float) width - offsetRight, offsetY);
        }

    }

    void draw(Canvas canvas, Paint paint, float offsetX, float width, float offsetY) {
        canvas.drawRoundRect(new RectF(offsetX, offsetY, offsetX + width, offsetY + this.barHeight), this.barRadius, this.barRadius, paint);
    }
}
