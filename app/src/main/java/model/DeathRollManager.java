package model;

import java.io.Serializable;

public class DeathRollManager implements Serializable {

    private int successRoll,failRoll;

    public DeathRollManager(int successRoll, int failRoll) {
        this.successRoll = successRoll;
        this.failRoll = failRoll;
    }

    public int getSuccessRoll() {
        return successRoll;
    }

    public void setSuccessRoll(int successRoll) {
        this.successRoll = successRoll;
    }

    public int getFailRoll() {
        return failRoll;
    }

    public void setFailRoll(int failRoll) {
        this.failRoll = failRoll;
    }
}
