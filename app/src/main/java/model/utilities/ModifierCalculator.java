package model.utilities;

public class ModifierCalculator {

    public static int getModifier(int Stat){
        //La formule de D&D5 pour calculer les modificateurs peut se résumer à (Stat -10)//2 (Arrondi à l'inférieur)
        float temp = (Stat-10)/2;
        return Math.round(temp);
    }


    public static int getMasteryBonus(int level){

        if(level > 16){
            return 6;
        }
        else if(level > 12){
            return 5;
        }
        else if(level > 8){
            return 4;
        }
        else if(level > 4){
            return 3;
        }
        else{
            return 2;
        }
    }

    public static int passivePerceptionCalculator(int lvl,int wisdValue){
        return 8 + getMasteryBonus(lvl) + getModifier(wisdValue);
    }

}
