package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Competence implements Serializable {

    private int idComp;

    public Competence(){
    }

    public Competence(int comp){
        idComp = comp;
    }

    public int getCompetences() {
        return idComp;
    }

    public void setCompetences(int competences) {
        this.idComp = competences;
    }

    public String toString(){
        return String.valueOf(idComp);
    }

}
