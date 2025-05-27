package uet.oop.spaceshootergamejavafx.entities;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;



/**
 * Utility class to handle bullet spawning and management.
 */
public class BulletTask {

    /**
     * Spawns a single bullet with the specified direction and power.
     *
     * @param position   The position to spawn the bullet.
     * @param direction  The direction vector of the bullet.
     * @param powerLevel The power level of the bullet.
     * @param type       The type of bullet (e.g., "player", "enemy").
     */
    public static void spamBullet(List<Bullet> bullets, Point2D position, Point2D direction, int powerLevel) { 
        if (powerLevel < 60) {
            Bullet bullet = new Bullet(position, direction);
            bullets.add(bullet);
            addBulletToGame(bullet);
        }
        else if (powerLevel >= 60 && powerLevel < 100) {
            for (int i = 0; i < 2; i++) {
                Bullet bullet = new Bullet(position, direction);
            bullets.add(bullet);
            addBulletToGame(bullet);    
            }
        } 
        else if (powerLevel >= 100) {
            for (int i = 0; i < 3; i++) {
                Bullet bullet = new Bullet(position, direction);
                bullets.add(bullet);
                addBulletToGame(bullet);
            }
        }
    }

    public static void spamEBullet(List<EnemyBullet> bullets, Point2D position, Point2D direction) { 
        EnemyBullet bullet = new EnemyBullet(position, direction);
        bullets.add(bullet);
        addBulletToGame(bullet);
    }

/**
 * Spawns a radial wave of bullets.
 *
 * @param bullets     The list to store spawned bullets.
 * @param position    The center position of the wave.
 * @param bulletCount The number of bullets in the wave.
 * @param powerLevel  The power level of the bullets.
 * @param type        The type of bullets (e.g., "player", "enemy").
 */
public static void spamRadialBullets(List<EnemyBullet> bullets, Point2D position, int bulletCount) {
    double angleStep = 2 * Math.PI / bulletCount;

    for (int i = 0; i < bulletCount; i++) {
        double angle = i * angleStep;
        Point2D direction = new Point2D(Math.cos(angle), Math.sin(angle));
        spamEBullet(bullets, position, direction);
    }
}

/**
 * Spawns bullets in a spiral pattern.
 *
 * @param bullets     The list to store spawned bullets.
 * @param position    The center position of the spiral.
 * @param bulletCount The total number of bullets to spawn.
 * @param powerLevel  The power level of the bullets.
 * @param type        The type of bullets (e.g., "player", "enemy").
 * @param interval    The interval between each bullet spawn (in milliseconds).
 */
public static void spamSpiralBullets(List<EnemyBullet> bullets, Point2D position, int bulletCount,long interval) {
    AnimationTimer timer = new AnimationTimer() {
        private long lastTime = 0;
        private int spawnedCount = 0;
        private double currentAngle = 0;
        private final double angleStep = 2 * Math.PI / bulletCount;

        @Override
        public void handle(long now) {
            if (lastTime == 0 || (now - lastTime) >= interval * 1_000_000) {
                Point2D direction = new Point2D(Math.cos(currentAngle), Math.sin(currentAngle));
                spamEBullet(bullets, position, direction);
                currentAngle += angleStep;
                spawnedCount++;

                if (spawnedCount >= bulletCount) {
                    stop();
                }

                lastTime = now;
            }
        }
    };
    timer.start();
}


    /**
     * Adds a bullet to the game.
     * This method should integrate with the main game loop or object manager.
     *
     * @param bullet The bullet to add.
     */
    private static void addBulletToGame(GameObject bullet) {
        // Placeholder for game object manager integration
        // Example: GameObjectManager.add(bullet);
        System.out.println("Bullet added at: " + bullet.getX() + ", " + bullet.getY());
    }
}


