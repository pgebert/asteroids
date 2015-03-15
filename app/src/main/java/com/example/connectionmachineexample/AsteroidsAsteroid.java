package com.example.connectionmachineexample;

import java.util.ArrayList;

/**
 * Created by Patrick on 09.03.2015.
 */
public class AsteroidsAsteroid extends LEDMatrixObject {

    private int moveSpeed;

    public AsteroidsAsteroid(LEDMatrixPoint position) {
        super(new LEDMatrixPoint());
        this.body.add(new LEDMatrixPoint(1, 0));
        this.body.add(new LEDMatrixPoint(0, 1));
        this.body.add(new LEDMatrixPoint(2, 1));
        this.body.add(new LEDMatrixPoint(1, 2));

        this.orientation = new LEDMatrixVector(0, 1);
        this.moveToPosition(position);
        this.position = position;
        this.moveSpeed = 10;
    }

    /**
     * Move the asteroid until the matrix border     *
     *
     * @param vector the vector
     */
    @Override
    public void move(LEDMatrixVector vector) {
        if (targetInBorder(this.position, vector)) {
            this.position.add(vector);
            ArrayList<LEDMatrixPoint> bodyCopy = new ArrayList<LEDMatrixPoint>(this.body);
            for (LEDMatrixPoint point : bodyCopy) {
                if (targetInBorder(point, vector)) {
                    point.add(vector);
                } else {
                    AsteroidsGame.onEnd();
                    this.body.remove(point);
                }
            }
        } else {
            LEDMatrix.removeObject(this);
        }
    }

    @Override
    public void onCollision() {
        destroy();
        AsteroidsGame.addPoints(100);
    }

    public void destroy() {
        this.body.clear();
    }

    @Override
    public void run() {
        if (LEDMatrix.speed(moveSpeed)) {
            move(new LEDMatrixVector(0, 1));
        }
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
