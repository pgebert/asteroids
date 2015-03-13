package com.example.connectionmachineexample;

/**
 * Created by Patrick on 06.03.2015.
 */
public class AsteroidsGame {

    private LEDMatrix matrix;
    private int frame;

    private AsteroidsSpaceShip ship;

    public AsteroidsGame() {
        this.matrix = LEDMatrix.getMatrix();
    }

    /**
     * Initialize the game and add the objects
     */
    public void start() {
        this.ship = new AsteroidsSpaceShip(new LEDMatrixPoint(0, 20));
        matrix.addObject(this.ship);
    }

    /**
     * Draw the matrix
     */
    public void render(int frame) {
        matrix.setFrame(frame);
        matrix.initFrame();
        matrix.run();
        matrix.collisionDetection();
        matrix.render();
    }

    public void rightButton() {
        this.ship.moveRight();
    }

    public void leftButton() {
        this.ship.moveLeft();
    }

    public void shootButton() {
        this.ship.fire();
    }

    public LEDMatrix getMatrix() {
        return matrix;
    }

    public void setMatrix(LEDMatrix matrix) {
        this.matrix = matrix;
    }

}
