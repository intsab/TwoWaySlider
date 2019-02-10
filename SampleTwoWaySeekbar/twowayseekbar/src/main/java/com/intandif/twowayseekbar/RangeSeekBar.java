package com.intandif.twowayseekbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class RangeSeekBar extends View {

    private int mTrackTintColor;
    private int mTrackHighlightTintColor;
    private float mTrackHeight;
    private float mThumbRadius;
    private float mThumbOutlineSize;
    private CircleView mMinValueThumb;
    private CircleView mMaxValueThumb;
    private BarView mTrack;
    private ArrayList<Integer> mValues;
    private int leastProgress;
    private int mMaxValue;
    private float mBeginTrackOffsetX;

    private onValueChangedListener mOnValueChangedListener;


    public RangeSeekBar(Context context) {
        super(context);
    }

    public RangeSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setOnValueChangedListener(onValueChangedListener onValueChangedListener) {
        this.mOnValueChangedListener = onValueChangedListener;
        this.invalidate();
    }


    public RangeSeekBar(Context context, AttributeSet attributes, int defaultAttributes) {
        super(context, attributes, defaultAttributes);

//        TypedArray typedArray = context.obtainStyledAttributes(attributes, Rs.styleable.RangeSliderView);

        this.mTrackTintColor = context.getResources().getColor(R.color.colorPrimary);

        //typedArray.getColor(Rs.styleable.RangeSliderView_trackTintColor, ContextCompat.getColor(context, Rs.color.trackTintColor));
        this.mTrackHighlightTintColor = context.getResources().getColor(R.color.colorAccent);

        //typedArray.getColor(Rs.styleable.RangeSliderView_trackHighlightTintColor, ContextCompat.getColor(context, Rs.color.trackHighlightTintColor));
        Resources resources = context.getResources();

        this.mTrackHeight = 8;//typedArray.getDimension(Rs.styleable.RangeSliderView_trackHeight, resources.getDimension(Rs.dimen.trackHeight));
        this.mThumbRadius = 22;//typedArray.getDimension(Rs.styleable.RangeSliderView_thumbRadius, resources.getDimension(Rs.dimen.thumbRadius));
        this.mThumbOutlineSize = 4;//typedArray.getDimension(Rs.styleable.RangeSliderView_thumbOutlineSize, resources.getDimension(Rs.dimen.thumbOutlineSize));


        this.mMinValueThumb = new CircleView(this.mThumbRadius, this.mThumbOutlineSize, this.mTrackTintColor, this.mTrackTintColor);
        this.mMaxValueThumb = new CircleView(this.mThumbRadius, this.mThumbOutlineSize, this.mTrackHighlightTintColor, this.mTrackTintColor);
        this.mTrack = new BarView(this.mTrackHeight, this.mTrackTintColor, this.mTrackHighlightTintColor);
        this.mValues = new ArrayList();

        for(int x=0;x<100;x++){
            mValues.add(x);
        }

        this.leastProgress = 1;
        this.mMaxValue = 100;
    }

    protected float progress(int value) {
        int index = this.mValues.indexOf(value);
        int count = this.mValues.size();
        float radius = this.mThumbRadius + this.mThumbOutlineSize / 2.0F;
        if (index == 0) {
            return radius;
        } else {
            return index == count - 1 ? (float) this.getWidth() - radius : ((float) this.getWidth() - radius * 2.0F) * (float) index / (float) count + radius;
        }
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float offsetY = (float) (this.getHeight() / 2);
        float minValueOffsetX = this.progress(this.leastProgress);
        float maxValueOffsetX = this.progress(this.mMaxValue);

        this.mTrack.draw(canvas, this.getWidth(), minValueOffsetX, maxValueOffsetX, offsetY - this.mTrackHeight / 2.0F);
        this.mMinValueThumb.draw(canvas, minValueOffsetX, offsetY);
        this.mMaxValueThumb.draw(canvas, maxValueOffsetX, offsetY);

    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != 3 && event.getAction() != 1) {
            float offsetX = event.getX();
            float radius = this.mThumbRadius + this.mThumbOutlineSize / 2.0F;
            switch (event.getAction()) {
                case 0:
                    float minValuePosition = this.progress(this.leastProgress);
                    if (offsetX >= minValuePosition - radius && offsetX <= minValuePosition + radius) {
                        this.mMinValueThumb.isHighlight = true;
                    } else {
                        float maxValuePosition = this.progress(this.mMaxValue);
                        if (offsetX >= maxValuePosition - radius && offsetX <= maxValuePosition + radius) {
                            this.mMaxValueThumb.isHighlight = true;
                        }
                    }

                    if (!this.mMinValueThumb.isHighlight && !this.mMaxValueThumb.isHighlight) {
                        this.mBeginTrackOffsetX = -1.0F;
                    } else {
                        this.mBeginTrackOffsetX = offsetX;
                        this.invalidate();
                    }
                    break;
                case 2:
                    if (this.leastProgress == this.mMaxValue && this.mBeginTrackOffsetX > -1.0F && offsetX > this.mBeginTrackOffsetX && !this.mMaxValueThumb.isHighlight) {
                        this.mMinValueThumb.isHighlight = false;
                        this.mMaxValueThumb.isHighlight = true;
                    }

                    int count = this.mValues.size();
                    int index = (int) (offsetX * (float) count / ((float) this.getWidth() - radius));
                    if (index < 0) {
                        index = 0;
                    } else if (index > count - 1) {
                        index = count - 1;
                    }

                    if (this.mMinValueThumb.isHighlight) {
                        if (index > this.mValues.indexOf(this.mMaxValue)) {
                            Log.d("SLIDER","IndexOfMax"+this.mValues.indexOf(this.mMaxValue)+" Index:"+index);
                            this.leastProgress = this.mMaxValue;
                        } else {
                            this.leastProgress = (Integer) this.mValues.get(index);
                        }
                    } else if (this.mMaxValueThumb.isHighlight) {
                        if (index < this.mValues.indexOf(this.leastProgress)) {
                            this.mMaxValue = this.leastProgress;
                        } else {
                            this.mMaxValue = (Integer) this.mValues.get(index);
                        }
                    }

                    if (this.mMinValueThumb.isHighlight || this.mMaxValueThumb.isHighlight) {
                        if (this.mOnValueChangedListener != null) {
                            this.mOnValueChangedListener.onValueChanged(this.leastProgress, this.mMaxValue);
                        }

                        this.invalidate();
                    }

                    break;

            }

            return true;
        } else {

            if(event.getAction()==1){
                if (this.mOnValueChangedListener != null) {
                    this.mOnValueChangedListener.onFinishScrolling(this.leastProgress, this.mMaxValue);
                }
            }
            this.mMinValueThumb.isHighlight = false;
            this.mMaxValueThumb.isHighlight = false;
            this.invalidate();
            return true;
        }
    }



    public abstract static class onValueChangedListener {
        public onValueChangedListener() {
        }

        public abstract void onValueChanged(int minimumValue, int maximumValue);
        public abstract void onFinishScrolling(int minimumValue, int maximumValue);
    }
}