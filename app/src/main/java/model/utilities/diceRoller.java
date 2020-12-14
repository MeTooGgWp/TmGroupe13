package model.utilities;

import android.util.Log;

import java.util.Random;

public abstract class diceRoller {

    public static int roll(int diceType){
        Random rand = new Random();
        int diceResult = rand.nextInt(diceType) + 1;
        Log.i("Dice", String.valueOf(diceResult));
        return diceResult;
    }


}
