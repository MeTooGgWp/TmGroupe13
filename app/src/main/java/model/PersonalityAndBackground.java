package model;

import java.io.Serializable;

public class PersonalityAndBackground implements Serializable {

    private String traitDePersonnalite;
    private String ideaux;
    private String liens;
    private String defauts ;
    private int alignement ;
    private String historique ;
    private int age ;
    private String apparence;
    private String allieEtOrganisation;
    private String background;

    public PersonalityAndBackground(int alignement, int age) {
        this.alignement = alignement;
        this.age = age;
    }


    public String getTraitDePersonnalite() {
        return traitDePersonnalite;
    }

    public void setTraitDePersonnalite(String traitDePersonnalite) {
        this.traitDePersonnalite = traitDePersonnalite;
    }

    public String getIdeaux() {
        return ideaux;
    }

    public void setIdeaux(String ideaux) {
        this.ideaux = ideaux;
    }

    public String getLiens() {
        return liens;
    }

    public void setLiens(String liens) {
        this.liens = liens;
    }

    public String getDefauts() {
        return defauts;
    }

    public void setDefauts(String defauts) {
        this.defauts = defauts;
    }

    public int getAlignement() {
        return alignement;
    }

    public void setAlignement(int alignement) {
        this.alignement = alignement;
    }

    public String getHistorique() {
        return historique;
    }

    public void setHistorique(String historique) {
        this.historique = historique;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getApparence() {
        return apparence;
    }

    public void setApparence(String apparence) {
        this.apparence = apparence;
    }

    public String getAllieEtOrganisation() {
        return allieEtOrganisation;
    }

    public void setAllieEtOrganisation(String allieEtOrganisation) {
        this.allieEtOrganisation = allieEtOrganisation;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
