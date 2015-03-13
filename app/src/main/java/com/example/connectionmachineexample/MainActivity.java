package com.example.connectionmachineexample;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.os.Handler;
import android.os.Looper;

import com.gc.materialdesign.views.ButtonFloat;

public class MainActivity extends Activity {

    private LEDMatrixBTConn BT;
    protected static final String REMOTE_BT_DEVICE_NAME = "ledpi-teco";

    // Remote display x and y size.
    protected static final int X_SIZE = 24;
    protected static final int Y_SIZE = 24;

    // Remote display color mode. 0 = red, 1 = green, 2 = blue, 3 = RGB.
    protected static final int COLOR_MODE = 0;

    // The name this app uses to identify with the server.
    protected static final String APP_NAME = "Asteroids";

    private ButtonFloat mStartButton;
    private ButtonFloat mRightButton;
    private ButtonFloat mLeftButton;
    private ButtonFloat mShootButton;
    private static TextView mPointTextView;
    private int sendDelay;

    // UI Handler for writer thread
    public static Handler UIHandler;

    //The Asteroids Game
    private AsteroidsGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartButton = (ButtonFloat) findViewById(R.id.startButton);
        mRightButton = (ButtonFloat) findViewById(R.id.rightButton);
        mLeftButton = (ButtonFloat) findViewById(R.id.leftButton);
        mShootButton = (ButtonFloat) findViewById(R.id.shootButton);
        mPointTextView = (TextView) findViewById(R.id.pointsTextView);

        mRightButton.setEnabled(false);
        mLeftButton.setEnabled(false);
        mShootButton.setEnabled(false);
    }


    public void start(View v) {
        mStartButton.setEnabled(false);
        mRightButton.setEnabled(true);
        mLeftButton.setEnabled(true);
        mShootButton.setEnabled(true);

        // Set up BT connection.
        // Set up BT connection.
        BT = new LEDMatrixBTConn(this, REMOTE_BT_DEVICE_NAME, X_SIZE, Y_SIZE, COLOR_MODE, APP_NAME);

        if (!BT.prepare() || !BT.checkIfDeviceIsPaired()) {
            mStartButton.setEnabled(true);
            return;
        }

        // Start BT sending thread.
        Thread sender = new Thread() {

            boolean loop = true;

            public void run() {

                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

                // Try to connect.
                if (!BT.connect()) {
                    loop = false;
                }

                // Connected. Calculate and set send delay from maximum FPS.
                // Negative maxFPS should not happen.
                int maxFPS = BT.getMaxFPS();
                if (maxFPS > 0) {
                    sendDelay = (int) (1000.0 / maxFPS);
                } else {
                    loop = false;
                }

                // Prepare variables for making the pattern.
                int frame = 0;

                game = new AsteroidsGame();
                game.onStart();

                // Main sending loop.
                while (loop) {

                    frame++;
                    game.render(frame);

                    // If write fails, the connection was probably closed by the server.
                    if (!BT.write(game.getMatrix().getBuffer())) {
                        loop = false;
                    }

                    try {
                        // Delay for a moment.
                        // Note: Delaying the same amount of time every frame will not give you constant FPS.
                        Thread.sleep(sendDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Connection terminated or lost.
                BT.closeConnection();
            }
        };

        // Start sending thread.
        sender.start();
    }

    public void onRightButton(View v) {
        game.rightButton();
    }

    public void onLeftButton(View v) {
        game.leftButton();
    }

    public void onShootButton(View v) {
        game.shootButton();
    }

    static {
        UIHandler = new Handler(Looper.getMainLooper());
    }

    public static void runOnUI(Runnable runnable) {
        UIHandler.post(runnable);
    }

    /**
     * Write string into the point text view
     * @param points the points string
     */
    public static void writePoints(String points) {
        if (mPointTextView != null) {
            class OneWriteTask implements Runnable {
                String points;

                OneWriteTask(String points) {
                    this.points = points;
                }

                @Override
                public void run() {
                    try {
                        MainActivity.runOnUI(new Runnable() {
                            public void run() {
                                mPointTextView.setText(points);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
            }
            Thread writer = new Thread(new OneWriteTask(points));
            writer.start();
        }
    }


    @Override
    public void onPause() {
        super.onPause();

        mStartButton.setEnabled(true);

        // Avoid crash if user exits the app before pressing start.
        if (BT != null) {
            BT.onPause();
        }
    }
}
