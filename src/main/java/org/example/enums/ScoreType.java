package org.example.enums;

public enum ScoreType {
    FOUR(4),
    SIX(6),
    EXTRA(1),
    SINGLE(1),
    DOUBLE(2),
    TRIPLE(3),
    NO_RUN(0);

    public int val;

    ScoreType(int val){
        this.val = val;
    }

    public static ScoreType getScoreType(int run, BallType ballType){
        try {
            switch(ballType){
                case WIDE, NO_BALL -> {
                    return EXTRA;
                }
                case DOT, WICKET -> {
                    return NO_RUN;
                }
                case NORMAL, LEG_BY -> {
                    switch(run){
                        case 1 -> {
                            return SINGLE;
                        }
                        case 2 -> {
                            return DOUBLE;
                        }
                        case 3 -> {
                            return TRIPLE;
                        }
                        case 4 -> {
                            return FOUR;
                        }
                        case 6 -> {
                            return SIX;
                        }
                        default -> {
                            return NO_RUN;
                        }
                    }
                }
                default -> {
                    return NO_RUN;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.printf("Invalid run %s, ball: %s", run, ballType);
            return NO_RUN;
        }
    }
}
