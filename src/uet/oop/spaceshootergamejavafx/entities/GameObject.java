package uet.oop.spaceshootergamejavafx.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

/**
 * Skeleton for GameObject. Base class for all game objects.
 * Subclasses must implement the abstract methods below.
 */
public abstract class GameObject {
    // Position and size
    protected Point2D position;
    protected double width;
    protected double height;

    /**
     * Constructs a GameObject at the specified position with dimensions.
     * @param x initial X position
     * @param y initial Y position
     * @param width object width
     * @param height object height
     */
    public GameObject(double x, double y, double width, double height) {
        this.position = new Point2D(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Updates the game object's state each frame.
     */
    public abstract void update(float deltaTime);

    /**
     * Renders the game object on the canvas.
     * @param gc graphics context
     */
    public abstract void render(GraphicsContext gc);

    /**
     * Checks whether this object should be removed from the game.
     * @return true if dead/removed
     */
    public abstract boolean isDead();

    public Point2D getPosition() {
        return position;
    }

    /**
     * Returns the current X coordinate.
     * @return x position
     */
    public double getX() {
        return position.getX();
    }

    public void setX(double x) {
        this.position = new Point2D(x, position.getY());
    }

    /**
     * Returns the current Y coordinate.
     * @return y position
     */
    public double getY() {
        return position.getY();
    }

    public void setY(double y) {
        this.position = new Point2D(position.getX(), y);
    }

    /**
     * Returns the bounding box for collision detection.
     * @return bounds of this object
     */
    public Bounds getBounds() {
        // Provided by engine; do not modify
        return new javafx.scene.shape.Rectangle(
            getX() - getWidth() / 2,
            getY() - getHeight() / 2,
            getWidth(),
            getHeight()
        ).getBoundsInLocal();
    }

    /**
     * Returns the width of the object.
     * @return width
     */
    public abstract double getWidth();

    /**
     * Returns the height of the object.
     * @return height
     */
    public abstract double getHeight();
}
