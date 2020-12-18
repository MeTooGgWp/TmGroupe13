package com.example.dragonsheetmanager;

public interface RequestCodes {

    //Modification des stats :
    public static final int REQUEST_CODE_FORM_USERINPUT_FORCE = 1;
    public static final int REQUEST_CODE_FORM_USERINPUT_DEXT = 2;
    public static final int REQUEST_CODE_FORM_USERINPUT_CONST = 3;
    public static final int REQUEST_CODE_FORM_USERINPUT_INTEL = 4;
    public static final int REQUEST_CODE_FORM_USERINPUT_WISD= 5;
    public static final int REQUEST_CODE_FORM_USERINPUT_CHAR = 6;

    //Modifications des maitrises et des langues :
    public static final int REQUEST_CODE_FORM_USERINPUT_MASTERIES = 7;
    public static final int REQUEST_CODE_FORM_USERINPUT_LANGUAGES = 8;

    //Pour les information de base du personnage :

    public static final int REQUEST_CODE_FORM_USERINPUT_CHARACTER_NAME = 9;
    public static final int REQUEST_CODE_FORM_USERINPUT_CHARACTER_CLASS = 10;
    public static final int REQUEST_CODE_FORM_USERINPUT_CHARACTER_RACE = 11;
    public static final int REQUEST_CODE_FORM_USERINPUT_CHARACTER_EXP = 12;
    public static final int REQUEST_CODE_FORM_USERINPUT_CHARACTER_LEVEL = 13;

    //Pour les informations du status :

    public static final int REQUEST_CODE_FORM_USERINPUT_CA = 14;
    public static final int REQUEST_CODE_FORM_USERINPUT_INITIATIVE = 15;
    public static final int REQUEST_CODE_FORM_USERINPUT_SPEED = 16;


    //Pour les hp
    public static final int REQUEST_CODE_FORM_USERINPUT_HPMAX = 17;
    public static final int REQUEST_CODE_FORM_USERINPUT_HPCURRENT = 18;
    public static final int REQUEST_CODE_FORM_USERINPUT_HPTEMP = 19;

    //Pour l'argent :

    public static final int REQUEST_CODE_FORM_USERINPUT_COPPER = 20;
    public static final int REQUEST_CODE_FORM_USERINPUT_SILVER = 21;
    public static final int REQUEST_CODE_FORM_USERINPUT_ELECTRUM = 22;
    public static final int REQUEST_CODE_FORM_USERINPUT_GOLD = 23;
    public static final int REQUEST_CODE_FORM_USERINPUT_PLATINE = 24;

    //Afin d'ajouter une attaque
    public static final int REQUEST_CODE_FORM_ATTAQUE_ADD = 25;

    //Afin d'ajouter les details
    public static final int REQUEST_CODE_FORM_USERINPUT_ALLIES = 26;
    public static final int REQUEST_CODE_FORM_USERINPUT_AGE = 27 ;
    public static final int REQUEST_CODE_FORM_USERINPUT_APPARENCE = 28;
    public static final int REQUEST_CODE_FORM_USERINPUT_BACKSTORY = 29;
    public static final int REQUEST_CODE_FORM_USERINPUT_CAPACITE = 30 ;
    public static final int REQUEST_CODE_FORM_USERINPUT_DEFAUT = 31 ;
    public static final int REQUEST_CODE_FORM_USERINPUT_EQUIPEMENT = 32 ;
    public static final int REQUEST_CODE_FORM_USERINPUT_HISTORIQUE = 33 ;
    public static final int REQUEST_CODE_FORM_USERINPUT_IDEAUX = 34 ;
    public static final int REQUEST_CODE_FORM_USERINPUT_LIENS =  35;
    public static final int REQUEST_CODE_FORM_USERINPUT_NOTE = 36 ;
    public static final int REQUEST_CODE_FORM_USERINPUT_TRAIT = 37 ;

    //Afin d'ajouter les sorts
    public static final int REQUEST_CODE_FORM_SORTS = 38 ;

    //Retourner la fiche avec modif background
    public static final int REQUEST_CODE_FICHE = 39;




}
