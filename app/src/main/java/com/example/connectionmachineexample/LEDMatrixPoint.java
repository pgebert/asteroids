package com.example.connectionmachineexample;

/**
 * Created by Patrick on 05.03.2015.
 */
public class LEDMatrixPoint extends LEDMatrixCoordObject {

    public LEDMatrixPoint() {
        super();
    }

    public LEDMatrixPoint(int x, int y) {
        super(x, y);
    }

    /**
     * Clone this point
     *
     * @return a new point with same attributes
     */
    public LEDMatrixPoint clone() {
        return new LEDMatrixPoint(this.getX(), this.getY());
    }

    /**
     * Test whether this point is inside the matrix borders
     * @return whether the point is matrix borders
     */
    public boolean valid() {
        return (this.x >= 0 && this.x < LEDMatrix.X_SIZE && this.y >= 0 && this.y < LEDMatrix.Y_SIZE);
    }

    public int getTotalPosition() {
        return this.y * LEDMatrix.X_SIZE + this.x;
    }

    public boolean equals(LEDMatrixPoint point) {
        return ((this.x == point.getX()) && (this.y == point.getY()));
    }

}
