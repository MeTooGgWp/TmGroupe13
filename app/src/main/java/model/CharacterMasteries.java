package model;

import java.io.Serializable;

public class CharacterMasteries implements Serializable {

    private String maitrises,langues;


    public CharacterMasteries(String maitrises, String langues) {
        this.maitrises = maitrises;
        this.langues = langues;
    }

    public String getMaitrises() {
        return maitrises;
    }

    public void setMaitrises(String maitrises) {
        this.maitrises = maitrises;
    }

    public String getLangues() {
        return langues;
    }

    public void setLangues(String langues) {
        this.langues = langues;
    }
}
