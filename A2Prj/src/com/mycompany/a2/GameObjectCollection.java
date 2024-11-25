package com.mycompany.a2;

import java.util.ArrayList;
import java.util.List;

// GameObjectCollection class implements ICollection
public class GameObjectCollection implements ICollection {
    private List<GameObject> gameObjects; // List to hold all game objects

    public GameObjectCollection() {
        gameObjects = new ArrayList<>(); // Initialize the list
    }

    // Add a new GameObject to the collection
    public void add(GameObject obj) {
        gameObjects.add(obj);
    }
    
    public void remove(GameObject obj) {
        gameObjects.remove(obj);  // Removes the specified GameObject from the collection
    }

    // Return an iterator for the GameObject collection
    public IIterator getIterator() {
        return new GameObjectIterator();
    }

    // Inner class implementing the IIterator interface
    private class GameObjectIterator implements IIterator {
        private int index = 0;

        // Check if there is another object in the collection
        public boolean hasNext() {
            return index < gameObjects.size();
        }

        // Return the next object in the collection
        public GameObject getNext() {
            if (hasNext()) {
                return gameObjects.get(index++);
            }
            return null;
        }

        // Optional: Remove the current object (could be implemented if needed)
        public void remove() {
            if (index > 0 && index <= gameObjects.size()) {
                gameObjects.remove(--index);
            }
        }
    }
}
