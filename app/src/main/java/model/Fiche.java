package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Fiche implements Serializable {


    private int idFiche;
    private String idJoueur;
    private String equipement;
    private String capaciteEtTrait;
    private String note;
    private boolean inspiration;
    private List<Competence> competences;
    private List<Sort> sorts;
    private List<Attaque> attaques;

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

   public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }


    public List<Attaque> getAttaques() {
        return attaques;
    }

    public void setAttaques(List<Attaque> attaques) {
        this.attaques = attaques;
    }


    public List<Sort> getSorts() {
        if (sorts == null){
            this.sorts = new ArrayList<>();
        }
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }

    @Override
    public String toString() {
        return "Fiche{" +
                "idFiche=" + idFiche +
                ", idJoueur='" + idJoueur + '\'' +
                ", Compétence" + competences.toString()+
                '}';
    }


    public boolean isMasterised(int id){
        for(Competence c:competences){
            if (c.getCompetences() == id)
                return true;
        }
        return false;
    }

    public void sortSort(){
        Collections.sort(sorts);
    }


}
