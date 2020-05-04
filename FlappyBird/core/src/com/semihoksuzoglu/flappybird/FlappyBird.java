package com.semihoksuzoglu.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    int sayac=0;
    BitmapFont font, font2;
    SpriteBatch batch;
    Texture background, bird, bee1, bee2, bee3, bee4;
    Random random;
    float birdX = 0, birdY = 0, velocity = 0, gravity = 0.4f, distince = 0, enemyVelocitiy = 5f, a;

    int gameState = 0, numberOfEnemies = 4, score = 0, scoredEnemy = 0;
    float[] enemyX = new float[numberOfEnemies];
    float[] enemyOffSet = new float[numberOfEnemies];
    float[] enemyOffSet2 = new float[numberOfEnemies];
    float[] enemyOffSet3 = new float[numberOfEnemies];
    float[] enemyOffSet4 = new float[numberOfEnemies];
    float[] b = new float[4];

    Circle birdCircle;
    Circle[] enemyCircle;
    Circle[] enemyCircle2;
    Circle[] enemyCircle3;
    Circle[] enemyCircle4;
    ShapeRenderer shapeRenderer;


    @Override
    public void create() {

        batch = new SpriteBatch();

        background = new Texture("background.png");
        bird = new Texture("birdd.png");
        bee1 = new Texture("bee2.png");
        bee2 = new Texture("bee2.png");
        bee3 = new Texture("bee2.png");
        bee4 = new Texture("bee2.png");

        birdX = Gdx.graphics.getWidth() / 3 - 250;
        birdY = Gdx.graphics.getHeight() - 650;
        distince = Gdx.graphics.getWidth() / 2;
        random = new Random();

        birdCircle = new Circle();
        enemyCircle = new Circle[numberOfEnemies];
        enemyCircle2 = new Circle[numberOfEnemies];
        enemyCircle3 = new Circle[numberOfEnemies];
        enemyCircle4 = new Circle[numberOfEnemies];

        shapeRenderer = new ShapeRenderer();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);

        font2 = new BitmapFont();
        font2.setColor(Color.WHITE);
        font2.getData().setScale(9);


        for (int i = 0; i < numberOfEnemies; i++) {
            enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distince;

            b[0] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
            enemyOffSet[i] = b[0];
            enemyOffSet2[i] = b[0];
            enemyOffSet3[i] = b[0];
            enemyOffSet4[i] = b[0];

            enemyCircle[i] = new Circle();
            enemyCircle2[i] = new Circle();
            enemyCircle3[i] = new Circle();
            enemyCircle4[i] = new Circle();

        }
    }

    @Override
    public void render() {

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState == 1) {

            if (enemyX[scoredEnemy] < Gdx.graphics.getWidth() / 3 - 250) {
                score++;
                if (scoredEnemy < numberOfEnemies - 1) {
                    scoredEnemy++;
                } else {
                    scoredEnemy = 0;
                }
            }

            if (Gdx.input.justTouched()) {
                if (Gdx.graphics.getWidth() / 2 + 30 > birdY) {
                    if (score < 4) {
                        velocity = -15;
                    } else if (score < 8) {
                        velocity = -13;
                    } else {
                        velocity = -10;
                    }

                }
            }

            for (int i = 0; i < numberOfEnemies; i++) {

                if (enemyX[i] < 15) {
                    enemyX[i] += distince * numberOfEnemies;

                    if (score < 6) {

                        a = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                        b[0] = a;
                        for (int j = 1; j < 2; j++) {
                            float c = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                            if (b[0] != c && Math.abs(c - a) > 92) {
                                b[j] = c;
                            } else {
                                j--;
                            }
                        }
                        enemyOffSet[i] = b[0];
                        enemyOffSet2[i] = b[1];
                        enemyOffSet3[i] = b[0];
                        enemyOffSet4[i] = b[0];
                        if (score % 2 == 0) {
                            enemyVelocitiy++;
                        }

                    } else if (score < 10) {
                        int temp = 0;
                        b[0] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                        for (int j = 0; j < 3; ) {

                            float c = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

                            for (int k = 0; k < (j + 1); k++) {
                                temp = k;
                                if (b[k] == c || Math.abs(b[k] - c) < 100) {
                                    break;
                                }
                            }
                            if (b[temp] != c && Math.abs(b[temp] - c) > 100) {
                                b[j] = c;
                                j++;
                            }

                        }
                        enemyOffSet[i] = b[0];
                        enemyOffSet2[i] = b[1];
                        enemyOffSet3[i] = b[2];
                        enemyOffSet4[i] = b[0];

                        System.out.println("deneme 1 b[0]" + b[0]);
                        System.out.println("deneme 1 b[1]" + b[1]);
                        System.out.println("deneme 1 b[2]" + b[2]);
                        System.out.println("deneme 1 b[3]" + b[3]);
                        if (score % 2 == 0) {
                            enemyVelocitiy++;
                        }

                    } else {
                        int temp = 0;
                        for (int j = 0; j < 4; ) {

                            float c = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

                            for (int k = 0; k < (j + 1); k++) {
                                temp = k;
                                if (b[k] == c || Math.abs(c - b[k]) < 100) {
                                    break;
                                }
                            }
                            if (b[temp] != c && Math.abs(b[temp] - c) > 100) {
                                b[j] = c;
                                j++;
                            }

                        }

                        System.out.println("deneme 2 b[0]" + b[0]);
                        System.out.println("deneme 2 b[1]" + b[1]);
                        System.out.println("deneme 2 b[2]" + b[2]);
                        System.out.println("deneme 2 b[3]" + b[3]);
                        enemyOffSet[i] = b[0];
                        enemyOffSet2[i] = b[1];
                        enemyOffSet3[i] = b[2];
                        enemyOffSet4[i] = b[3];

                        if (score % 2 == 0) {
                            enemyVelocitiy++;
                        }

                    }

                } else {
                    enemyX[i] -= enemyVelocitiy;
                }

                batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet[i], Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 11);
                batch.draw(bee2, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet2[i], Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 11);
                batch.draw(bee3, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet3[i], Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 11);
                batch.draw(bee4, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet4[i], Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 11);

                enemyCircle[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 28, Gdx.graphics.getHeight() / 2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 22, Gdx.graphics.getWidth() / 28 - 11);
                enemyCircle2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 28, Gdx.graphics.getHeight() / 2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 22, Gdx.graphics.getWidth() / 28 - 11);
                enemyCircle3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 28, Gdx.graphics.getHeight() / 2 + enemyOffSet3[i] + Gdx.graphics.getHeight() / 22, Gdx.graphics.getWidth() / 28 - 11);
                enemyCircle4[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 28, Gdx.graphics.getHeight() / 2 + enemyOffSet4[i] + Gdx.graphics.getHeight() / 22, Gdx.graphics.getWidth() / 28 - 11);

            }

            if (birdY > 0) {
                velocity += gravity;
                birdY -= velocity;
            } else {
                 gameState = 2;
            }

        } else if (gameState == 0) {
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else if (gameState == 2) {

            font2.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - 325, Gdx.graphics.getHeight() / 2 + 100);

            if (Gdx.input.justTouched()) {
                birdY = Gdx.graphics.getHeight() - 650;

                for (int i = 0; i < numberOfEnemies; i++) {
                    enemyX[i] = Gdx.graphics.getWidth() - bee1.getWidth() / 2 + i * distince;


                    b[0] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffSet[i] = b[0];
                    enemyOffSet2[i] = b[0];
                    enemyOffSet3[i] = b[0];
                    enemyOffSet4[i] = b[0];

                    enemyCircle[i] = new Circle();
                    enemyCircle2[i] = new Circle();
                    enemyCircle3[i] = new Circle();
                    enemyCircle4[i] = new Circle();

                }
                enemyVelocitiy = 5f;
                score = 0;
                velocity = 0;
                scoredEnemy = 0;
                gameState = 1;

            }

        }

        batch.draw(bird, birdX, birdY, Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 11);

        font.draw(batch, String.valueOf(score), 150, 200);

        batch.end();

        birdCircle.set(birdX + Gdx.graphics.getWidth() / 28 + 2, birdY + Gdx.graphics.getHeight() / 22 + 2, Gdx.graphics.getWidth() / 28 - 9);
        //  shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //  shapeRenderer.setColor(Color.BLACK);
        //  shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);

        for (int i = 0; i < numberOfEnemies; i++) {

            //  shapeRenderer.circle(enemyCircle[i].x, enemyCircle[i].y, enemyCircle[i].radius);
            //  shapeRenderer.circle(enemyCircle2[i].x, enemyCircle2[i].y, enemyCircle2[i].radius);
            //  shapeRenderer.circle(enemyCircle3[i].x, enemyCircle3[i].y, enemyCircle3[i].radius);
            // shapeRenderer.circle(enemyCircle4[i].x, enemyCircle4[i].y, enemyCircle4[i].radius);

             if (Intersector.overlaps(birdCircle, enemyCircle[i]) || Intersector.overlaps(birdCircle, enemyCircle2[i]) || Intersector.overlaps(birdCircle, enemyCircle4[i]) || Intersector.overlaps(birdCircle, enemyCircle3[i])) {
                 sayac++;
                 System.out.println("aaa2 "+sayac);
                 gameState = 2;
             }
        }
        shapeRenderer.end();

    }

    @Override
    public void dispose() {

    }


}

