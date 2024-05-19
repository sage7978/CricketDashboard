package org.example.enums;

public enum BallType {
    WIDE("Wd", false, true),
    NO_BALL("Nb", false, true),
    WICKET("Wkt", true, false),
    LEG_BY("Lb", true, true),
    DOT("Dt", true, false),
    NORMAL("No", true, false);

    public String val;
    public boolean ballToBeCounted;
    public boolean extraRun;

    public static BallType fromVal(String text) {
        for (BallType b : BallType.values()) {
            if (b.val.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return NORMAL;
    }

    BallType(String val, boolean ballToBeCounted, boolean extraRun){
        this.ballToBeCounted = ballToBeCounted;
        this.val = val;
        this.extraRun = extraRun;
    }
}
