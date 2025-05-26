package uet.oop.spaceshootergamejavafx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.List;
import javafx.geometry.Point2D;

/**
 * Skeleton for Enemy. Students must implement movement, rendering,
 * and death state without viewing the original implementation.
 */
public class EnemyType2 extends GameObject {

    // Hitbox dimensions
    protected static final int WIDTH = 40;
    protected static final int HEIGHT = 40;

    // Movement speed
    public static double SPEED = 20;
    private int health;


    // Flag to indicate if enemy should be removed
    private int targetX;
    private boolean dead;
    private Image Type2Image;

    /**
     * Constructs an Enemy at the given coordinates.
     * @param x initial X position
     * @param y initial Y position
     */
    public EnemyType2(double x, double y,int targetX) {
        super(x, y, WIDTH, HEIGHT);
        this.Type2Image = new Image("img/enemy2.png");
        this.dead = false;
        this.health = 15;
        this.targetX = targetX;

        // TODO: load sprite if needed and initialize dead flag
    }

    /**
     * Updates enemy position each frame.
     */
    @Override
    public void update(float deltaTime) {
        if (getX() < targetX) {
            setX(getX() + SPEED * deltaTime);
        } else if (getX() > targetX) {
            setX(getX() - SPEED * deltaTime);
        } else {
            fireBullet();
        }

        if (health == 0) {
            setDead(true);
        }

    }

    /**
     * Renders the enemy on the canvas.
     * @param gc the GraphicsContext to draw on
     */
    @Override
    public void render(GraphicsContext gc) {
        // TODO: draw sprite or fallback shape (e.g., colored rectangle)
        gc.drawImage(Type2Image, getX(), getY(), WIDTH, HEIGHT);
    }

    public void fireBullet() {
        BulletTask.fireRadialWave(20, 2);
    }

    public void takeDamage() {
        this.health -= 1;
    }
    /**
     * Returns the current width of the enemy.
     * @return WIDTH
     */
    @Override
    public double getWidth() {
        // TODO: return width
        return WIDTH;
    }

    /**
     * Returns the current height of the enemy.
     * @return HEIGHT
     */
    @Override
    public double getHeight() {
        // TODO: return height
        return HEIGHT;
    }

    public int getHealth() {
        return health;
    }


    /**
     * Marks this enemy as dead (to be removed).
     * @param dead true if enemy should be removed
     */
    public void setDead(boolean dead) {
        // TODO: update dead flag
    }

    /**
     * Checks if this enemy is dead.
     * @return true if dead, false otherwise
     */
    @Override
    public boolean isDead() {
        // TODO: return dead flag
        return dead;
    }
}