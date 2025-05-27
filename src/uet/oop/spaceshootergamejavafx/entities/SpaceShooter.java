package uet.oop.spaceshootergamejavafx.entities;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import java.util.List;
import java.util.ArrayList;
import uet.oop.spaceshootergamejavafx.entities.*;

/**
 * Skeleton for SpaceShooter. Students must implement game loop,
 * spawning, collision checks, UI, and input handling.
 */
public class SpaceShooter extends Application {

    public static final int WIDTH = 880;
    public static final int HEIGHT = 480;
    public static int numLives = 3;

    private int score;
    private boolean bossExists;
    private boolean reset;
    private boolean levelUpShown;
    private boolean gameRunning;
    public static List<EnemyType1> enemiesType1;
    public static List<EnemyType2> enemiesType2;
    public static List<Bullet> playerBullets;
    public static List<EnemyBullet> enemyBullets;
    public static List<EnemyBullet> bossBullets;
    private BossEnemy bossEnemy;
    private Player player;
    private List<PowerUp> powerUps;


    // TODO: Declare UI labels, lists of GameObjects, player, root Pane, Scene, Stage

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // TODO: initialize primaryStage, scene, canvas, UI labels, root pane
        // TODO: set up event handlers
        // TODO: initialize gameObjects list with player
        // TODO: create menu and switch to menu scene
        // TODO: set up AnimationTimer game loop and start it
        // TODO: show primaryStage
    }

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

    public static List<EnemyBullet> getBossBullets() {
        return bossBullets;
    }

    public BossEnemy getBossEnemy() {
        return bossEnemy;
    }

    public Player getPlayer() {
        return player;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }
    

    // Game mechanics stubs

    private void spawnEnemy() {
        // TODO: implement enemy and boss spawn logic based on score
    }

    private void spawnPowerUp() {
        // TODO: implement power-up spawn logic
    }

    private void spawnBossEnemy() {
        // TODO: implement boss-only spawn logic
    }

    private void checkCollisions() {
        // TODO: detect and handle collisions between bullets, enemies, power-ups, player
    }

    private void checkEnemiesReachingBottom() {
        // TODO: handle enemies reaching bottom of screen (reduce lives, respawn, reset game)
    }

    // UI and game state methods

    private void showLosingScreen() {
        // TODO: display Game Over screen with score and buttons
    }

    private void restartGame() {
        // TODO: reset gameObjects, lives, score and switch back to game scene
    }

    private void resetGame() {
        // TODO: stop game loop and call showLosingScreen
    }

    private void initEventHandlers(Scene scene) {
        // TODO: set OnKeyPressed and OnKeyReleased for movement and shooting
    }

    private Pane createMenu() {
        // TODO: build and return main menu pane with styled buttons
        return new Pane();
    }

    private void showInstructions() {
        // TODO: display instructions dialog
    }

    private void showTempMessage(String message, double x, double y, double duration) {
        // TODO: show temporary on-screen message for duration seconds
    }

    private void startGame() {
        // TODO: set gameRunning to true and switch to game scene
    }
}
