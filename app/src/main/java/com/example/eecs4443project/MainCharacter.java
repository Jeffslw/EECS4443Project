package com.example.eecs4443project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;

public class MainCharacter {

    public static final int LAND_POSY = 610;
    public static final float GRAVITY = 0.4f;
    private static final int NORMAL_RUN = 0;
    private static final int JUMPING = 1;

    private float posY;
    private float posX;
    private float speedX;
    private float speedY;
    private Rect rectBound;
    private int state = NORMAL_RUN;

    private AnimationDrawable normalRunAnim;
    private Bitmap jumping;

    public MainCharacter(Context context) {
        posX = 50;
        posY = LAND_POSY;
        rectBound = new Rect();
        normalRunAnim = new AnimationDrawable();
        normalRunAnim.addFrame(context.getResources().getDrawable(R.drawable.mc1), 90);
        normalRunAnim.addFrame(context.getResources().getDrawable(R.drawable.mc2), 90);
        jumping = BitmapFactory.decodeResource(context.getResources(), R.drawable.mc3);
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void draw(Canvas canvas) {
        switch(state) {
            case NORMAL_RUN:
                normalRunAnim.setBounds((int) posX, (int) posY, (int) posX + normalRunAnim.getIntrinsicWidth(), (int) posY + normalRunAnim.getIntrinsicHeight());
                normalRunAnim.draw(canvas);
                // moveRight(); // Makes dinosaur move right
                break;
            case JUMPING:
                canvas.drawBitmap(jumping, posX, posY, null);
                break;
        }
    }

    public void update() {
        if(state == NORMAL_RUN) {
            normalRunAnim.run();
        }
        if(posY >= LAND_POSY) {
            posY = LAND_POSY;
            if(state == JUMPING) {
                state = NORMAL_RUN;
            }
        } else {
            speedY += GRAVITY;
            posY += speedY;
        }
    }

    public void jump() {
        if(posY >= LAND_POSY) {
            speedY = -13f;
            posY += speedY;
            state = JUMPING;
        }
    }

    public Rect getBound() {
        rectBound = new Rect();
        rectBound.left = (int) posX + 5;
        rectBound.top = (int) posY;
        rectBound.right = (int) posX + normalRunAnim.getIntrinsicWidth() - 10;
        rectBound.bottom = (int) posY + normalRunAnim.getIntrinsicHeight();
        return rectBound;
    }

    public boolean collidesWith(Enemy enemy) {
        Rect characterBound = getBound();
        Rect enemyBound = enemy.getBounds();
        return Rect.intersects(characterBound, enemyBound);
    }

    public float getX() {
        return posX;
    }

    public void moveRight() {
        posX += 10; // move character 10 pixels to the right
    }
}