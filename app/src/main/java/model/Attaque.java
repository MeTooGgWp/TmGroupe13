package model;

import java.io.Serializable;

public class Attaque implements Serializable {

    private String nomAttaque,typeDegat;
    private int bonusAuJet,bonusAuDegat,deDegat;

    public Attaque(String nomAttaque,int bonusAuDegat,String typeDegat,int bonusAuJet,int deDegat){
        this.nomAttaque = nomAttaque;
        this.bonusAuDegat = bonusAuDegat;
        this.typeDegat = typeDegat;
        this.bonusAuJet = bonusAuJet;
        this.deDegat = deDegat;
    }

    public Attaque(){
        this("",0,"",0,0);
    }

    public String getNomAttaque() {
        return nomAttaque;
    }

    public void setNomAttaque(String nomAttaque) {
        this.nomAttaque = nomAttaque;
    }

    public String getTypeDegat() {
        return typeDegat;
    }

    public void setTypeDegat(String typeDegat) {
        this.typeDegat = typeDegat;
    }

    public int getBonusAuJet() {
        return bonusAuJet;
    }

    public void setBonusAuJet(int bonusAuJet) {
        this.bonusAuJet = bonusAuJet;
    }

    public int getBonusAuDegat() {
        return bonusAuDegat;
    }

    public void setBonusAuDegat(int bonusAuDegat) {
        this.bonusAuDegat = bonusAuDegat;
    }

    public int getDeDegat() {
        return deDegat;
    }

    public void setDeDegat(int deDegat) {
        this.deDegat = deDegat;
    }
}
