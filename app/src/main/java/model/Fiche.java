package model;

public class Fiche {


    private int idFiche;
    private String idJoueur;
    private String equipement;
    private String capaciteEtTrait;
    private String note;
    private boolean inspiration;

    //Composant de la fiche :
    private BasicCharacterInfo basicInfo;
    private HealthPointManager hpManager;
    private SaveRollManager saveRolls;
    private DeathRollManager deathRolls;
    private MoneyManager wallet;
    private PersonalityAndBackground backgroundAndTrait;
    private CharacterMasteries masteries;
    private CaracteristicsManager caracteristics;
    private CharacterStatus status;


    public Fiche(String idJoueur) {
        this.idJoueur = idJoueur;
    }
}
