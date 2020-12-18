package model;

import java.io.Serializable;

public class Sort implements Serializable, Comparable {

    public int niveauSort;
    public String nomSort;
    public String descriptionSort;

    public Sort(int niveauSort,String nomSort,String descriptionSort){
        this.niveauSort = niveauSort;
        this.nomSort = nomSort;
        this.descriptionSort = descriptionSort;
    }


    public int getNiveauSort() {
        return niveauSort;
    }

    public void setNiveauSort(int niveauSort) {
        this.niveauSort = niveauSort;
    }

    public String getNomSort() {
        return nomSort;
    }

    public void setNomSort(String nomSort) {
        this.nomSort = nomSort;
    }

    public String getDescriptionSort() {
        return descriptionSort;
    }

    public void setDescriptionSort(String descriptionSort) {
        this.descriptionSort = descriptionSort;
    }


    @Override
    public int compareTo(Object o) {
        int compareLvl = ((Sort)o).getNiveauSort();
        //Pour l'ordre croissant :
        return this.niveauSort-compareLvl;
    }
}
