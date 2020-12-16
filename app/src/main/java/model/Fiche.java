package model;

import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fiche implements Serializable {


    private int idFiche;
    private String idJoueur;
    private String equipement;
    private String capaciteEtTrait;
    private String note;
    private boolean inspiration;
    private Competences competences;

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
        this.equipement = "Un bâton";
        this.capaciteEtTrait ="Je sais faire des trucs";
        this.note = "J'ai pris des notes !";
        this.inspiration = true;
        basicInfo = new BasicCharacterInfo(
                "Guerrier",
                (byte) 1,
                "Elfe",
                "Patrick",
                0
        );

        hpManager = new HealthPointManager(
                10,
                10,
                0,
                10
        );
        saveRolls = new SaveRollManager(
                true,
                false,
                true,
                false,
                false,
                false
        );
        wallet = new MoneyManager(
                0,
                0,
                0,
                0,
                0
        );
        backgroundAndTrait = new PersonalityAndBackground(
                5,
                22
        );
        masteries = new CharacterMasteries(
                "Arme courantes/guerre,bouclier,armures et plein d'autre truc pour prendre beaucoup de place dans la TextView",
                "commun,elfique,nain"
        );
        caracteristics = new CaracteristicsManager(
                16,
                14,
                15,
                12,
                13,
                8
        );
        status = new CharacterStatus(
                16,
                0,
                9
        );
        deathRolls = new DeathRollManager(2,1);
        ArrayList<Integer> comp = new ArrayList<>();
        comp.add(1);
        comp.add(5);
        competences = new Competences(comp);

    }

    //Getters & Setters (used to applied modif and get information)


    public int getIdFiche() {
        return idFiche;
    }

    public void setIdFiche(int idFiche) {
        this.idFiche = idFiche;
    }

    public String getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(String idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public String getCapaciteEtTrait() {
        return capaciteEtTrait;
    }

    public void setCapaciteEtTrait(String capaciteEtTrait) {
        this.capaciteEtTrait = capaciteEtTrait;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isInspiration() {
        return inspiration;
    }

    public void setInspiration(boolean inspiration) {
        this.inspiration = inspiration;
    }

    public BasicCharacterInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicCharacterInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public HealthPointManager getHpManager() {
        return hpManager;
    }

    public void setHpManager(HealthPointManager hpManager) {
        this.hpManager = hpManager;
    }

    public SaveRollManager getSaveRolls() {
        return saveRolls;
    }

    public void setSaveRolls(SaveRollManager saveRolls) {
        this.saveRolls = saveRolls;
    }

    public DeathRollManager getDeathRolls() {
        return deathRolls;
    }

    public void setDeathRolls(DeathRollManager deathRolls) {
        this.deathRolls = deathRolls;
    }

    public MoneyManager getWallet() {
        return wallet;
    }

    public void setWallet(MoneyManager wallet) {
        this.wallet = wallet;
    }

    public PersonalityAndBackground getBackgroundAndTrait() {
        return backgroundAndTrait;
    }

    public void setBackgroundAndTrait(PersonalityAndBackground backgroundAndTrait) {
        this.backgroundAndTrait = backgroundAndTrait;
    }

    public CharacterMasteries getMasteries() {
        return masteries;
    }

    public void setMasteries(CharacterMasteries masteries) {
        this.masteries = masteries;
    }

    public CaracteristicsManager getCaracteristics() {
        return caracteristics;
    }

    public void setCaracteristics(CaracteristicsManager caracteristics) {
        this.caracteristics = caracteristics;
    }

    public CharacterStatus getStatus() {
        return status;
    }

    public void setStatus(CharacterStatus status) {
        this.status = status;
    }

    public Competences getCompetences() {
        return competences;
    }

    public void setCompetences(Competences competences) {
        this.competences = competences;
    }
}
