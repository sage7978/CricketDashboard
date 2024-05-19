package org.example.features;

import org.example.entity.Ball;
import org.example.entity.Batsman;

public interface Bowlable {
    Ball bowl(String ball);
    Batsman takeWicket();
}
