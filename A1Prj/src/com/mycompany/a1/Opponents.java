package com.mycompany.a1;

import java.util.Random;

public abstract class Opponents extends GameObject implements IMoving {
    private int speed;        // Speed of the opponent
    private int direction;    // Direction of movement

    // Constructor
    public Opponents(int color) {
        super(color, new Random().nextInt(31) + 20); // Size between 20-50
        this.speed = 5; 
        this.direction = new Random().nextInt(360); // Direction between 0-359 degrees
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public abstract void move();  // Abstract method to be implemented by concrete subclasses
}
