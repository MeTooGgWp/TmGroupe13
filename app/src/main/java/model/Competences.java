package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Competences implements Serializable {

    private int idComp;

    public Competences(){
    }

    public Competences(int comp){
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
