package com.example.connectionmachineexample;

/**
 * Created by Patrick on 06.03.2015.
 */
public class AsteroidsBullet extends LEDMatrixObject {

    public AsteroidsBullet(LEDMatrixPoint position) {
        super(position);
        this.body.add(new LEDMatrixPoint(position.getX(), position.getY()));
    }

    /**
     * Move the bullet until the matrix border     *
     *
     * @param vector the vector
     */
    @Override
    public void move(LEDMatrixVector vector) {
        if (targetInBorder(this.position, vector)) {
            this.position.add(vector);
            for (LEDMatrixPoint point : this.body) {
                point.add(vector);
            }
        } else {
            LEDMatrix.removeObject(this);
        }
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void run() {
        move(new LEDMatrixVector(0, -1));
    }
}
