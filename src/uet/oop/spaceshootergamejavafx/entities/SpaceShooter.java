package uet.oop.spaceshootergamejavafx.entities;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uet.oop.spaceshootergamejavafx.entities.*;

import java.util.ArrayList;
import java.util.List;

public class SpaceShooter extends Application {

    private static final int WIDTH = 480;
    private static final int HEIGHT = 640;

    private static GraphicsContext gc;

    private static List<Bullet> playerBullets = new ArrayList<>();
    private static List<EnemyBullet> enemyBullets = new ArrayList<>();
    private static List<EnemyType1> enemiesType1 = new ArrayList<>();
    private static List<EnemyType2> enemiesType2 = new ArrayList<>();
    private static List<EnemyBullet> bossBullets = new ArrayList<>();


    private Player player;
    private static BossEnemy boss;
    private boolean bossSpawned = false;

    public static List<Bullet> getPlayerBullets() {
        return playerBullets;
    }

    public static List<EnemyBullet> getEnemyBullets() {
        return enemyBullets;
    }

    public static List<EnemyType1> getEnemiesType1() {
        return enemiesType1;
    }

    public static List<EnemyType2> getEnemiesType2() {
        return enemiesType2;
    }

    public static BossEnemy getBoss() {
        return boss;
    }

    public static List<EnemyBullet> getBossBullets() {
        return bossBullets;
    }


    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Space Shooter");
        stage.show();

        player = new Player(WIDTH / 2.0 - 15, HEIGHT - 60);

        scene.setOnKeyPressed(e -> player.handleKeyPress(e.getCode()));
        scene.setOnKeyReleased(e -> player.handleKeyRelease(e.getCode()));

        new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                float deltaTime = (now - lastTime) / 1e9f;
                lastTime = now;

                update(deltaTime);
                render();
            }
        }.start();
    }

    private void update(float deltaTime) {
        for (EnemyBullet bb : bossBullets) {
            bb.update(deltaTime);
        }
        bossBullets.removeIf(GameObject::isDead);


        player.update(deltaTime);

        playerBullets.forEach(b -> b.update(deltaTime));
        enemyBullets.forEach(b -> b.update(deltaTime));
        enemiesType1.forEach(e -> e.update(deltaTime));
        enemiesType2.forEach(e -> e.update(deltaTime));

        if (boss != null) {
            boss.update(deltaTime);
        }

        playerBullets.removeIf(GameObject::isDead);
        enemyBullets.removeIf(GameObject::isDead);
        enemiesType1.removeIf(GameObject::isDead);
        enemiesType2.removeIf(GameObject::isDead);
        if (boss != null && boss.isDead()) {
            boss = null;
        }

        checkCollisions();
        spawnEnemies();
    }

    private void checkCollisions() {
        for (Bullet bullet : playerBullets) {
            for (EnemyType1 enemy : enemiesType1) {
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bullet.setDead(true);
                    enemy.health--;
                }
            }
            for (EnemyType2 enemy : enemiesType2) {
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bullet.setDead(true);
                    enemy.takeDamage();
                }
            }
            if (boss != null && bullet.getBounds().intersects(boss.getBounds())) {
                bullet.setDead(true);
                boss.takeDamage();
            }
        }

        for (EnemyBullet eb : enemyBullets) {
            if (eb.getBounds().intersects(player.getBounds())) {
                eb.setDead(true);
                player.takeDamage();
            }
        }
    }

    private void spawnEnemies() {
        if (!bossSpawned) {
            if (Math.random() < 0.02) {
                enemiesType1.add(new EnemyType1(Math.random() * (WIDTH - 30), -30));
            }
            if (Math.random() < 0.005) {
                enemiesType2.add(new EnemyType2(0, 50, (int) (Math.random() * (WIDTH - 40))));
            }

            if (enemiesType1.isEmpty() && enemiesType2.isEmpty()) {
                boss = new BossEnemy(WIDTH / 2.0 - 25, -100);
                bossSpawned = true;
            }
        }
    }

    private void render() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        player.render(gc);
        for (EnemyBullet bb : bossBullets) {
            bb.render(gc);
        }
        playerBullets.forEach(b -> b.render(gc));
        enemyBullets.forEach(b -> b.render(gc));
        enemiesType1.forEach(e -> e.render(gc));
        enemiesType2.forEach(e -> e.render(gc));
        if (boss != null) {
            boss.render(gc);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
