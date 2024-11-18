package com.mycompany.a2;

public interface IIterator {
    boolean hasNext();   // Check if there is another object
    GameObject getNext(); // Return the next object
    void remove();        // Remove the current object
}
