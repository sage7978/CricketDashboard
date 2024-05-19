package org.example.enums;

public enum WicketType {
    LBW("Lbw"),
    STUMPED("St"),
    CAUGHT("Ct"),
    RUN_OUT("R"),
    BOWLED("B"),
    NOT_OUT("Not");

    public String val;
    WicketType(String val){
        this.val = val;
    }

    public static WicketType getWicketType(BallType ballType, String wkt){
        if (ballType == BallType.WICKET) {
            return fromVal(wkt);
        }
        return NOT_OUT;
    }


    public static WicketType fromVal(String text) {
        for (WicketType w : WicketType.values()) {
            if (w.val.equalsIgnoreCase(text)) {
                return w;
            }
        }
        return WicketType.BOWLED;
    }
}
