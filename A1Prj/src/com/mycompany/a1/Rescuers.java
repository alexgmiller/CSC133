package com.mycompany.a1;

import com.codename1.charts.models.Point;
import java.util.Random;

public abstract class Rescuers extends GameObject {

    // Constructor
    public Rescuers(int size, int color) {
        super(color, size); // Call the GameObject constructor with size and color

        // Set random initial location within the game world
        Random rnd = new Random();
        float x = rnd.nextFloat() * 1000; // Random x-coordinate
        float y = rnd.nextFloat() * 1000; // Random y-coordinate
        setLocation(x, y); // Set the rescuer's location
    }

    public void move(float deltaX, float deltaY) {
        Point currentLocation = getLocation();
        float newX = currentLocation.getX() + deltaX;
        float newY = currentLocation.getY() + deltaY;
        setLocation(newX, newY);
    }
}
