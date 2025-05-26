package uet.oop.spaceshootergamejavafx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.List;
import javafx.scene.input.KeyCode;
import javafx.geometry.Point2D;

/**
 * Skeleton for Enemy. Students must implement movement, rendering,
 * and death state without viewing the original implementation.
 */
public class EnemyType1 extends GameObject {

    // Hitbox dimensions
    protected static final int WIDTH = 30;
    protected static final int HEIGHT = 30;
    protected static final int targetY = 360;

    // Movement speed
    public static double SPEED = 12;
    public  int health;


    // Flag to indicate if enemy should be removed
    private boolean dead;
    private Image Type1Image;

    /**
     * Constructs an Enemy at the given coordinates.
     * @param x initial X position
     * @param y initial Y position
     */
    public EnemyType1(double x, double y) {
        super(x, y, WIDTH, HEIGHT);
        this.Type1Image = new Image("img/enemy1.png");
        this.dead = false;
        this.health = 4;

        // TODO: load sprite if needed and initialize dead flag
    }

    /**
     * Updates enemy position each frame.
     */
    @Override
    public void update(float deltaTime) {
        // TODO: implement vertical movement by SPEED
            if (getY() < targetY) {
        // Move the enemy down by adjusting its Y-coordinate
            setY(getY() + SPEED * deltaTime);
            }
        // Clamp the Y-coordinate to stop exactly at the target position
            if (getY() > targetY) {
            setY(targetY);
            fireBullet();
        }
        if (getHealth() == 0) {
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
        gc.drawImage(Type1Image, getX(), getY(), WIDTH, HEIGHT);
    }

    public void fireBullet() {
         BulletTask.spamBullet(getPosition(), new Point2D(0, 1), 10 ,"enemy");
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
