package com.mycompany.a1;

import com.codename1.charts.models.Point;

public interface IGuided {
    void moveLeft();
    void moveRight();
    void moveUp();
    void moveDown();
    void jumpToLocation(Point p);
}
