package com.mycompany.a2;

import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable {
    private int totalScore;
    private int astronautsRescued;
    private int aliensSneakedIn;
    private int astronautCount;
    private int alienCount;
    private int gameClock;
    private boolean gameEnded;
    private boolean soundOn = true;  // Default sound state set to ON
    private GameObjectCollection gameObjects;  // Store game objects
    private Spaceship spaceship;
    private final int gameWorldWidth = 1000;
    private final int gameWorldHeight = 1000;
    private Random random = new Random();

    // Constructor
    public GameWorld() {
        spaceship = Spaceship.getInstance();  // Initialize the spaceship (Singleton)
        gameObjects = new GameObjectCollection();  // Initialize GameObjectCollection
    }

    // Initialize the game world
    public void init() {
        this.totalScore = 0;
        this.astronautsRescued = 0;
        this.aliensSneakedIn = 0;
        this.astronautCount = 4;  // Fixed count of 4 astronauts
        this.alienCount = 3;      // Fixed count of 3 aliens
        this.gameClock = 0;
        this.gameEnded = false;

        addGameObjects();  // Add initial game objects

        // Notify observers (views) that the game has initialized
        setChanged();
        notifyObservers();
    }

    // Add initial GameObjects (Astronauts, Aliens, and Spaceship) to the GameWorld
    private void addGameObjects() {
        for (int i = 0; i < astronautCount; i++) {
            gameObjects.add(new Astronaut());  // Add 4 astronauts
        }

        for (int i = 0; i < alienCount; i++) {
            gameObjects.add(new Alien());  // Add 3 aliens
        }

        gameObjects.add(spaceship);  // Add the singleton spaceship
    }

    // Getter for the game objects collection
    public GameObjectCollection getGameObjects() {
        return gameObjects;
    }

    // Expand the spaceship door
    public void expandDoor() {
        spaceship.expandDoor();  // Delegate to the spaceship's expandDoor method
        setChanged();  // Mark that the state has changed
        notifyObservers();  // Notify observers that the state has changed
    }

    // Contract the spaceship door
    public void contractDoor() {
        spaceship.contractDoor();  // Delegate to the spaceship's contractDoor method
        setChanged();  // Mark that the state has changed
        notifyObservers();  // Notify observers that the state has changed
    }

    // Move spaceship to the location of a random alien
    public void transferSpaceshipToAlien() {
        if (alienCount > 0) {
            Alien alien = null;
            IIterator iterator = gameObjects.getIterator();
            while (iterator.hasNext()) {
                GameObject obj = iterator.getNext();
                if (obj instanceof Alien) {
                    alien = (Alien) obj;
                    break;
                }
            }

            if (alien != null) {
                spaceship.setLocation(alien.getLocation().getX(), alien.getLocation().getY());
                System.out.println("Spaceship moved to alien at location: " + alien.getLocation());
                setChanged();
                notifyObservers();  // Notify observers (MapView, ScoreView)
            }
        } else {
            System.out.println("No aliens present in the world.");
        }
    }

    // Move spaceship to the location of a random astronaut
    public void transferSpaceshipToAstronaut() {
        if (astronautCount > 0) {
            Astronaut astronaut = null;
            IIterator iterator = gameObjects.getIterator();
            while (iterator.hasNext()) {
                GameObject obj = iterator.getNext();
                if (obj instanceof Astronaut) {
                    astronaut = (Astronaut) obj;
                    break;
                }
            }

            if (astronaut != null) {
                spaceship.setLocation(astronaut.getLocation().getX(), astronaut.getLocation().getY());
                System.out.println("Spaceship moved to astronaut at location: " + astronaut.getLocation());
                setChanged();
                notifyObservers();  // Notify observers (MapView, ScoreView)
            }
        } else {
            System.out.println("No astronauts present in the world.");
        }
    }

    // Spaceship movement logic with boundary checks
    public void moveRight() {
        float currentX = spaceship.getLocation().getX();
        if (currentX + 10 <= gameWorldWidth) {
            spaceship.moveRight();
            setChanged();
            notifyObservers();
        } else {
            System.out.println("Cannot move right. Spaceship is at the right boundary.");
        }
    }

    public void moveLeft() {
        float currentX = spaceship.getLocation().getX();
        if (currentX - 10 >= 0) {
            spaceship.moveLeft();
            setChanged();
            notifyObservers();
        } else {
            System.out.println("Cannot move left. Spaceship is at the left boundary.");
        }
    }

    public void moveUp() {
        float currentY = spaceship.getLocation().getY();
        if (currentY + 10 <= gameWorldHeight) {
            spaceship.moveUp();
            setChanged();
            notifyObservers();
        } else {
            System.out.println("Cannot move up. Spaceship is at the top boundary.");
        }
    }

    public void moveDown() {
        float currentY = spaceship.getLocation().getY();
        if (currentY - 10 >= 0) {
            spaceship.moveDown();
            setChanged();
            notifyObservers();
        } else {
            System.out.println("Cannot move down. Spaceship is at the bottom boundary.");
        }
    }

    // Game tick logic
    public void gameTick() {
        gameClock++;
        IIterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject obj = iterator.getNext();
            if (obj instanceof IMoving) {
                ((IMoving) obj).move();  // Update positions of moving objects
            }
        }
        setChanged();  // Mark state as changed
        notifyObservers();  // Notify observers of the change
        System.out.println("Clock ticked to: " + gameClock);
    }

    // Print the game state (score, astronaut and alien counts, etc.)
    public void printGameState() {
        System.out.println("Total score: " + totalScore);
        System.out.println("Astronauts rescued: " + astronautsRescued);
        System.out.println("Aliens sneaked in: " + aliensSneakedIn);
        System.out.println("Remaining astronauts: " + astronautCount);
        System.out.println("Remaining aliens: " + alienCount);
    }

    // Getters for game state (for ScoreView)
    public int getTotalScore() {
        return totalScore;
    }

    public int getAstronautsRescued() {
        return astronautsRescued;
    }

    public int getAliensSneakedIn() {
        return aliensSneakedIn;
    }

    public int getGameClock() {
        return gameClock;
    }
    public int getAstronautCount() {
        return astronautCount;
    }

    public int getAlienCount() {
        return alienCount;
    }

    // Method to add a new alien to the game world
    public void addNewAlien() {
        Alien newAlien = new Alien();  // Create a new alien
        gameObjects.add(newAlien);  // Add the new alien to the gameObjects collection
        alienCount++;  // Increment the alien count
        System.out.println("A new alien has been added to the game.");
        setChanged();  // Mark the state as changed
        notifyObservers();  // Notify observers (e.g., MapView and ScoreView)
    }

    // Method to toggle the sound state
    public void toggleSound() {
        soundOn = !soundOn;  // Flip the sound state
        System.out.println("Sound is now " + (soundOn ? "ON" : "OFF"));
        setChanged();
        notifyObservers();
    }

    // Getter method for the sound state (for ScoreView)
    public boolean isSoundOn() {
        return soundOn;
    }

    // Method to handle alien-astronaut collisions
    public void collideAlienAndAstronaut() {
        if (alienCount > 0 && astronautCount > 0) {
            Astronaut astronaut = null;
            Alien alien = null;

            IIterator iterator = gameObjects.getIterator();
            while (iterator.hasNext()) {
                GameObject obj = iterator.getNext();
                if (obj instanceof Alien) {
                    alien = (Alien) obj;
                } else if (obj instanceof Astronaut) {
                    astronaut = (Astronaut) obj;
                }

                if (alien != null && astronaut != null) {
                    break;  // We've found both an alien and an astronaut
                }
            }

            if (alien != null && astronaut != null) {
                System.out.println("Alien and astronaut collided!");

                astronaut.decrementHealth();  // Decrease astronaut's health
                astronaut.fight();  // Handle fight logic, such as reducing speed or changing color

                if (astronaut.getHealth() == 0) {
                    System.out.println("Astronaut is incapacitated.");
                    gameObjects.remove(astronaut);  // Remove the incapacitated astronaut
                    astronautCount--;  // Decrease the astronaut count
                }

                setChanged();
                notifyObservers();
            } else {
                System.out.println("No alien or astronaut available for a collision.");
            }
        } else {
            System.out.println("No aliens or astronauts present for a collision.");
        }
    }
}
