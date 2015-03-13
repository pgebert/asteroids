package com.example.connectionmachineexample;

import java.util.ArrayList;

/**
 * Created by Patrick on 06.03.2015.
 */
public abstract class LEDMatrixObject {

    protected LEDMatrixPoint position;
    protected ArrayList<LEDMatrixPoint> body;
    protected LEDMatrixVector orientation;
    protected int speed;
    protected int acceleration;
    protected int zIndex;

    protected LEDMatrixObject() {
        this.position = new LEDMatrixPoint();
        this.body = new ArrayList<LEDMatrixPoint>();
        this.orientation = new LEDMatrixVector();
        this.speed = 0;
        this.acceleration = 0;
        this.zIndex = 0;
    }

    public LEDMatrixObject(LEDMatrixPoint position) {
        this.position = position;
        this.body = new ArrayList<LEDMatrixPoint>();
        this.orientation = new LEDMatrixVector();
        this.speed = 0;
        this.acceleration = 0;
        this.zIndex = 0;
    }

    public LEDMatrixObject(LEDMatrixPoint position, ArrayList<LEDMatrixPoint> body) {
        this.position = position;
        this.body = body;
        this.orientation = new LEDMatrixVector();
        this.speed = 0;
        this.acceleration = 0;
        this.zIndex = 0;
    }

    public abstract void onCollision();
    public abstract void run();

    /**
     * Move all the body points to the position
     * @param position the given position
     */
    public void moveToPosition(LEDMatrixPoint position) {
        for (LEDMatrixPoint point : this.body) {
            point.add(new LEDMatrixVector(position.getX() - this.position.getX(), position.getY() - this.position.getY()));
        }
    }

    /**
     * Move this object with a given vector
     * @param vector the vector to move
     */
    public void move(LEDMatrixVector vector) {
        this.position.add(vector);
        for (LEDMatrixPoint point : this.body) {
            point.add(vector);
        }
    }

    /**
     * Returns whether this objects collides with a given object
     * @param object the given object
     * @return whether the two objects collide
     */
    public boolean collision(LEDMatrixObject object) {
        for (LEDMatrixPoint objectPoint : object.getBody()) {
            for (LEDMatrixPoint ownPoint : this.body) {
                if (objectPoint.equals(ownPoint)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns whether a move to with this vector would result in a position in the matrix
     *
     * @param start the point to start
     * @param vector the vector to move
     * @return whether the position is in the matrix borders
     */
    public boolean targetInBorder(LEDMatrixPoint start, LEDMatrixVector vector) {
        LEDMatrixPoint target = new LEDMatrixPoint(start.getX() + vector.getX(), start.getY() + vector.getY());
        return target.valid();
    }

    public LEDMatrixPoint getPosition() {
        return position;
    }

    public void setPosition(LEDMatrixPoint position) {
        this.position = position;
    }

    public ArrayList<LEDMatrixPoint> getBody() {
        return body;
    }

    public void setBody(ArrayList<LEDMatrixPoint> body) {
        this.body = body;
    }

    public LEDMatrixVector getOrientation() {
        return orientation;
    }

    public void setOrientation(LEDMatrixVector orientation) {
        this.orientation = orientation;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }
}
