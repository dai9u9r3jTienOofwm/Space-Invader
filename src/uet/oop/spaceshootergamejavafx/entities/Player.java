package uet.oop.spaceshootergamejavafx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.geometry.Point2D;
import uet.oop.spaceshootergamejavafx.entities.BulletTask;
import uet.oop.spaceshootergamejavafx.entities.Bullet;
import java.util.List;

/**
 * Represents the player in the game with movement, rendering, shooting, and health management.
 */
public class Player extends GameObject {

    // Hitbox dimensions
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    // Movement speed
    private static final double SPEED = 5;

    // Movement flags
    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveForward;
    private boolean moveBackward;
    private boolean shootPressed;
    private boolean focusPressed;

    private Image playerImage;
    private Image playerLeftImage;
    private Image playerRightImage;

    // Player attributes
    private int health;
    private int powerLevel;
    private int pointLevel;

    // State flag for removal
    private boolean dead;

    /**
     * Constructs a Player at the given position.
     *
     * @param x initial X position
     * @param y initial Y position
     */
    public Player(double x, double y) {
        super(x, y, WIDTH, HEIGHT);
        this.health = 5;
        this.powerLevel = 1;
        this.pointLevel = 0;
        this.dead = false;

        // Load images
        this.playerImage = new Image("img/player.png");
        this.playerLeftImage = new Image("img/player_left.png");
        this.playerRightImage = new Image("img/player_right.png");

        this.moveForward = false;
        this.moveBackward = false;
        this.moveLeft = false;
        this.moveRight = false;
        this.shootPressed = false;
        this.focusPressed = false;
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    /**
     * Updates player position based on movement flags.
     */
    @Override
    public void update(float deltaTime) {
        double speed = SPEED;
        if (isFocus()) {
            speed = SPEED / 2;
        }
        if (isMoveForward()) {
            setY(getY() - speed * deltaTime);
        }
        if (isMoveBackward()) {
            setY(getY() + speed * deltaTime);
        }
        if (isMoveLeft()) {
            setX(getX() - speed * deltaTime);
        }
        if (isMoveRight()) {
            setX(getX() + SPEED * deltaTime);
        }
        if (isShooting()) {
            if (isFocus()) {
                fireBullet(true, SpaceShooter.getPlayerBullets());
            } else {
                fireBullet(false, SpaceShooter.getPlayerBullets());
            }
        } 
        if (health <= 0) {
            setDead(true);
        }
        
    }

    private boolean isMoveForward() { return moveForward; }
    private boolean isMoveBackward() { return moveBackward; }
    private boolean isMoveLeft() { return moveLeft; }
    private boolean isMoveRight() { return moveRight; }
    private boolean isShooting() { return shootPressed; }
    private boolean isFocus() { return focusPressed; }


    /**
     * Renders the player on the canvas.
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(playerImage, getX(), getY(), WIDTH, HEIGHT);
        if (isMoveLeft()) {
            gc.drawImage(playerLeftImage, getX(), getY(), WIDTH, HEIGHT);
        }
        if (isMoveRight()) {
            gc.drawImage(playerRightImage, getX(), getY(), WIDTH, HEIGHT);
        }
    }

    public void fireBullet(boolean isFocus, List<Bullet> bullets) {
        if (isFocus) {
            BulletTask.spamBullet(bullets,getPosition(), new Point2D(0, 1), powerLevel * 2);
        } else {
            BulletTask.spamBullet(bullets,getPosition(), new Point2D(0, 1), powerLevel);
            BulletTask.spamBullet(bullets,getPosition(), new Point2D(-0.3f, 1), powerLevel);
            BulletTask.spamBullet(bullets,getPosition(), new Point2D(0.3f, 1), powerLevel);
        }
    }

    /**
     * Handles key press events.
     */
    public void handleKeyPress(KeyCode key) {
        switch (key) {
            case LEFT:
                moveLeft = true;
                break;
            case RIGHT:
                moveRight = true;
                break;
            case UP:
                moveForward = true;
                break;
            case DOWN:
                moveBackward = true;
                break;
            case Z:
                shootPressed = true;
                break;
            case SHIFT:
                focusPressed = true;
                break;
            default:
                // Do nothing for other keys
                break;
        }  
    }              
    public void handleKeyRelease(KeyCode key) {
        switch (key) {
            case LEFT:
                moveLeft = false;
                break;
            case RIGHT:
                moveRight = false;
                break;
            case UP:
                moveForward = false;
                break;
            case DOWN:
                moveBackward = false;
                break;
            case Z:
                shootPressed = false;
                break;
            case SHIFT:
                focusPressed = false;
                break;
            default:
                // Do nothing for other keys
                break;
        }
    }

    public int getHealth() {
        return health;
    }

    public void addHealth() {
        health += 1;
    }

    public void takeDamage() {
        health += 1;
    }

    public void addPower() {
        powerLevel += 1;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void addPoints(int points) {
        pointLevel += points;
        if (pointLevel >= 50) {
            addHealth();
        }
        if (pointLevel >= 100) {
            addHealth();
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public boolean isDead() {
        return dead;
    }
}

