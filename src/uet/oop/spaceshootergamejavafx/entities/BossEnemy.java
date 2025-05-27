package uet.oop.spaceshootergamejavafx.entities;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.spaceshootergamejavafx.entities.*;

/**
 * Skeleton for BossEnemy. Students must implement behavior
 * without viewing the original implementation.
 */
public class BossEnemy extends GameObject {

    // Health points of the boss
    private int health;
    private int currentPhrase;
    private float stateTime;
    private boolean dead;
    private Image bossImage;


    // Hitbox dimensions
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    // Horizontal movement speed
    private double horizontalSpeed;

    /**
     * Constructs a BossEnemy at the given coordinates.
     * @param x initial X position
     * @param y initial Y position
     */
    public BossEnemy(double x, double y) {
        super(x, y, WIDTH, HEIGHT);
        this.health = 100; // Example health value
        this.currentPhrase = 0; // Initial phase
        this.stateTime = 0; // Time since last state change
        this.dead = false; // Boss is initially alive
        this.bossImage = new Image("res/boss.png"); // Load boss sprite
        this.horizontalSpeed = 2.0; // Example horizontal speed
        // TODO: initialize health, speeds, and load resources

    }

    /**
     * Update boss's position and behavior each frame.
     */
    @Override
    public void update(float deltaTime) {
        stateTime += deltaTime;
        if ((health <= 55 || stateTime >= 60 ) && currentPhrase == 1 ) {
            transitionToSpellCard1(deltaTime);
         }

         if ((health <= 5 || stateTime >= 120 )&& currentPhrase == 2) {
            health = 100;
            transitionToSpellCard2();
         }
         if (health <= 0) {
            setDead(true); // Mark boss as dead
         }
        // TODO: implement vertical and horizontal movement
    }

    /**
     * Inflicts damage to the boss.
     */
    public void takeDamage() {
        // TODO: decrement health, mark dead when <= 0
        if (health > 0) {
            health--;
        } else {
            setDead(true);
        }
    }

    public void transitionToSpellCard1(float deltaTime) {
        currentPhrase = 2;
        if (stateTime == 75) {
            horizontalSpeed = 4.0; // Increase speed for phase 1
            setY(getY() + horizontalSpeed * deltaTime);
        }
        if (stateTime == 100) {
            horizontalSpeed = 3.0;
            setY(getY() - horizontalSpeed * deltaTime);
        }
        if ( getY() <= 100) {
            setY(100);
        }
        if (getY() >= 300) {
            setY(300);
        }
        BulletTask.spamRadialBullets(SpaceShooter.getBossBullets(), getPosition(), 50);
    }

    public void transitionToSpellCard2() {
        currentPhrase = 3;
        horizontalSpeed = 0; // Increase speed for phase 2
        BulletTask.spamSpiralBullets(null, position, 100, HEIGHT);
    }
    

    /**
     * Render the boss on the canvas.
     * @param gc graphics context
     */
    @Override
    public void render(GraphicsContext gc) {
        // TODO: draw boss sprite or placeholder
        gc.drawImage(bossImage, getX(), getY(), WIDTH, HEIGHT);
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public boolean isDead() {
        return dead;
    }
}
