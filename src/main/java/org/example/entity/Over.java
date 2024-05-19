package org.example.entity;

import org.example.enums.BallType;
import org.example.features.Overable;

import java.util.ArrayList;
import java.util.List;

public class Over implements Overable {
    List<Ball> balls;
    Bowler bowler;
    int extras;

    public Over(){
        balls = new ArrayList<>();
        bowler = new Bowler("Colocasia", 1);
        extras = 0;
    }

    @Override
    public boolean isOver() {
        int cnt = 0;
        for(int i=0;i<balls.size();i++){
            if(balls.get(i).toBeCounted){
                cnt++;
            }
        }
        return (cnt == 6);
    }

    public void add(Ball ball) {
        balls.add(ball);
    }
}
