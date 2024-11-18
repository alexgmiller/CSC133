package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class Alien extends GameObject implements IMoving {
    private final int speed; // Speed is constant for aliens
    private int direction; // Compass direction in degrees
    private static Random random = new Random(); // Create a Random instance

    // Constructor
    public Alien() {
        super(chooseRandomSize(), generateRandomLocation(), ColorUtil.rgb(0, 0, 255)); // Blue aliens
        this.speed = 5; // Alien speed is constant at 5
        this.direction = random.nextInt(360); // Random direction between 0 and 359
    }

    // Randomly generate a size for the alien between 20 and 50
    private static int chooseRandomSize() {
        return random.nextInt(31) + 20; // Random size between 20 and 50
    }

    // Randomly generate a location for the alien
    private static Point generateRandomLocation() {
        float x = random.nextFloat() * 1000; // X in range [0, 1000]
        float y = random.nextFloat() * 1000; // Y in range [0, 1000]
        return new Point(x, y);
    }

    // Implement the move method from IMoving interface with boundary checking
    @Override
    public void move() {
        float deltaX = (float) (Math.cos(Math.toRadians(90 - direction)) * speed);
        float deltaY = (float) (Math.sin(Math.toRadians(90 - direction)) * speed);
        float newX = getLocation().getX() + deltaX;
        float newY = getLocation().getY() + deltaY;

        // Boundary checking
        boolean hitBoundary = false;
        if (newX < 0) {
            newX = 0;
            hitBoundary = true;
        } else if (newX > 1000) {
            newX = 1000;
            hitBoundary = true;
        }
        if (newY < 0) {
            newY = 0;
            hitBoundary = true;
        } else if (newY > 1000) {
            newY = 1000;
            hitBoundary = true;
        }

        // Update location after applying boundaries
        setLocation(newX, newY);

        // If the alien hits a boundary, adjust its direction
        if (hitBoundary) {
            direction = (direction + random.nextInt(90) + 270) % 360; // Adjust direction to "bounce"
            System.out.println("Alien hit the boundary and changed direction to: " + direction);
        }
    }

    @Override
    public String toString() {
        return "Alien: loc=" + getLocation().getX() + "," + getLocation().getY() +
               " color=" + getColor() +
               " size=" + getSize() +
               " speed=" + speed +
               " dir=" + direction;
    }
}
