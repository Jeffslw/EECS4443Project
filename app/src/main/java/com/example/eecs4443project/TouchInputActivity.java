package com.example.eecs4443project;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TouchInputActivity extends AppCompatActivity implements View.OnTouchListener {

    private final static int DELAY = 100;
    private ImageView land;

    private GameLogic gameLogic;
    private GameView gameView;
    private MainCharacter mainCharacter;
    private Obstacles obstacles;
    private boolean isJumping = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_input);

        gameView = findViewById(R.id.game_view);

        // Create GameLogic instance and set it on the GameView
        mainCharacter = gameView.getMainCharacter();
        obstacles = gameView.getObstacles();
        gameLogic = new GameLogic(mainCharacter, obstacles, gameView, GameLogic.TOUCH_INPUT);
        gameView.setGameLogic(gameLogic);

        // Set touch listener on the gameView
        gameView.setOnTouchListener(this);

        // Display land ImageView
        land = (ImageView) findViewById(R.id.landImage);

        // Set the floor image as the background of the floor ImageView
        land.setBackgroundResource(R.drawable.land);

        handler.post(gameLoopRunnable);
    }

    private Runnable gameLoopRunnable = new Runnable() {
        @Override
        public void run() {
            gameLogic.update();
            gameView.invalidate();
            handler.postDelayed(this, 20);
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && !isJumping) {
            isJumping = true;
            gameLogic.jump();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isJumping = false;
                }
            }, DELAY);
        }
        return true;
    }

}
