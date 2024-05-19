package org.example.features;

import org.example.entity.Ball;
import org.example.entity.Bowler;
import org.example.entity.Score;

public interface Battable {
    int bat(Ball ball);
    boolean out();
    boolean come();
}
