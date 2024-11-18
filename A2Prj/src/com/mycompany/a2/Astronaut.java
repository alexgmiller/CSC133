package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class Astronaut extends GameObject {
    private int health;
    private int speed;
    private int fightsInvolved;
    private static Random random = new Random(); // Create a Random instance

    // Constructor
    public Astronaut() {
        super(chooseRandomSize(), generateRandomLocation(), ColorUtil.rgb(255, 0, 0)); // Red astronauts
        this.health = 5;
        this.speed = health;
        this.fightsInvolved = 0;
    }

    // Randomly generate a size for the astronaut between 20 and 50
    private static int chooseRandomSize() {
        return random.nextInt(31) + 20; // Random size between 20 and 50
    }

    // Randomly generate a location for the astronaut
    private static Point generateRandomLocation() {
        float x = random.nextFloat() * 1000; // X in range [0, 1000]
        float y = random.nextFloat() * 1000; // Y in range [0, 1000]
        return new Point(x, y);
    }

    // Decrement health and increase fight count
    public void decrementHealth() {
        if (health > 0) {
            health--;
            fightsInvolved++;

            // Reduce the red component as health decreases
            int currentColor = getColor();
            int newRed = Math.max(0, ColorUtil.red(currentColor) - 50); // Gradually reduce red
            setColor(ColorUtil.rgb(newRed, ColorUtil.green(currentColor), ColorUtil.blue(currentColor)));
        }
    }

    // Track the astronaut's involvement in a fight
    public void fight() {
        fightsInvolved++;
        System.out.println("Astronaut has been in " + fightsInvolved + " fights.");
    }

    // Calculate the astronaut's speed based on health
    public void calculateSpeed() {
        this.speed = health; // Speed is directly proportional to health
    }

    // Get the number of fights the astronaut has been involved in
    public int getFightsInvolved() {
        return fightsInvolved;
    }

    // Getter for health
    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "Astronaut: loc=" + getLocation().getX() + "," + getLocation().getY() +
               " color=" + getColor() +
               " size=" + getSize() +
               " speed=" + speed +
               " health=" + health +
               " fightsInvolved=" + fightsInvolved;
    }
}
