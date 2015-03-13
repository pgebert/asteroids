package com.example.connectionmachineexample;

/**
 * Created by Patrick on 06.03.2015.
 */
public class LEDMatrixVector extends LEDMatrixCoordObject {

    public LEDMatrixVector() {
        super(1, 0);
    }

    public LEDMatrixVector(int x, int y) {
        super(x, y);
    }

    public LEDMatrixVector getNormalizedVector() {
        int x = 0;
        int y = 0;

        if (this.x > 0) x = 1;
        else if (this.x < 0) x = -1;

        if (this.y > 0) y = 1;
        else if (this.y < 0) y = -1;

        return new LEDMatrixVector(x, y);
    }

    /**
     * Clone this vector
     * @return a new vector with same attributes
     */
    public LEDMatrixVector clone() {
        return new LEDMatrixVector(this.getX(), this.getY());
    }

}
