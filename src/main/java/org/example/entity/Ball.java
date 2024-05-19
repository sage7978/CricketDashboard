package org.example.entity;

import org.example.enums.BallType;
import org.example.enums.ScoreType;
import org.example.enums.WicketType;
import org.example.objects.BallVO;

public class Ball {
    int run;
    boolean toBeCounted;
    BallType ballType;
    ScoreType scoreType;
    WicketType wicketType;

    public static Ball mapBallFromVO(BallVO ballVO){
        Ball ball = new Ball();
        ball.wicketType = ballVO.getWicketType();
        ball.ballType = ballVO.getBallType();
        ball.run = ballVO.getRun();
        ball.toBeCounted = ballVO.getBallType().ballToBeCounted;
        ball.scoreType = ballVO.getScoreType();
        return ball;
    }
}
