package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
    private int size;
    private Point location;
    private int color;

    // Constructor
    public GameObject(int size, Point location, int color) {
        this.size = size;
        this.location = location;
        this.color = color;
    }

    // Getters
    public int getSize() {
        return size;
    }

    public Point getLocation() {
        return location;
    }

    public int getColor() {
        return color;
    }

    // Setters
    public void setLocation(float x, float y) {
        this.location.setX(x);
        this.location.setY(y);
    }

    public void setColor(int newColor) {
        this.color = newColor;
    }

    // Abstract method to be overridden by subclasses
    public abstract String toString();
}
