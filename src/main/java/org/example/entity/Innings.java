package org.example.entity;

import org.example.enums.BallType;
import org.example.features.Overable;
import org.example.features.Printable;
import org.example.objects.BallVO;

public class Innings implements Overable, Printable {
    Team batting;
    Team bowling;

    public Innings(Team batting, Team bowling) {
        this.batting = batting;
        this.bowling = bowling;
    }

    @Override
    public boolean isOver() {
        return batting.battingPlay.isOver() || bowling.bowlingPlay.isOver();
    }

    public void nextBall(String baller){

        BallVO ballObject = BallVO.getBallObject(baller);
        Ball ball = Ball.mapBallFromVO(ballObject);

        if(BallType.WICKET.equals(ball.ballType)){
            batting.out();
            if(bowling.wicketTaken(ball)){
                batting.rotate();
            }
            return;
        }
        if(!ball.toBeCounted){
            batting.addExtraRun(ball.run);
            bowling.addABall(ball);
            return;
        }
        batting.addARun(ball);
        if(bowling.addABall(ball)){
            batting.rotate();
        }
    }

    @Override
    public void print() {
        System.out.printf("Batting Team %s \n ", batting.name);
        batting.battingPlay.print();
    }

    public void start() {
        batting.battingPlay.getNewBatsmen();
    }
}
