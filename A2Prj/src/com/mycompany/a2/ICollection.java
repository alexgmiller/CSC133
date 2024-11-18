package com.mycompany.a2;

public interface ICollection {
    void add(GameObject obj);           // Add a game object to the collection
    void remove(GameObject obj);        // Remove a specified game object from the collection
    IIterator getIterator();            // Return an iterator for traversing the collection
}
