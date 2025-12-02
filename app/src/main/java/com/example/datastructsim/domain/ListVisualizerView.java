package com.example.datastructsim.domain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.datastructsim.domain.model.Node;

import java.util.ArrayList;
import java.util.List;

public class ListVisualizerView extends View {
    private List<Node> elements = new ArrayList<>();
    private Paint boxPaint;
    private Paint textPaint;

    public ListVisualizerView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init(){
        boxPaint = new Paint();
        boxPaint.setColor(Color.parseColor("#2196f3"));

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40f);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setElements(List<Integer> newElements){
        elements.clear();
        for(int i : newElements){
            elements.add(new  Node(i));
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int boxCount = elements.size();

        if(boxCount == 0) return;

        float boxWidth = width/6f;
        float startX = (width - (boxCount * boxWidth)) / 2f;

        for(int i = 0; i < boxCount; i++){
            float left = startX + i * boxWidth;
            float top = height / 2f - 100;
            float right = left + boxWidth - 10;
            float bottom = height / 2f + 100;

            canvas.drawRect(left, top, right, bottom, boxPaint);
            canvas.drawText(String.valueOf(elements.get(i).getValue()),
                    left+boxWidth / 2f - 5, height / 2f + 15, textPaint);
        }

    }
}
