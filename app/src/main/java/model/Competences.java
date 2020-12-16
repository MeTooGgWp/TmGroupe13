package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Competences implements Serializable {

    private List<Integer> competences;

    public Competences(){
        this(new ArrayList<>());
    }

    public Competences(List<Integer> comp){
        competences = new ArrayList<>();
        for (int i:comp){
            competences.add(i);
        }
    }


    public boolean isMasterised(int id){
        if(competences.contains(id))
            return true;
        return false;
    }

}
