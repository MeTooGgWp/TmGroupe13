package model;

public class SaveRollManager {

    private boolean forceSave,dexteriteSave,constitutionSave,intelligenceSave,sagesseSave,charismeSave;

    public SaveRollManager(boolean forceSave, boolean dexteriteSave, boolean constitutionSave, boolean intelligenceSave, boolean sagesseSave, boolean charismeSave) {
        this.forceSave = forceSave;
        this.dexteriteSave = dexteriteSave;
        this.constitutionSave = constitutionSave;
        this.intelligenceSave = intelligenceSave;
        this.sagesseSave = sagesseSave;
        this.charismeSave = charismeSave;
    }


    public boolean isForceSave() {
        return forceSave;
    }

    public void setForceSave(boolean forceSave) {
        this.forceSave = forceSave;
    }

    public boolean isDexteriteSave() {
        return dexteriteSave;
    }

    public void setDexteriteSave(boolean dexteriteSave) {
        this.dexteriteSave = dexteriteSave;
    }

    public boolean isConstitutionSave() {
        return constitutionSave;
    }

    public void setConstitutionSave(boolean constitutionSave) {
        this.constitutionSave = constitutionSave;
    }

    public boolean isIntelligenceSave() {
        return intelligenceSave;
    }

    public void setIntelligenceSave(boolean intelligenceSave) {
        this.intelligenceSave = intelligenceSave;
    }

    public boolean isSagesseSave() {
        return sagesseSave;
    }

    public void setSagesseSave(boolean sagesseSave) {
        this.sagesseSave = sagesseSave;
    }

    public boolean isCharismeSave() {
        return charismeSave;
    }

    public void setCharismeSave(boolean charismeSave) {
        this.charismeSave = charismeSave;
    }
}
