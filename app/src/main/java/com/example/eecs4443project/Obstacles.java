package com.example.eecs4443project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacles {

    private final static int ENEMY_DISTANCE = 1000;
    private final static int ENEMY_DISTANCE_FROM_DINO = 800;
    private List<Enemy> enemies = new ArrayList<>();
    private MainCharacter mainCharacter;
    private Bitmap cactus1;
    private Bitmap cactus2;
    private Random rand;

    public Obstacles(MainCharacter mainCharacter, Context context) {
        this.mainCharacter = mainCharacter;
        rand = new Random();
        cactus1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cactus1);
        cactus2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cactus2);

        // add the initial enemy to the list
        addEnemy(rand.nextInt(ENEMY_DISTANCE) + ENEMY_DISTANCE_FROM_DINO);
    }

    private void addEnemy(float x) {
        int type = rand.nextInt(2);
        if (type == 0) {
            enemies.add(new CactusEnemy(mainCharacter, (int) x, cactus1.getWidth(), cactus1.getHeight(), cactus1));
        } else {
            enemies.add(new CactusEnemy(mainCharacter, (int) x, cactus2.getWidth(), cactus2.getHeight(), cactus2));
        }
    }

    public Enemy[] getEnemies() {
        return enemies.toArray(new Enemy[enemies.size()]);
    }

    public void generateNewEnemies(float lastEnemyX) {
        float x = lastEnemyX + rand.nextInt(ENEMY_DISTANCE) + ENEMY_DISTANCE_FROM_DINO; // random distance between 500 and 1000 pixels
        addEnemy(x);
    }

    public float getLastEnemyX() {
        if (enemies.isEmpty()) {
            return 0;
        } else {
            Enemy lastEnemy = enemies.get(enemies.size() - 1);
            return lastEnemy.getX();
        }
    }

}