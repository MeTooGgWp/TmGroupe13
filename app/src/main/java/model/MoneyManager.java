package model;

public class MoneyManager {

    private int cuivre,argent,or,platine,electrum;


    public MoneyManager(int cuivre, int argent, int or, int platine, int electrum) {
        this.cuivre = cuivre;
        this.argent = argent;
        this.or = or;
        this.platine = platine;
        this.electrum = electrum;
    }


    public int getCuivre() {
        return cuivre;
    }

    public void setCuivre(int cuivre) {
        this.cuivre = cuivre;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public int getOr() {
        return or;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public int getPlatine() {
        return platine;
    }

    public void setPlatine(int platine) {
        this.platine = platine;
    }

    public int getElectrum() {
        return electrum;
    }

    public void setElectrum(int electrum) {
        this.electrum = electrum;
    }
}
