package com.mycompany.a1;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class Alien extends Opponents {
    private static final Random random = new Random();

    public Alien() {
        super(ColorUtil.rgb(0, 0, 255));  // Alien color is blue
        this.setLocation(generateRandomCoordinate(), generateRandomCoordinate());
    }

    private float generateRandomCoordinate() {
        return random.nextFloat() * 1000; // Generate a coordinate within game bounds
    }

    @Override
    public void move() {
        System.out.println("Alien moves at speed " + getSpeed() + " in direction " + getDirection());

        float deltaX = (float) Math.cos(Math.toRadians(getDirection())) * getSpeed();
        float deltaY = (float) Math.sin(Math.toRadians(getDirection())) * getSpeed();

        float newX = getLocation().getX() + deltaX;
        float newY = getLocation().getY() + deltaY;

        newX = Math.max(0, Math.min(newX, 1000));
        newY = Math.max(0, Math.min(newY, 1000));

        setLocation(newX, newY);
    }
}
