package model;

import java.io.Serializable;

public class BasicCharacterInfo implements Serializable {

    private String classe;
    private byte niveau;
    private String race;
    private String nomPersonnage;
    private int experience;


    public BasicCharacterInfo(String classe,byte niveau,String race,String nomPersonnage,int experience){
        this.classe = classe;
        this.niveau = niveau;
        this.race = race;
        this.nomPersonnage = nomPersonnage;
        this.experience = experience;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public byte getNiveau() {
        return niveau;
    }

    public void setNiveau(byte niveau) {
        this.niveau = niveau;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getNomPersonnage() {
        return nomPersonnage;
    }

    public void setNomPersonnage(String nomPersonnage) {
        this.nomPersonnage = nomPersonnage;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
