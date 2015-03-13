package com.example.connectionmachineexample;

import java.util.ArrayList;

/**
 * Created by Patrick on 13.03.2015.
 */
public class AsteroidsGameFont extends LEDMatrixObject {

    public AsteroidsGameFont() {
    }

    public AsteroidsGameFont(LEDMatrixPoint position, LEDMatrixPoint[] fontPoints) {
        super(new LEDMatrixPoint());
        for (LEDMatrixPoint point : fontPoints) {
            this.body.add(point);
        }

        this.orientation = new LEDMatrixVector(0, 1);
        this.moveToPosition(position);
        this.position = position;
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void run() {
        if (LEDMatrix.speed(40)) {
            move(new LEDMatrixVector(0, 1));
        }
    }

    /**
     * Move the font until the matrix border     *
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
                    this.body.remove(point);
                }
            }
        } else {
            LEDMatrix.removeObject(this);
        }
    }

    public static LEDMatrixPoint[] getEndFontPoints() {
        LEDMatrixPoint points[] = {
                //G
                new LEDMatrixPoint(0, 0),
                new LEDMatrixPoint(0, 1),
                new LEDMatrixPoint(0, 2),
                new LEDMatrixPoint(0, 3),
                new LEDMatrixPoint(1, 0),
                new LEDMatrixPoint(1, 2),
                new LEDMatrixPoint(1, 3),
                new LEDMatrixPoint(2, 0),
                new LEDMatrixPoint(2, 2),
                new LEDMatrixPoint(2, 3),
                //A
                new LEDMatrixPoint(4, 0),
                new LEDMatrixPoint(4, 1),
                new LEDMatrixPoint(4, 2),
                new LEDMatrixPoint(4, 3),
                new LEDMatrixPoint(5, 0),
                new LEDMatrixPoint(5, 2),
                new LEDMatrixPoint(6, 0),
                new LEDMatrixPoint(6, 1),
                new LEDMatrixPoint(6, 2),
                new LEDMatrixPoint(6, 3),
                //M
                new LEDMatrixPoint(8, 0),
                new LEDMatrixPoint(8, 1),
                new LEDMatrixPoint(8, 2),
                new LEDMatrixPoint(8, 3),
                new LEDMatrixPoint(9, 1),
                new LEDMatrixPoint(10, 0),
                new LEDMatrixPoint(10, 1),
                new LEDMatrixPoint(10, 2),
                new LEDMatrixPoint(10, 3),
                //E
                new LEDMatrixPoint(12, 0),
                new LEDMatrixPoint(12, 1),
                new LEDMatrixPoint(12, 2),
                new LEDMatrixPoint(12, 3),
                new LEDMatrixPoint(13, 0),
                new LEDMatrixPoint(13, 2),
                new LEDMatrixPoint(13, 3),
                new LEDMatrixPoint(14, 0),
                new LEDMatrixPoint(14, 2),
                new LEDMatrixPoint(14, 3),
                //O
                new LEDMatrixPoint(8, 6),
                new LEDMatrixPoint(8, 7),
                new LEDMatrixPoint(8, 8),
                new LEDMatrixPoint(8, 9),
                new LEDMatrixPoint(9, 6),
                new LEDMatrixPoint(9, 9),
                new LEDMatrixPoint(10, 6),
                new LEDMatrixPoint(10, 7),
                new LEDMatrixPoint(10, 8),
                new LEDMatrixPoint(10, 9),
                //V
                new LEDMatrixPoint(12, 6),
                new LEDMatrixPoint(12, 7),
                new LEDMatrixPoint(12, 8),
                new LEDMatrixPoint(12, 9),
                new LEDMatrixPoint(13, 9),
                new LEDMatrixPoint(14, 6),
                new LEDMatrixPoint(14, 7),
                new LEDMatrixPoint(14, 8),
                new LEDMatrixPoint(14, 9),
                //E
                new LEDMatrixPoint(16, 6),
                new LEDMatrixPoint(16, 7),
                new LEDMatrixPoint(16, 8),
                new LEDMatrixPoint(16, 9),
                new LEDMatrixPoint(17, 6),
                new LEDMatrixPoint(17, 8),
                new LEDMatrixPoint(17, 9),
                new LEDMatrixPoint(18, 6),
                new LEDMatrixPoint(18, 8),
                new LEDMatrixPoint(18, 9),
                //R
                new LEDMatrixPoint(20, 6),
                new LEDMatrixPoint(20, 7),
                new LEDMatrixPoint(20, 8),
                new LEDMatrixPoint(20, 9),
                new LEDMatrixPoint(21, 6),
                new LEDMatrixPoint(21, 8),
                new LEDMatrixPoint(22, 6),
                new LEDMatrixPoint(22, 7),
                new LEDMatrixPoint(22, 8),
                new LEDMatrixPoint(22, 9)
        };
        return points;
    }
}
