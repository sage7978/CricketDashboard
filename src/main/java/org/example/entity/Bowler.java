package org.example.entity;

import org.example.features.Bowlable;

public class Bowler extends Player implements Bowlable {
    int balls;
    int runs;
    int wickets;
    double economy;

    public Bowler(String name, int jersey) {
        super(name, jersey);
        runs = 0;
        balls = 0;
        wickets = 0;
        economy = 0.0;
    }

    @Override
    public Ball bowl(String ball) {
        return null;
    }

    @Override
    public Batsman takeWicket() {
        return null;
    }
}
