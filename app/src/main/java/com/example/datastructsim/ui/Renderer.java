package com.example.datastructsim.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.os.Handler;

import com.example.datastructsim.domain.LinkedListModel;
import com.example.datastructsim.domain.model.Action;

import java.util.List;

public class Renderer extends View {
    private LinkedListModel list;
    private Paint boxPaint;
    private Paint textPaint;
    private Handler handler;
    private Runnable animationRunnable;
    private boolean isAnimating = false;

    public Renderer(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init(){
        list = new LinkedListModel();
        boxPaint = new Paint();
        boxPaint.setColor(Color.parseColor("#2196f3"));
        boxPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        handler = new Handler(Looper.getMainLooper());
    }

    public void setListCode(String code){
        stopAnimation();
        list.setCode(code);
        playActions();
    }

    public void stopAnimation() {
        if (handler != null && animationRunnable != null) {
            handler.removeCallbacks(animationRunnable);
        }
        isAnimating = false;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int boxCount = list.getSize();

        if (boxCount == 0) {
            Paint emptyPaint = new Paint();
            emptyPaint.setColor(Color.GRAY);
            emptyPaint.setTextSize(50f);
            emptyPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Empty List", width / 2f, height / 2f, emptyPaint);
            return;
        }

        float boxWidth = width / 6f;
        float startX = (width - (boxCount * boxWidth)) / 2f;
        float top = height / 2f - 100;
        float bottom = height / 2f + 100;

        Paint arrowPaint = new Paint();
        arrowPaint.setColor(Color.GRAY);
        arrowPaint.setStrokeWidth(8f);
        arrowPaint.setAntiAlias(true);

        for(int i = 0; i < boxCount; i++){
            float left = startX + i * boxWidth;
            float right = left + boxWidth - 10;

            if (i == list.pointer) {
                boxPaint.setColor(Color.parseColor("#E91E63"));
            } else {
                boxPaint.setColor(Color.parseColor("#2196f3"));
            }

            canvas.drawRect(left, top, right, bottom, boxPaint);

            canvas.drawText(
                    String.valueOf(list.getIdx(i)),
                    left + (boxWidth - 10) / 2f,
                    height / 2f + 15,
                    textPaint
            );

            if (i < boxCount - 1) {
                float arrowStartX = right;
                float arrowEndX = startX + (i + 1) * boxWidth;
                float centerY = height / 2f;

                canvas.drawLine(arrowStartX, centerY, arrowEndX, centerY, arrowPaint);
                canvas.drawLine(arrowEndX - 20, centerY - 20, arrowEndX, centerY, arrowPaint);
                canvas.drawLine(arrowEndX - 20, centerY + 20, arrowEndX, centerY, arrowPaint);
            }
        }
    }

    public void playActions() {
        if (isAnimating) {
            stopAnimation();
        }

        List<Action> actions = list.getActions();
        if (actions.isEmpty()) {
            return;
        }

        isAnimating = true;

        animationRunnable = new Runnable() {
            int index = 0;

            @Override
            public void run() {
                if (index >= actions.size()) {
                    isAnimating = false;
                    return;
                }

                if(actions.get(index) == null)
                    return;

                actions.get(index).run(list);
                invalidate();
                index++;
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(animationRunnable);
    }

    public LinkedListModel getModel() {
        return list;
    }
}