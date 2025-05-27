package uet.oop.spaceshootergamejavafx.entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Skeleton for Bullet. Students must implement movement,
 * rendering, and state management.
 */
public class Bullet extends GameObject {

    // Width and height of the bullet
    public static final int WIDTH = 4;
    public static final int HEIGHT = 8;

    public Point2D direction;
    public Image bulletImage;

    // Movement speed of the bullet
    private static final double SPEED = 300;

    // Flag to indicate if bullet should be removed
    private boolean dead;

    /**
     * Constructs a Bullet at the given position.
     * @param x initial X position
     * @param y initial Y position
     */
    public Bullet(Point2D position, Point2D direction) {
        super(position.getX(), position.getY() - 10, WIDTH, HEIGHT);
        this.direction = direction;
        this.bulletImage = new Image("img/player_bullet.png");
        // TODO: initialize dead flag if needed
        this.dead = false;
    }

    /**
     * Updates bullet position each frame.
     */
    @Override
    public void update(float deltaTime) {
        // TODO: move bullet vertically by SPEED
        setX(getX() + direction.getX() * SPEED * deltaTime);
        setY(getY() + direction.getY() * SPEED * deltaTime);

        if (getY() < 0 || getY() > 480 || getX() < 0 || getX() > 360) {
            setDead(true);
        }
    }

    /**
     * Renders the bullet on the canvas.
     * @param gc the GraphicsContext to draw on
     */
    @Override
    public void render(GraphicsContext gc) {
        // TODO: draw bullet (e.g., filled rectangle or sprite)
        gc.drawImage(bulletImage, getX(), getY(), WIDTH, HEIGHT);
    }

    /**
     * Returns current width of the bullet.
     * @return WIDTH
     */
    @Override
    public double getWidth() {
        // TODO: return bullet width
        return WIDTH;
    }

    /**
     * Returns current height of the bullet.
     * @return HEIGHT
     */
    @Override
    public double getHeight() {
        // TODO: return bullet height
        return HEIGHT;
    }

    /**
     * Marks this bullet as dead (to be removed).
     * @param dead true if bullet should be removed
     */
    public void setDead(boolean dead) {
        // TODO: update dead flag
        this.dead = dead;
    }

    /**
     * Checks if this bullet is dead.
     * @return true if dead, false otherwise
     */
    @Override
    public boolean isDead() {
        // TODO: return dead flag
        return dead;
    }
}
