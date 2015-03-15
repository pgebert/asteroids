package com.example.connectionmachineexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Patrick on 06.03.2015.
 */
public class LEDMatrix {

    // Remote display x and y size.
    protected static final int X_SIZE = 24;
    protected static final int Y_SIZE = 24;

    private static int[][] ledBoard = new int[X_SIZE][Y_SIZE];

    private static List<LEDMatrixObject> objects = new ArrayList<LEDMatrixObject>();
    private static int frame;

    private static boolean createAsteroids;
    private int asteroidCreationSpeed;
    private int asteroidSpeed;

    public static final int LED_ON = 255;
    public static final int LED_OFF = 0;


    private static LEDMatrix matrix = null;

    protected LEDMatrix() {
        // Exists only to defeat instantiation.
        this.clear();
        LEDMatrix.frame = 0;
        LEDMatrix.createAsteroids = true;
        this.asteroidCreationSpeed = 80;
        this.asteroidSpeed = 10;

    }

    /**
     * Get the matrix - singleton pattern
     * @return the matrix instance
     */
    public static LEDMatrix getMatrix() {
        if (matrix == null) {
            matrix = new LEDMatrix();
        }
        return matrix;
    }

    /**
     * Transform the ledBoard array to an byte array
     * @return byte array
     */
    public byte[] getBuffer() {
        byte[] buffer = new byte[X_SIZE * Y_SIZE];
        for (int i = 0; i < (X_SIZE * Y_SIZE); i++) {
            buffer[i] = (byte) ledBoard[i % 24][i / 24];
        }
        return buffer;
    }

    /**
     * Add an object to the game objects
     * @param object LEDMatrixObject to add
     */
    public static void addObject(LEDMatrixObject object) {
        objects.add(object);
    }

    /**
     * Remove an object from game objects
     * @param object LEDMatrixObject to remove
     */
    public static void removeObject(LEDMatrixObject object) {
        objects.remove(object);
    }

    /**
     * Removes all the stored game objects
     */
    public static void removeAllObjects() {
        objects.clear();
    }

    /**
     * Clear the LED Matrix
     */
    public static void clear() {
        for (int i = 0; i < ledBoard.length; i++) {
            for (int j = 0; j < ledBoard[i].length; j++)
                ledBoard[i][j] = LED_OFF;
        }
    }

    /**
     *Call the run method of every game object to perform action
     */
    public void run() {
        LEDMatrixObject[] objectsCopy = objects.toArray(new LEDMatrixObject[objects.size()]);
        for (LEDMatrixObject object : objectsCopy) {
            object.run();
        }
    }

    /**
     * Return current frame.
     * @return current frame
     */
    public static int getFrame() {
        return frame;
    }

    /**
     * Set the current frame.
     * @param frame current frame
     */
    public static void setFrame(int frame) {
        LEDMatrix.frame = frame;
    }

    /**
     * Method is called directly when frame starts
     */
    public void initFrame() {
        if (this.createAsteroids) {
            createAsteroid(10);
        }
    }

    /**
     * Increases the asteroid creation speed about a given value.
     * @param value the value to increase
     */
    public void increaseAsteroidCreationSpeed(int value) {
        int speedValue = this.asteroidCreationSpeed - value;
        if (speedValue < 0) {
            this.asteroidCreationSpeed = 0;
        } else {
            this.asteroidCreationSpeed = speedValue;
        }
    }

    /**
     * Increases the asteroid moving speed about a given value.
     * @param value the value to increase
     */
    public void increaseAsteroidSpeed(int value) {
        int speedValue = this.asteroidSpeed - value;
        if (speedValue < 0) {
            this.asteroidSpeed = 0;
        } else {
            this.asteroidSpeed = speedValue;
        }
    }

    /**
     * Creates an asteroid at random position
     */
    public void createAsteroid(int asteroidSpeed) {
        if (speed(asteroidCreationSpeed)) {
            int random = randInt(0, 19);
            AsteroidsAsteroid asteroid = new AsteroidsAsteroid(new LEDMatrixPoint(random, 0));
            asteroid.setMoveSpeed(asteroidSpeed);
            LEDMatrix.addObject(asteroid);
            this.increaseAsteroidCreationSpeed(1);
            this.increaseAsteroidSpeed(2);
        }
    }

    /**
     * Controls the speed of an action
     * @param rate the frame rate to redo an action. Higher is slower.
     * @return whether this is the right frame or not
     */
    public static boolean speed(int rate) {
        return (frame % rate) == 0;
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
         return (new Random()).nextInt((max - min) + 1) + min;
    }

    /**
     * Runs collision detection for all the objects in this game - triggers onCollision
     */
    public void collisionDetection() {
        LEDMatrixObject[] objectsCopy = objects.toArray(new LEDMatrixObject[objects.size()]);
        ArrayList<LEDMatrixObject> collisions = new ArrayList<LEDMatrixObject>();
        for (int i = 0; i < (objectsCopy.length - 1); i++) {
            for (int j = i + 1; j < objectsCopy.length; j++) {
                if (objectsCopy[i].collision(objectsCopy[j])) {
                    if (!collisions.contains(objectsCopy[i])) {
                        collisions.add(objectsCopy[i]);
                        objectsCopy[i].onCollision();
                    }
                    if (!collisions.contains(objectsCopy[i])) {
                        collisions.add(objectsCopy[j]);
                        objectsCopy[j].onCollision();
                    }
                }
            }
        }
    }

    /**
     * Render the game objects on the LED Matrix
     */
    public void render() {
        this.clear();
        for (LEDMatrixObject object : objects) {
            for (LEDMatrixPoint point : object.getBody()) {
                ledBoard[point.getX()][point.getY()] = LED_ON;
            }
        }
    }

    public static void setCreateAsteroids(boolean createAsteroids) {
        LEDMatrix.createAsteroids = createAsteroids;
    }
}
