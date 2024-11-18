package com.mycompany.a2;

public interface IGuided {
    void moveLeft();
    void moveRight();
    void moveUp();
    void moveDown();
    void jumpToLocation(float x, float y);  // Allows jumping to a specific location
}
