package com.example.connectionmachineexample;


/**
 * Created by Patrick on 06.03.2015.
 */
public class AsteroidsGame {

    private LEDMatrix matrix;

    private static AsteroidsSpaceShip ship;
    private static int points;

    public AsteroidsGame() {
        this.matrix = LEDMatrix.getMatrix();
    }

    /**
     * Initialize the game and add the objects
     */
    public static void onStart() {
        //Reinit matrix
        LEDMatrix.clear();
        LEDMatrix.setFrame(0);
        LEDMatrix.setCreateAsteroids(true);

        AsteroidsGame.setPoints(0);
        ship = new AsteroidsSpaceShip(new LEDMatrixPoint(0, 20));
        LEDMatrix.addObject(ship);
    }

    public static void onEnd()  {
        LEDMatrix.setCreateAsteroids(false);
        LEDMatrix.removeAllObjects();
        AsteroidsGameFont endHeading = new AsteroidsGameFont(new LEDMatrixPoint(), AsteroidsGameFont.getEndFontPoints());
        LEDMatrix.addObject(endHeading);
    }

    public static void setPoints(int points) {
        AsteroidsGame.points = points;
    }

    public static void addPoints(int points) {
        AsteroidsGame.points += points;
        MainActivity.writePoints(AsteroidsGame.points + "");
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
