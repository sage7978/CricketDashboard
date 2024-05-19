package org.example.entity;

import org.example.features.Overable;

import java.util.ArrayList;
import java.util.List;

public class BowlingPlay extends TeamPlay implements Overable {
    int numberOfOvers;
    List<Over> overs;
    List<Bowler> bowlers;
    Over temp;

    public BowlingPlay(int numberOfOvers, List<Bowler> bowlers){
        this.numberOfOvers = numberOfOvers;
        this.overs = new ArrayList<>();
        this.temp = new Over();
        this.bowlers = bowlers;
    }


    @Override
    public boolean isOver() {
        if(overs.size() >= numberOfOvers){
            return true;
        }
        return false;
    }

    public boolean wicketTaken(Ball ball) {
        return addABall(ball);
    }

    public boolean addABall(Ball ball) {
        temp.add(ball);
        return checkIfOverComplete();
    }

    private boolean checkIfOverComplete() {
        if(temp.isOver()){
            System.out.println("Over is complete, Strike will be changed.");
            overs.add(temp);
            temp = new Over();
            return true;
        }
        return false;
    }
}
