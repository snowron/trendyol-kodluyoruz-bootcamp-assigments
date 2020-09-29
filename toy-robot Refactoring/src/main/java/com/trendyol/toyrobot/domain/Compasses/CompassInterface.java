package com.trendyol.toyrobot.domain.Compasses;

import com.trendyol.toyrobot.domain.Rover;

public interface CompassInterface {
    String getDirection();
    void setRover(Rover rover);
    void getLeftDirection();
    void getRightDirection();
    void getNewCoordinates();
}