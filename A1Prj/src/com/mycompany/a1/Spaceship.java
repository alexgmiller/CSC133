package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class Spaceship extends GameObject implements IGuided {
    private static final float MOVE_DISTANCE = 10; // Adjust as necessary
    private static final Random random = new Random(); 
    private static final float MIN_COORDINATE = 20; 
    private static final float MAX_COORDINATE = 980; 
    private static final int MAX_DOOR_SIZE = 100; 

    private int doorSize; // Size of the spaceship door

    public Spaceship() {
        super(ColorUtil.rgb(0, 255, 0), 40); // Initialize with color and size
        this.doorSize = 20; // Initial door size
        this.setLocation(generateRandomCoordinate(), generateRandomCoordinate());
    }

    private static float generateRandomCoordinate() {
        return MIN_COORDINATE + random.nextFloat() * (MAX_COORDINATE - MIN_COORDINATE);
    }

    @Override
    public void moveLeft() {
        float newX = this.getLocation().getX() - MOVE_DISTANCE;
        if (newX - (getSize() / 2) >= 0) {
            this.setLocation(newX, this.getLocation().getY());
        }
		System.out.println("Spaceship moved left.");
    }

    @Override
    public void moveRight() {
        float newX = this.getLocation().getX() + MOVE_DISTANCE;
        if (newX + (getSize() / 2) <= 1000) {
            this.setLocation(newX, this.getLocation().getY());
        }
		System.out.println("Spaceship moved right.");
    }

    @Override
    public void moveUp() {
        float newY = this.getLocation().getY() + MOVE_DISTANCE;
        if (newY + (getSize() / 2) <= 1000) {
            this.setLocation(this.getLocation().getX(), newY);
        }
		System.out.println("Spaceship moved up.");
    }

    @Override
    public void moveDown() {
        float newY = this.getLocation().getY() - MOVE_DISTANCE;
        if (newY - (getSize() / 2) >= 0) {
            this.setLocation(this.getLocation().getX(), newY);
        }
		System.out.println("Spaceship moved down.");
    }

    public void expandDoor() {
        if (doorSize < MAX_DOOR_SIZE) {
            doorSize += 5; 
            System.out.println("Spaceship door expanded. New door size: " + doorSize);
        } else {
            System.out.println("Spaceship door cannot be expanded further.");
        }
    }

    public void contractDoor() {
        if (doorSize > 5) {
            doorSize -= 5; 
            System.out.println("Spaceship door contracted. New door size: " + doorSize);
        } else {
            System.out.println("Spaceship door cannot be contracted further.");
        }
    }

    public void transferSpaceshipToAlien(Alien alien) {
        System.out.println("Transferring spaceship to alien at location: " + alien.getLocation());
    }

    public int getDoorSize() {
        return doorSize;
    }

    @Override
    public String toString() {
        return "Spaceship [Location: " + getLocation() + ", Color: " + getColor() + ", Door Size: " + doorSize + "]";
    }

    @Override
    public void jumpToLocation(Point p) {
        float newX = Math.max(MIN_COORDINATE, Math.min(p.getX(), MAX_COORDINATE));
        float newY = Math.max(MIN_COORDINATE, Math.min(p.getY(), MAX_COORDINATE));
        this.setLocation(newX, newY);
        System.out.println("Jumped to location: " + this.getLocation());
    }
}
