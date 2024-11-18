package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class Astronaut extends GameObject implements IMoving {
    private int health;
    private int speed;
    private int direction; // Compass direction in degrees
    private int fightsInvolved; // Track the number of fights
    private static final int HEALTH_MAX = 5; // Maximum health
    private static final int SPEED_CONSTANT = 1; // Speed multiplier
    private static final int WORLD_SIZE = 1000; // Game world size is 1000x1000

    public Astronaut() {
        super(generateRandomSize(), ColorUtil.rgb(255, 0, 0)); // Default color is red
        this.health = HEALTH_MAX;
        this.speed = calculateSpeed();
        this.direction = new Random().nextInt(360); // Random initial direction
        this.fightsInvolved = 0; // Initially, no fights have occurred
    }

    private static int generateRandomSize() {
        Random rand = new Random();
        return 20 + rand.nextInt(31); // Size between 20 and 50
    }

    public int calculateSpeed() {
        return health * SPEED_CONSTANT;
    }

    public int getHealth() {
        return health;
    }

    public void decrementHealth() {
        if (health > 0) {
            health--;
            speed = calculateSpeed();
            if (health < HEALTH_MAX) {
                setColor(ColorUtil.rgb(255, 200 - (50 * health), 200)); // Change color as health degrades
            }
            if (health == 0) {
                System.out.println("Astronaut has lost all health!");
            }
        }
    }

    public void fight() {
        if (fightsInvolved < 5) {
            fightsInvolved++;
            decrementHealth();
            if (fightsInvolved >= 5) {
                stopMoving(); // Astronaut stops moving after 5 fights
            }
        }
    }

    public int getFightsInvolved() {
        return fightsInvolved;
    }

    public void stopMoving() {
        this.speed = 0;
        System.out.println("Astronaut has been in 5 fights and can no longer move.");
    }

    @Override
    public void move() {
        if (fightsInvolved < 5) {
            double deltaX = Math.cos(Math.toRadians(direction)) * speed;
            double deltaY = Math.sin(Math.toRadians(direction)) * speed;
            Point currentLocation = getLocation();
            float newX = (float) (currentLocation.getX() + deltaX);
            float newY = (float) (currentLocation.getY() + deltaY);
            newX = Math.max(0, Math.min(newX, WORLD_SIZE)); // Keep within world bounds
            newY = Math.max(0, Math.min(newY, WORLD_SIZE)); // Keep within world bounds
            setLocation(newX, newY);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }

    public void changeDirection() {
        this.direction = new Random().nextInt(360);
    }
    
    public String toString() {
        return "Astronaut [health=" + health + ", speed=" + speed + ", direction=" + direction +
               ", location=" + getLocation() + "]";
    }
}
