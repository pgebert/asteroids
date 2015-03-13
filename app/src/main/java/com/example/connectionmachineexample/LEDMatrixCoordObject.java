package com.example.connectionmachineexample;

/**
 * Created by Patrick on 09.03.2015.
 */
public abstract class LEDMatrixCoordObject {

    protected int y;
    protected int x;

    public LEDMatrixCoordObject() {
        this.x = 0;
        this.y = 0;
    }

    public LEDMatrixCoordObject(int x, int y) {
        this.y = y;
        this.x = x;
    }

    /**
     * Add another coordObject to this one
     *
     * @param coord another coordObject to add
     */
    public void add(LEDMatrixCoordObject coord) {
        this.x = ((((coord.getX() + this.x) % LEDMatrix.X_SIZE) + LEDMatrix.X_SIZE) % LEDMatrix.X_SIZE);
        this.y = ((((coord.getY() + this.y) % LEDMatrix.Y_SIZE) + LEDMatrix.Y_SIZE) % LEDMatrix.Y_SIZE);
    }

    public abstract LEDMatrixCoordObject clone();

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
