package uet.oop.spaceshootergamejavafx.entities;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.geometry.Point2D;

/**
 * Skeleton for EnemyBullet. Students must implement movement,
 * rendering, and state management.
 */
public class EnemyBullet extends GameObject {

    // Dimensions of the enemy bullet
    public static final int WIDTH = 4;
    public static final int HEIGHT = 20;

    // Movement speed of the bullet
    private static final double SPEED = 200;
    private Point2D direction;
    private Image enemyBulletImage;


    // Flag indicating whether the bullet should be removed
    private boolean dead;

    /**
     * Constructs an EnemyBullet at the given position.
     * @param position initial X position
     * @param direction initial Y position
     */
    public EnemyBullet(Point2D position, Point2D direction) {
        super(position.getX(), position.getY(), WIDTH, HEIGHT);
        this.direction = direction.normalize(); // đảm bảo vector đơn vị
        this.enemyBulletImage = new Image("img/enemy_bullet.png"); // Thay đúng đường dẫn ảnh
        this.dead = false;
    }

    /**
     * Updates bullet position each frame.
     */
    @Override
    public void update(float deltaTime) {
        // Cập nhật vị trí theo hướng
        double dx = direction.getX() * SPEED * deltaTime;
        double dy = direction.getY() * SPEED * deltaTime;

        setX(getX() + dx);
        setY(getY() + dy);

        // Nếu ra khỏi màn hình thì đánh dấu là dead
        if (getY() < 0 || getY() > 600 || getX() < 0 || getX() > 800) {
            setDead(true);
        }
    }

    /**
     * Renders the bullet on the canvas.
     * @param gc the GraphicsContext to draw on
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(enemyBulletImage, getX(), getY(), WIDTH, HEIGHT);
        // TODO: draw bullet (e.g., filled rectangle or sprite)
    }

    /**
     * Returns the width of the bullet.
     * @return WIDTH
     */
    @Override
    public double getWidth() {
        // TODO: return width
        return WIDTH;
    }

    /**
     * Returns the height of the bullet.
     * @return HEIGHT
     */
    @Override
    public double getHeight() {
        // TODO: return height
        return HEIGHT;
    }

    /**
     * Marks this bullet as dead (to be removed).
     * @param dead true if bullet should be removed
     */
    public void setDead(boolean dead) {
        this.dead = dead;
        // TODO: update dead flag
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
