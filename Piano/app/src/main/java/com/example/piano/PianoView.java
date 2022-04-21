package com.example.piano;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PianoView extends View {
    public static final int NUMBER_KEYS = 14;
    private Paint black, white, green, blackStroke;
    private ArrayList<Key> blacks, whites;
    private int keyWidth, keyHeight;

    private SoundManager soundManager;

    public PianoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        soundManager = new SoundManager();
        soundManager.init(context);

        black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);
        white = new Paint();
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);
        green = new Paint();
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);
        blackStroke = new Paint();
        blackStroke.setColor(Color.BLACK);
        blackStroke.setStrokeWidth(2);

        blacks = new ArrayList<Key>();
        whites = new ArrayList<Key>();

        soundManager = SoundManager.getInstance();
        soundManager.init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        keyWidth = w / NUMBER_KEYS;
        keyHeight = h;
        int count = 15;
        for(int i=0; i<NUMBER_KEYS; i++){
            int left = i*keyWidth;
            int right = left + keyWidth;
            RectF rect = new RectF(left, 0, right, keyHeight);
            whites.add(new Key(i+1, rect));

            if (i != 0 && i!=3 && i!=7 && i!=10){
                rect = new RectF((float) (i-1)*keyWidth + 0.75f*keyWidth,
                        0,
                        (float) i*keyWidth+0.25f*keyWidth,
                        0.67f*keyHeight);
                blacks.add(new Key(count++, rect));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Key k:whites){
            canvas.drawRect(k.rect, k.isDown ? green : white);
        }

        for(int i=1; i<NUMBER_KEYS; i++)
            canvas.drawLine(i*keyWidth, 0, i*keyWidth, keyHeight, blackStroke);

        for (Key k:blacks){
            canvas.drawRect(k.rect, k.isDown ? green : black);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        boolean isDownAction = action == MotionEvent.ACTION_DOWN ||
                                action == MotionEvent.ACTION_MOVE;

        for (int touchIndex=0; touchIndex<event.getPointerCount(); touchIndex++){
            float x = event.getX(touchIndex);
            float y = event.getY(touchIndex);

            for (Key k:whites){
                boolean bPress = false;
                int keySound = k.sound;
                if (k.rect.contains(x,y)){
                    for (Key kb:blacks){
                        if (kb.rect.contains(x,y)) {
                            bPress = true;
                            keySound = kb.sound;
                            kb.isDown = isDownAction;
                        } else {
                            kb.isDown = false;
                        }
                    }
                    if (bPress) k.isDown = false;
                    else {
                        keySound = k.sound;
                        k.isDown = isDownAction;
                    }
                    if (isDownAction) keyPlaySound(keySound);
                } else {
                    k.isDown = false;
                }
            }
        }
        keyPlaySound(1);

        invalidate();

        return true;
    }

    private void keyPlaySound(int keySound){
        switch (keySound) {
            case 1:
                soundManager.playSound(R.raw.c2);
                break;
            case 2:
                soundManager.playSound(R.raw.d2);
                break;
            case 3:
                soundManager.playSound(R.raw.e2);
                break;
            case 4:
                soundManager.playSound(R.raw.f2);
                break;
            case 5:
                soundManager.playSound(R.raw.g2);
                break;
            case 6:
                soundManager.playSound(R.raw.a2);
                break;
            case 7:
                soundManager.playSound(R.raw.b2);
                break;
            case 8:
                soundManager.playSound(R.raw.c3);
                break;
            case 9:
                soundManager.playSound(R.raw.d3);
                break;
            case 10:
                soundManager.playSound(R.raw.e3);
                break;
            case 11:
                soundManager.playSound(R.raw.f3);
                break;
            case 12:
                soundManager.playSound(R.raw.g3);
                break;
            case 13:
                soundManager.playSound(R.raw.a3);
                break;
            case 14:
                soundManager.playSound(R.raw.b3);
                break;
            case 15:
                soundManager.playSound(R.raw.db2);
                break;
            case 16:
                soundManager.playSound(R.raw.eb2);
                break;
            case 17:
                soundManager.playSound(R.raw.gb2);
                break;
            case 18:
                soundManager.playSound(R.raw.ab2);
                break;
            case 19:
                soundManager.playSound(R.raw.bb2);
                break;
            case 20:
                soundManager.playSound(R.raw.db3);
                break;
            case 21:
                soundManager.playSound(R.raw.eb3);
                break;
            case 22:
                soundManager.playSound(R.raw.gb3);
                break;
            case 23:
                soundManager.playSound(R.raw.ab3);
                break;
            case 24:
                soundManager.playSound(R.raw.bb3);
                break;
            default:
                break;
        }
    }
}
