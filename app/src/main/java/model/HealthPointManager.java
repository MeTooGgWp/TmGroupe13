package model;

public class HealthPointManager {

    private int maxHp;
    private int currentHp,temporaryHp,hpDice;

    public HealthPointManager(int maxHp, int currentHp, int temporaryHp, int hpDice) {
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.temporaryHp = temporaryHp;
        this.hpDice = hpDice;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getTemporaryHp() {
        return temporaryHp;
    }

    public void setTemporaryHp(int temporaryHp) {
        this.temporaryHp = temporaryHp;
    }

    public int getHpDice() {
        return hpDice;
    }

    public void setHpDice(int hpDice) {
        this.hpDice = hpDice;
    }
}
