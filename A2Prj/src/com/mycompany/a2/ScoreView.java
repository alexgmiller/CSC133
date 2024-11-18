package com.mycompany.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

import java.util.Observable;
import java.util.Observer;

public class ScoreView extends Container implements Observer {
    private Label soundStatusLabel;
    private Label scoreLabel;
    private Label clockLabel;
    private Label astronautsRescuedLabel;
    private Label aliensSneakedInLabel;
    private Label astronautsRemainingLabel;  // New label for astronauts remaining
    private Label aliensRemainingLabel;  // New label for aliens remaining

    public ScoreView() {
        // Set the layout
        this.setLayout(new FlowLayout());

        // Create the labels
        scoreLabel = new Label("Score: 0");
        clockLabel = new Label("Time: 0");
        astronautsRescuedLabel = new Label("Astronauts Rescued: 0");
        aliensSneakedInLabel = new Label("Aliens Sneaked In: 0");
        soundStatusLabel = new Label("Sound: ON");  // Initialize sound label with default ON
        astronautsRemainingLabel = new Label("Astronauts Remaining: 0");  // New label
        aliensRemainingLabel = new Label("Aliens Remaining: 0");  // New label

        // Add the labels to the container
        this.add(scoreLabel);
        this.add(clockLabel);
        this.add(astronautsRescuedLabel);
        this.add(aliensSneakedInLabel);
        this.add(astronautsRemainingLabel);  // Add astronauts remaining label to the ScoreView
        this.add(aliensRemainingLabel);  // Add aliens remaining label to the ScoreView
        this.add(soundStatusLabel);  // Add sound status label to the ScoreView
    }

    @Override
    public void update(Observable observable, Object data) {
        GameWorld gw = (GameWorld) observable;

        // Update the labels based on the game state
        scoreLabel.setText("Score: " + gw.getTotalScore());
        clockLabel.setText("Time: " + gw.getGameClock());
        astronautsRescuedLabel.setText("Astronauts Rescued: " + gw.getAstronautsRescued());
        aliensSneakedInLabel.setText("Aliens Sneaked In: " + gw.getAliensSneakedIn());
        astronautsRemainingLabel.setText("Astronauts Remaining: " + gw.getAstronautCount());  // Update astronauts remaining
        aliensRemainingLabel.setText("Aliens Remaining: " + gw.getAlienCount());  // Update aliens remaining
        soundStatusLabel.setText("Sound: " + (gw.isSoundOn() ? "ON" : "OFF"));  // Update sound status
    }
}
