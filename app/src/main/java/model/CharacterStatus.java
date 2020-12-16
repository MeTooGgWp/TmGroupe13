package model;

import java.io.Serializable;

public class CharacterStatus implements Serializable {

    private int classeArmure,initiative;
    private float vitesse;

    public CharacterStatus(int classeArmure, int initiative, float vitesse) {
        this.classeArmure = classeArmure;
        this.initiative = initiative;
        this.vitesse = vitesse;
    }

    public int getClasseArmure() {
        return classeArmure;
    }

    public void setClasseArmure(int classeArmure) {
        this.classeArmure = classeArmure;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public float getVitesse() {
        return vitesse;
    }

    public void setVitesse(float vitesse) {
        this.vitesse = vitesse;
    }
}
