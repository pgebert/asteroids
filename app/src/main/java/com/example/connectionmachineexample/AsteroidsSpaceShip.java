package com.example.connectionmachineexample;

import java.util.ArrayList;

/**
 * Created by Patrick on 06.03.2015.
 */
public class AsteroidsSpaceShip extends LEDMatrixObject {

    public AsteroidsSpaceShip(LEDMatrixPoint position) {
        super(new LEDMatrixPoint());
        this.body.add(new LEDMatrixPoint(1, 3));
        this.body.add(new LEDMatrixPoint(3, 3));
        this.body.add(new LEDMatrixPoint(0, 2));
        this.body.add(new LEDMatrixPoint(1, 2));
        this.body.add(new LEDMatrixPoint(2, 2));
        this.body.add(new LEDMatrixPoint(3, 2));
        this.body.add(new LEDMatrixPoint(4, 2));
        this.body.add(new LEDMatrixPoint(1, 1));
        this.body.add(new LEDMatrixPoint(2, 1));
        this.body.add(new LEDMatrixPoint(3, 1));
        this.body.add(new LEDMatrixPoint(2, 0));

        this.orientation = new LEDMatrixVector(0, -1);
        // Position the space ship
        this.moveToPosition(position);
        this.position = position;
    }

    private AsteroidsSpaceShip(LEDMatrixPoint position, ArrayList<LEDMatrixPoint> body) {
        super(position, body);
    }

    public void moveRight() {
        this.move(new LEDMatrixVector(1, 0));
    }

    public void moveLeft() {
        this.move(new LEDMatrixVector(-1, 0));
    }

    public void fire() {
        LEDMatrix.addObject(new AsteroidsBullet(this.getShipHead()));
    }

    /**
     * Calculates the position of the head of the space ship
     *
     * @return the point before the space ship head
     */
    public LEDMatrixPoint getShipHead() {
        LEDMatrixPoint head = this.position.clone();
        head.add(new LEDMatrixVector(2, -1));
        return head;
    }

    @Override
    public void onCollision() {
        AsteroidsGame.addPoints( -100);
        AsteroidsGame.onEnd();
    }

    @Override
    public void run() {

    }
}
