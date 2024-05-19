package org.example.entity;

import org.example.features.Battable;

public class Batsman extends Player implements Battable {
    int balls;
    int runs;
    double strikeRate;
    boolean isOut;
    boolean onCrease;
    boolean onStrike;
    int ones;
    int twos;
    int threes;
    int fours;
    int sixes;

    public Batsman(String name, int jersey) {
        super(name, jersey);
        balls = 0;
        runs = 0;
        strikeRate = 0.0;
        isOut = false;
        onCrease = false;
        onStrike = false;
    }

    @Override
    public int bat(Ball ball) {
        runs += ball.run;
        balls++;
        strikeRate = (runs  * 1.0D / balls) * 100;
        onStrike = true;
        switch(ball.scoreType){
            case SINGLE -> ones++;
            case DOUBLE -> twos++;
            case TRIPLE -> threes++;
            case FOUR -> fours++;
            case SIX -> sixes++;
        }
        return runs;
    }

    @Override
    public boolean out() {
        isOut = true;
        onCrease = false;
        onStrike = false;
        return isOut;
    }

    @Override
    public boolean come() {
        isOut = false;
        onCrease = true;
        runs = 0;
        balls = 0;
        strikeRate = 0.0;
        onStrike = true;
        return onCrease;
    }


//    @Override
//    public String toString() {
//        return "Batsman{" +
//                "balls=" + balls +
//                ", runs=" + runs +
//                ", strikeRate=" + strikeRate +
//                ", isOut=" + isOut +
//                ", onCrease=" + onCrease +
//                ", onStrike=" + onStrike +
//                ", name='" + name + '\'' +
//                ", jersey=" + jersey +
//                '}';
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append((onStrike)?"*":"");
        sb.append(" - ");
        sb.append(runs);
        sb.append("/");
        sb.append(balls);
        sb.append("     ");
        sb.append(strikeRate);
        return sb.toString();
    }
}
