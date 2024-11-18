package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class GameWorld {
    private int totalScore;
    private int astronautsRescued;
    private int aliensSneakedIn;
    private int astronautCount;
    private int alienCount;
    private int gameClock;
    private boolean gameEnded;
    private ArrayList<GameObject> gameObjects;
    private Spaceship spaceship;
    private final int gameWorldWidth = 1000;
    private final int gameWorldHeight = 1000;
    private Random random = new Random();

    // Default constructor with no arguments
    public GameWorld() {}

    // Initialize the game world
    public void init() {
        this.totalScore = 0;
        this.astronautsRescued = 0;
        this.aliensSneakedIn = 0;
        this.astronautCount = random.nextInt(5) + 5; // Random count between 5 and 9
        this.alienCount = random.nextInt(5) + 5;     // Random count between 5 and 9
        this.gameClock = 0;
        this.gameEnded = false;
        this.gameObjects = new ArrayList<>();

        addGameObjects();
    }

    // Adding GameObjects (Astronauts, Aliens, and Spaceship) to the GameWorld
    private void addGameObjects() {
        for (int i = 0; i < astronautCount; i++) {
            gameObjects.add(new Astronaut());
        }

        for (int i = 0; i < alienCount; i++) {
            gameObjects.add(new Alien());
        }

        this.spaceship = new Spaceship();
        gameObjects.add(spaceship);
    }

    // Move spaceship to the location of a random alien
    public void moveSpaceshipToAlien() {
        transferSpaceshipToAlien();
    }

    // Move spaceship to the location of a random astronaut
    public void moveSpaceshipToAstronaut() {
        transferSpaceshipToAstronaut();
    }

    public void moveRight() {
        spaceship.moveRight();
    }

    public void moveLeft() {
        spaceship.moveLeft();
    }

    public void moveUp() {
        spaceship.moveUp();
    }

    public void moveDown() {
        spaceship.moveDown();
    }

    public void expandDoor() {
        spaceship.expandDoor();
    }

    public void contractDoor() {
        spaceship.contractDoor();
    }

    // Logic for alien collision and generating a new alien
    public void handleCollisionWithAlien() {
        if (alienCount >= 2) {
            Alien alien1 = null;
            Alien alien2 = null;

            // Find two random aliens
            for (GameObject obj : gameObjects) {
                if (obj instanceof Alien) {
                    if (alien1 == null) {
                        alien1 = (Alien) obj;
                    } else if (alien2 == null) {
                        alien2 = (Alien) obj;
                        break;
                    }
                }
            }

            if (alien1 != null && alien2 != null) {
                System.out.println("Two aliens collided! A new alien is created.");

                // Generate a new alien near alien1
                Alien newAlien = new Alien();
                float x = alien1.getLocation().getX() + random.nextFloat() * 10 - 5; // Random offset
                float y = alien1.getLocation().getY() + random.nextFloat() * 10 - 5; // Random offset
                newAlien.setLocation(x, y);

                gameObjects.add(newAlien);
                alienCount++;
            }
        } else {
            System.out.println("Not enough aliens to collide.");
        }
    }

    // Logic for handling alien-astronaut collisions
    public void collideAlienAndAstronaut() {
        if (alienCount > 0 && astronautCount > 0) {
            Astronaut astronaut = null;
            Alien alien = null;

            // Find one alien and one astronaut
            for (GameObject obj : gameObjects) {
                if (obj instanceof Alien) {
                    alien = (Alien) obj;
                } else if (obj instanceof Astronaut) {
                    astronaut = (Astronaut) obj;
                }

                if (alien != null && astronaut != null) {
                    break;
                }
            }

            if (alien != null && astronaut != null) {
                System.out.println("Alien and astronaut collided!");

                // Reduce astronaut's health
                astronaut.decrementHealth();
                astronaut.fight(); // Track fights for the astronaut

                // Adjust astronaut's color (less red)
                int currentColor = astronaut.getColor();
                int newColor = ColorUtil.rgb(ColorUtil.red(currentColor) - 50, ColorUtil.green(currentColor), ColorUtil.blue(currentColor));
                astronaut.setColor(newColor);

                // Reduce speed of the astronaut based on health
                astronaut.calculateSpeed();

                if (astronaut.getHealth() == 0) {
                    System.out.println("Astronaut is incapacitated and stops moving.");
                    astronautCount--; // Decrement astronaut count when incapacitated
                    gameObjects.remove(astronaut); // Remove incapacitated astronaut from the game
                }
            }
        } else {
            System.out.println("No aliens or astronauts present for a collision.");
        }
    }

    // Move spaceship to a random alien
    public void transferSpaceshipToAlien() {
        if (alienCount > 0) {
            Alien alien = null;

            // Find a random alien
            for (GameObject obj : gameObjects) {
                if (obj instanceof Alien) {
                    alien = (Alien) obj;
                    break;
                }
            }

            if (alien != null) {
                spaceship.setLocation(alien.getLocation().getX(), alien.getLocation().getY());
                System.out.println("Spaceship moved to alien at location: " + alien.getLocation());
            }
        } else {
            System.out.println("No aliens present in the world.");
        }
    }

    // Move spaceship to a random astronaut
    public void transferSpaceshipToAstronaut() {
        if (astronautCount > 0) {
            Astronaut astronaut = null;

            // Find a random astronaut
            for (GameObject obj : gameObjects) {
                if (obj instanceof Astronaut) {
                    astronaut = (Astronaut) obj;
                    break;
                }
            }

            if (astronaut != null) {
                spaceship.setLocation(astronaut.getLocation().getX(), astronaut.getLocation().getY());
                System.out.println("Spaceship moved to astronaut at location: " + astronaut.getLocation());
            }
        } else {
            System.out.println("No astronauts present in the world.");
        }
    }

    // Logic for opening the spaceship door and rescuing astronauts
    public void openSpaceshipDoor() {
        System.out.println("Spaceship door opened.");

        // Find the astronaut near the spaceship and rescue them
        for (GameObject obj : gameObjects) {
            if (obj instanceof Astronaut) {
                Astronaut astronaut = (Astronaut) obj;
                float spaceshipX = spaceship.getLocation().getX();
                float spaceshipY = spaceship.getLocation().getY();
                float astronautX = astronaut.getLocation().getX();
                float astronautY = astronaut.getLocation().getY();

                // Check if the spaceship is close enough to rescue the astronaut
                if (Math.abs(spaceshipX - astronautX) < 10 && Math.abs(spaceshipY - astronautY) < 10) {
                    System.out.println("Astronaut rescued!");

                    // Adjust the score based on how many fights the astronaut has been in
                    int fights = astronaut.getFightsInvolved();
                    int rescueScore = 10 - fights; // Base score is 10, reduced by 1 per fight
                    totalScore += rescueScore;

                    astronautsRescued++;
                    astronautCount--;
                    gameObjects.remove(astronaut); // Remove the rescued astronaut
                    break;
                }
            }
        }
    }

    // Update positions and check if the game is over
    public void gameTick() {
        gameClock++;
        for (GameObject obj : gameObjects) {
            if (obj instanceof IMoving) {
                ((IMoving) obj).move();  // Update positions based on speed and direction
            }
        }
        System.out.println("Clock ticked to: " + gameClock);

        checkGameEnd();
    }

    // Check if the game is over
    public void checkGameEnd() {
        if (astronautCount <= 0) {
            gameEnded = true;
            System.out.println("Game over! Final score: " + totalScore);
            System.exit(0);
        }
    }

    // Print the map and current state of the game
    public void printMap() {
        for (GameObject obj : gameObjects) {
            System.out.println(obj.toString());
        }
    }

    // Update score based on game events
    public void updateScore() {
        totalScore += astronautsRescued * 100 - aliensSneakedIn * 50;
    }

    // Print the game state (score, astronaut and alien counts, etc.)
    public void printGameState() {
        System.out.println("Total score: " + totalScore);
        System.out.println("Astronauts rescued: " + astronautsRescued);
        System.out.println("Aliens sneaked in: " + aliensSneakedIn);
        System.out.println("Remaining astronauts: " + astronautCount);
        System.out.println("Remaining aliens: " + alienCount);
    }
    public void exit() {
        System.out.println("Exiting the game.");
        System.exit(0);
    }
}
