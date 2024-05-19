package org.example.objects;

import org.example.enums.BallType;
import org.example.enums.ScoreType;
import org.example.enums.WicketType;

public class BallVO {
    BallType ballType;
    ScoreType scoreType;
    WicketType wicketType;
    int run;

    public static BallVO getBallObject(String baller){
        BallVO ballVO = new BallVO();
        String curBall = null;
        int run = 0;

        if(onlySingleChar(baller)){
            int num = onlyNumber(baller);
            if(num!=-1){
                if(num == 0){
                    curBall = BallType.DOT.val;
                }
                else {
                    curBall = BallType.NORMAL.val;
                }
                run = num;
            } else {
                curBall = baller;
            }
        }
        else{
            curBall = baller.split(" ")[0];
            run = Integer.parseInt(baller.split(" ")[1]);
        }

        BallType ballType = BallType.fromVal(curBall);

        ballVO.ballType = ballType;
        ballVO.scoreType = ScoreType.getScoreType(run, ballType);
        ballVO.run = ballVO.scoreType.val;
        ballVO.wicketType = WicketType.getWicketType(ballType, "");
        return ballVO;
    }

    private static boolean onlySingleChar(String baller) {
        return (baller.split(" ").length < 2);
    }

    private static int onlyNumber(String baller) {
        try{
            int ans = Integer.parseInt(baller);
            return ans;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args){
        String baller  = "Wd 1"; // Only "Wd" as "Wd 2" doesn't matter
        System.out.println(getBallObject(baller));  // BallVO{ballType=WIDE, toBeCounted=false, extraRun=true, scoreType=EXTRA, wicketType=NOT_OUT, run=1}

        baller = "No 1"; // Only "1", "2" -> "No 1"
        System.out.println(getBallObject(baller));  // BallVO{ballType=NORMAL, toBeCounted=true, extraRun=false, scoreType=SINGLE, wicketType=NOT_OUT, run=1}

        baller = "No 2";
        System.out.println(getBallObject(baller));  // BallVO{ballType=NORMAL, toBeCounted=true, extraRun=false, scoreType=DOUBLE, wicketType=NOT_OUT, run=2}

        baller = "No 6";
        System.out.println(getBallObject(baller));  // BallVO{ballType=NORMAL, toBeCounted=true, extraRun=false, scoreType=SIX, wicketType=NOT_OUT, run=6}

        baller = "Dt 0";  // Only "0" -> "Dt 0"
        System.out.println(getBallObject(baller));  // BallVO{ballType=DOT, toBeCounted=true, extraRun=false, scoreType=NO_RUN, wicketType=NOT_OUT, run=0}

        baller = "Wkt 1"; // Only "Wkt"
        System.out.println(getBallObject(baller));  // BallVO{ballType=WICKET, toBeCounted=true, extraRun=false, scoreType=NO_RUN, wicketType=BOWLED, run=0}

        baller = "Nb 2"; // Only "Nb"
        System.out.println(getBallObject(baller));  // BallVO{ballType=NO_BALL, toBeCounted=false, extraRun=true, scoreType=EXTRA, wicketType=NOT_OUT, run=1}

        baller = "Lb 1";
        System.out.println(getBallObject(baller));  // BallVO{ballType=LEG_BY, toBeCounted=true, extraRun=true, scoreType=SINGLE, wicketType=NOT_OUT, run=1}

        baller  = "Lb 1"; // Only "Wd" as "Wd 2" doesn't matter
        System.out.println(getBallObject(baller));
    }

    public BallType getBallType() {
        return ballType;
    }

    public void setBallType(BallType ballType) {
        this.ballType = ballType;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

    public WicketType getWicketType() {
        return wicketType;
    }

    public void setWicketType(WicketType wicketType) {
        this.wicketType = wicketType;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }


    public BallVO() {
    }

    @Override
    public String toString() {
        return "BallVO{" +
                "ballType=" + ballType +
                ", toBeCounted=" + ballType.ballToBeCounted +
                ", extraRun=" + ballType.extraRun +
                ", scoreType=" + scoreType +
                ", wicketType=" + wicketType +
                ", run=" + run +
                '}';
    }
}


// 1
// 2
// 6
// Wd 1
// Wkt
// Nb 2
// Wd
// Wkt