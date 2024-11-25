package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class Spaceship extends GameObject implements IGuided {
    private int doorSize;
    private static Random random = new Random(); // Create a Random instance

    // Create a static instance variable to hold the single instance
    private static Spaceship instance = null;

    // Make the constructor private to prevent direct instantiation
    private Spaceship() {
        super(100, generateRandomLocation(), ColorUtil.rgb(0, 255, 0)); // Green spaceship
        this.doorSize = 100; // Initial door size is 100
    }

    // Public method to return the single instance of Spaceship
    public static Spaceship getInstance() {
        if (instance == null) {
            instance = new Spaceship();  // Create the instance if it doesn't exist
        }
        return instance;
    }

    // Randomly generate a location for the spaceship
    private static Point generateRandomLocation() {
        float x = random.nextFloat() * 1000; // X in range [0, 1000]
        float y = random.nextFloat() * 1000; // Y in range [0, 1000]
        return new Point(x, y);
    }

    // Get the size of the spaceship's door
    public int getDoorSize() {
        return doorSize;
    }

    // Expand the door size by 10, up to a maximum of 1000
    public void expandDoor() {
        if (doorSize < 1000) {
            doorSize += 10;
            System.out.println("Spaceship door expanded. New door size: " + doorSize);
        }
    }

    // Contract the door size by 10, down to a minimum of 50
    public void contractDoor() {
        if (doorSize > 50) {
            doorSize -= 10;
            System.out.println("Spaceship door contracted. New door size: " + doorSize);
        }
    }

    public void moveLeft() {
        if (getLocation().getX() - 10 >= 0) {
            setLocation(getLocation().getX() - 10, getLocation().getY());
            System.out.println("Spaceship moved left to location: (" + getLocation().getX() + ", " + getLocation().getY() + ")");
        } else {
            System.out.println("Cannot move left. Spaceship is at the left boundary.");
        }
    }

    public void moveRight() {
        if (getLocation().getX() + 10 <= 1000) {
            setLocation(getLocation().getX() + 10, getLocation().getY());
            System.out.println("Spaceship moved right to location: (" + getLocation().getX() + ", " + getLocation().getY() + ")");
        } else {
            System.out.println("Cannot move right. Spaceship is at the right boundary.");
        }
    }

    public void moveUp() {
        if (getLocation().getY() + 10 <= 1000) {
            setLocation(getLocation().getX(), getLocation().getY() + 10);
            System.out.println("Spaceship moved up to location: (" + getLocation().getX() + ", " + getLocation().getY() + ")");
        } else {
            System.out.println("Cannot move up. Spaceship is at the upper boundary.");
        }
    }

    public void moveDown() {
        if (getLocation().getY() - 10 >= 0) {
            setLocation(getLocation().getX(), getLocation().getY() - 10);
            System.out.println("Spaceship moved down to location: (" + getLocation().getX() + ", " + getLocation().getY() + ")");
        } else {
            System.out.println("Cannot move down. Spaceship is at the lower boundary.");
        }
    }

    // Jump to a specific location (override from IGuided interface)
    @Override
    public void jumpToLocation(float x, float y) {
        setLocation(x, y);  // Directly set the spaceship's location to the specified coordinates
        System.out.println("Spaceship jumped to location: (" + x + ", " + y + ")");
    }

    @Override
    public String toString() {
        return "Spaceship: loc=" + getLocation().getX() + "," + getLocation().getY() +
               " color=" + getColor() +
               " size=" + doorSize;
    }
}
