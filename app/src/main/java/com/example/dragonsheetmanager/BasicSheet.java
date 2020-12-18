package com.example.dragonsheetmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Attaque;
import model.Competence;
import model.DeathRollManager;
import model.Fiche;
import model.SaveRollManager;
import model.utilities.DiceRoller;
import model.utilities.ModifierCalculator;
import repository.FicheRepository;

public class BasicSheet extends AppCompatActivity implements RequestCodes{

    public static final String AUTO_COMPLETE = "AUTO_COMPLETE";
    BasicSheet bs = this;

    private Fiche fiche;

    //TextView Relatives aux valeurs des statistiques :
    private TextView tvForce,tvDext,tvConst,tvIntel,tvWisd,tvChar;
    //TextView Relatives aux valeurs des modificateur
    private TextView tvForce_mod,tvDext_mod,tvConst_mod,tvIntel_mod,tvWisd_mod,tvChar_mod;
    //TextView nom Personnage:
    private TextView tvNomPersonnage;

    //TextView Concernant les informations basiques du personnage :
    private TextView tvClasse,tvNiveau,tvRace,tvExp;

    //TextView concernant les status du personnage :
    private TextView tvCa,tvVit,tvInit;

    //CheckBox inspiration
    private CheckBox cbInspiration;

    //Bonus de maitrise:
    private TextView tvBonusMaitrise;

    //JDS:
    private CheckBox cbForceSave,cbDextSave,cbConstSave,cbIntelSave,cbWisdSave,cbCharSave;

    //Compétences :
    //Les compétences correspondront à leur id via leur indice :
    //Le [0] étant null (par simplicité de lecture)
    private CheckBox[] competences = new CheckBox[19];

    //Perception passive :
    private TextView tvPP;

    //Langues et autres maitrises  :
    private TextView tvMasteries;
    private TextView tvLanguage;

    //Pv :

    private TextView tvHpMax,tvHpActuel,tvHpTemp;
    private TextView tvDv;


    //DeathRoll

    private CheckBox[] cbSucces = new CheckBox[3];
    private CheckBox[] cbFail = new CheckBox[3];

    //Gestion des attaques :
    private ListView lvAttaques;
    private AttackArrayAdapter attackArrayAdapter;

    //Gestion des ressources monétaire :
    private TextView tvCopper,tvSilver,tvElectrum,tvGold,tvPlatinum;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_sheet);
        //Récupération de l'intent:
        Intent i = getIntent();
        //Dans un cadre hors-test passer par values.string !!
        fiche = (Fiche)i.getSerializableExtra("ficheObject");
        //Initialisation des variables "graphique" :

        //Pour les informations basiques du personnage :
        initBasicInfo();
        //Concernant l'inspiration
        cbInspiration = (CheckBox) findViewById(R.id.cb_inspiration);
        cbInspiration.setChecked(fiche.isInspiration());
        //Pour les informations du status :
       initStatus();
       //Pour le bonus de maitrise :
        tvBonusMaitrise = (TextView) findViewById(R.id.tv_masteryBonus);
        tvBonusMaitrise.setText(String.valueOf(ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau())));
        //Pour les jets de sauvegardes :
        initSaveRoll();
        //Pour les compétences :
       initSkill();
       //Perception passive
        tvPP = (TextView) findViewById(R.id.tv_pp);
       tvPP.setText(String.valueOf(ModifierCalculator.passivePerceptionCalculator(fiche.getBasicInfo().getNiveau(),fiche.getCaracteristics().getSagesse())));
       //Langues et maitrises :
        initLanguageAndMasteries();
        //Pv :
        initHp();
        //JDS contre la mort :
        initDeathSaveRoll();
        //Pour les attaques :
        lvAttaques = (ListView) findViewById(R.id.lv_attaques);
        initArrayAdapter();
        //Pour le portefeuille :
        initWallet();
        //Pour la valeur "brute" des stats
        initRoughtStat();
        //Pour le modificateur des stats:
        initModifier();

    }

    private void initModifier() {
        tvForce_mod = (TextView) findViewById(R.id.tv_force_mod);
        tvDext_mod = (TextView) findViewById(R.id.tv_dext_mod);
        tvConst_mod = (TextView) findViewById(R.id.tv_const_mod);
        tvIntel_mod = (TextView) findViewById(R.id.tv_intel_mod);
        tvWisd_mod = (TextView) findViewById(R.id.tv_wisd_mod);
        tvChar_mod = (TextView) findViewById(R.id.tv_char_mod);

        tvForce_mod.setText(String.valueOf(ModifierCalculator.getModifier(fiche.getCaracteristics().getForce())));
        tvDext_mod.setText(String.valueOf(ModifierCalculator.getModifier(fiche.getCaracteristics().getDexterite())));
        tvConst_mod.setText(String.valueOf(ModifierCalculator.getModifier(fiche.getCaracteristics().getConstitution())));
        tvIntel_mod.setText(String.valueOf(ModifierCalculator.getModifier(fiche.getCaracteristics().getIntelligence())));
        tvWisd_mod.setText(String.valueOf(ModifierCalculator.getModifier(fiche.getCaracteristics().getSagesse())));
        tvChar_mod.setText(String.valueOf(ModifierCalculator.getModifier(fiche.getCaracteristics().getCharisme())));
    }

    private void initRoughtStat() {
        tvForce = (TextView) findViewById(R.id.tv_force);
        tvDext = (TextView) findViewById(R.id.tv_dext);
        tvConst = (TextView) findViewById(R.id.tv_const);
        tvIntel = (TextView) findViewById(R.id.tv_intel);
        tvWisd = (TextView) findViewById(R.id.tv_wisd);
        tvChar = (TextView) findViewById(R.id.tv_char);

        tvForce.setText(String.valueOf(fiche.getCaracteristics().getForce()));
        tvDext.setText(String.valueOf(fiche.getCaracteristics().getDexterite()));
        tvConst.setText(String.valueOf(fiche.getCaracteristics().getConstitution()));
        tvIntel.setText(String.valueOf(fiche.getCaracteristics().getIntelligence()));
        tvWisd.setText(String.valueOf(fiche.getCaracteristics().getSagesse()));
        tvChar.setText(String.valueOf(fiche.getCaracteristics().getCharisme()));
    }

    private void initDeathSaveRoll() {
        cbSucces[0] = (CheckBox) findViewById(R.id.cb_succes1);
        cbSucces[1] = (CheckBox) findViewById(R.id.cb_succes2);
        cbSucces[2] = (CheckBox) findViewById(R.id.cb_succes3);
        //Initialisation des cb :
        for(int j = 0;j<fiche.getDeathRolls().getSuccessRoll();j++){
            cbSucces[j].setChecked(true);
        }
        //*****************************************************
        cbFail[0] = (CheckBox) findViewById(R.id.cb_fail1);
        cbFail[1] = (CheckBox) findViewById(R.id.cb_fail2);
        cbFail[2] = (CheckBox) findViewById(R.id.cb_fail3);
        //Initialisation des cb :
        for(int j = 0;j<fiche.getDeathRolls().getFailRoll();j++){
            cbFail[j].setChecked(true);
        }
    }

    private void initHp() {
        tvHpMax = (TextView) findViewById(R.id.tv_pvMax);
        tvHpActuel = (TextView) findViewById(R.id.tv_pvActuel);
        tvHpTemp = (TextView) findViewById(R.id.tv_pvTemp);

        tvHpMax.setText(String.valueOf(fiche.getHpManager().getMaxHp()));
        tvHpActuel.setText(String.valueOf(fiche.getHpManager().getCurrentHp()));
        tvHpTemp.setText(String.valueOf(fiche.getHpManager().getTemporaryHp()));

        tvDv = (TextView) findViewById(R.id.tv_dv);

        tvDv.setText("Type dé : d"+String.valueOf(fiche.getHpManager().getHpDice()));
    }

    private void initLanguageAndMasteries() {
        tvMasteries = (TextView) findViewById(R.id.tv_masteries);
        tvMasteries.setText(fiche.getMasteries().getMaitrises());
        tvMasteries.setMovementMethod(new ScrollingMovementMethod());

        tvLanguage = (TextView) findViewById(R.id.tv_language);
        tvLanguage.setText(fiche.getMasteries().getLangues());
        tvLanguage.setMovementMethod(new ScrollingMovementMethod());
    }

    private void initSkill() {
        competences[1] = findViewById(R.id.cb_acrobatie);
        competences[2] = findViewById(R.id.cb_arcanes);
        competences[3] = findViewById(R.id.cb_athletisme);
        competences[4] = findViewById(R.id.cb_discretion);
        competences[5] = findViewById(R.id.cb_dressage);
        competences[6] = findViewById(R.id.cb_escamotage);
        competences[7] = findViewById(R.id.cb_histoire);
        competences[8] = findViewById(R.id.cb_intimidation);
        competences[9] = findViewById(R.id.cb_investigation);
        competences[10] = findViewById(R.id.cb_medecine);
        competences[11] = findViewById(R.id.cb_nature);
        competences[12] = findViewById(R.id.cb_perception);
        competences[13] = findViewById(R.id.cb_perspicacite);
        competences[14] = findViewById(R.id.cb_persuasion);
        competences[15] = findViewById(R.id.cb_relegion);
        competences[16] = findViewById(R.id.cb_representation);
        competences[17] = findViewById(R.id.cb_survie);
        competences[18] = findViewById(R.id.cb_tromperie);
        //Association des compétences et des cb:
        for(int j = 1;j<=18;j++){
            competences[j].setChecked(fiche.isMasterised(j));
        }
    }

    private void initSaveRoll() {
        cbForceSave = (CheckBox) findViewById(R.id.cb_forceSave);
        cbDextSave = (CheckBox) findViewById(R.id.cb_dextSave);
        cbConstSave = (CheckBox) findViewById(R.id.cb_constSave);
        cbIntelSave = (CheckBox) findViewById(R.id.cb_intelSave);
        cbWisdSave = (CheckBox) findViewById(R.id.cb_wisdSave);
        cbCharSave = (CheckBox) findViewById(R.id.cb_charSave);

        cbForceSave.setChecked(fiche.getSaveRolls().isForceSave());
        cbDextSave.setChecked(fiche.getSaveRolls().isDexteriteSave());
        cbConstSave.setChecked(fiche.getSaveRolls().isConstitutionSave());
        cbIntelSave.setChecked(fiche.getSaveRolls().isIntelligenceSave());
        cbWisdSave.setChecked(fiche.getSaveRolls().isSagesseSave());
        cbCharSave.setChecked(fiche.getSaveRolls().isCharismeSave());
    }

    private void initStatus() {
        tvCa = (TextView) findViewById(R.id.tv_ca);
        tvVit =(TextView) findViewById(R.id.tv_vit);
        tvInit = (TextView) findViewById(R.id.tv_init);

        tvCa.setText(String.valueOf(fiche.getStatus().getClasseArmure()));
        tvVit.setText(String.valueOf(fiche.getStatus().getVitesse()));
        tvInit.setText(String.valueOf(fiche.getStatus().getInitiative()));
    }

    public void initArrayAdapter(){
        List<Attaque> attaques = fiche.getAttaques();
        attackArrayAdapter = new AttackArrayAdapter(this,
                R.id.lv_attaques,
                attaques);
        lvAttaques.setAdapter(attackArrayAdapter);
        lvAttaques.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Attaque","j'ai une attaque");
                final Attaque attaque = attaques.get(position);
                int attackRoll = DiceRoller.roll(20) + attaque.getBonusAuJet();
                int damageRoll = DiceRoller.roll(attaque.getDeDegat()) + attaque.getBonusAuDegat();

                Toast.makeText(bs,
                        "Jet pour toucher : "+
                                attackRoll +
                                "Jet de dégats : " +
                                damageRoll,
                        Toast.LENGTH_LONG);

            }
        });

    }

    public void initWallet(){
        tvCopper = (TextView) findViewById(R.id.tv_copperValue);
        tvSilver = (TextView) findViewById(R.id.tv_silverValue);
        tvElectrum = (TextView) findViewById(R.id.tv_electrumValue);
        tvGold = (TextView) findViewById(R.id.tv_goldValue);
        tvPlatinum = (TextView) findViewById(R.id.tv_platineValue);

        tvCopper.setText(String.valueOf(fiche.getWallet().getCuivre()));
        tvSilver.setText(String.valueOf(fiche.getWallet().getArgent()));
        tvElectrum.setText(String.valueOf(fiche.getWallet().getElectrum()));
        tvGold.setText(String.valueOf(fiche.getWallet().getOr()));
        tvPlatinum.setText(String.valueOf(fiche.getWallet().getPlatine()));
    }

    public void initBasicInfo(){
        //Pour le nom du personnage:
        tvNomPersonnage = (TextView) findViewById(R.id.tv_characterName);
        tvNomPersonnage.setText(fiche.getBasicInfo().getNomPersonnage());


        tvClasse = (TextView) findViewById(R.id.tv_classe);
        tvNiveau = (TextView) findViewById(R.id.tv_niveau);
        tvRace = (TextView) findViewById(R.id.tv_race);
        tvExp = (TextView) findViewById(R.id.tv_exp);

        tvClasse.setText(fiche.getBasicInfo().getClasse());
        tvNiveau.setText(String.valueOf(fiche.getBasicInfo().getNiveau()));
        tvRace.setText(fiche.getBasicInfo().getRace());
        tvExp.setText(String.valueOf(fiche.getBasicInfo().getExperience()));
    }


    public void modifyNumericValue(View view) {
        //On "active" le formulaire
        Intent intent = new Intent(BasicSheet.this, FormUserInputNumericActivity.class);
        int extra;

        switch (view.getId())
        {

            case R.id.im_forceEdit:
                startFormNumeric(R.id.tv_force,REQUEST_CODE_FORM_USERINPUT_FORCE,intent);
                break;
            case R.id.im_dextEditEdit:
                startFormNumeric(R.id.tv_dext,REQUEST_CODE_FORM_USERINPUT_DEXT,intent);
                break;
            case R.id.im_constEdit:
                startFormNumeric(R.id.tv_const,REQUEST_CODE_FORM_USERINPUT_CONST,intent);
                break;
            case R.id.im_intelEdit:
                startFormNumeric(R.id.tv_intel,REQUEST_CODE_FORM_USERINPUT_INTEL,intent);
                break;
            case R.id.im_wisdEdit:
                startFormNumeric(R.id.tv_wisd,REQUEST_CODE_FORM_USERINPUT_WISD,intent);
                break;
            case R.id.im_charEdit:
                startFormNumeric(R.id.tv_char,REQUEST_CODE_FORM_USERINPUT_CHAR,intent);
                break;
            case R.id.im_characterLevelEdit:
                startFormNumeric(R.id.tv_niveau,REQUEST_CODE_FORM_USERINPUT_CHARACTER_LEVEL,intent);
                break;
            case R.id.im_characterExpEdit:
                startFormNumeric(R.id.tv_exp,REQUEST_CODE_FORM_USERINPUT_CHARACTER_EXP,intent);
                break;
            case R.id.im_caEdit:
                startFormNumeric(R.id.tv_ca,REQUEST_CODE_FORM_USERINPUT_CA,intent);
                break;
            case R.id.im_initiativeEdit:
                startFormNumeric(R.id.tv_init,REQUEST_CODE_FORM_USERINPUT_INITIATIVE,intent);
                break;
            case R.id.im_vitesseEdit:
                startFormNumeric(R.id.tv_vit,REQUEST_CODE_FORM_USERINPUT_SPEED,intent);
                break;
            case R.id.im_hpMaxEdit:
                startFormNumeric(R.id.tv_pvMax,REQUEST_CODE_FORM_USERINPUT_HPMAX,intent);
                break;
            case R.id.im_hpCurrentEdit:
                startFormNumeric(R.id.tv_pvActuel,REQUEST_CODE_FORM_USERINPUT_HPCURRENT,intent);
                break;
            case R.id.im_hpTempEdit:
                startFormNumeric(R.id.tv_pvTemp,REQUEST_CODE_FORM_USERINPUT_HPTEMP,intent);
                break;
            case R.id.im_copperEdit:
                startFormNumeric(R.id.tv_copperValue,REQUEST_CODE_FORM_USERINPUT_COPPER,intent);
                break;
            case R.id.im_silverEdit:
                startFormNumeric(R.id.tv_silverValue,REQUEST_CODE_FORM_USERINPUT_SILVER,intent);
                break;
            case R.id.im_electrumEdit:
                startFormNumeric(R.id.tv_electrumValue,REQUEST_CODE_FORM_USERINPUT_ELECTRUM,intent);
                break;
            case R.id.im_goldEdit:
                startFormNumeric(R.id.tv_goldValue,REQUEST_CODE_FORM_USERINPUT_GOLD,intent);
                break;
            case R.id.im_platinedEdit:
                startFormNumeric(R.id.tv_platineValue,REQUEST_CODE_FORM_USERINPUT_PLATINE,intent);
                break;




        }
    }

    public void modifyStringValue(View view) {
        //On "active" le formulaire
        Intent intent = new Intent(BasicSheet.this, FormUserInputStringActivity.class);
        //Gestion des différents boutons :
        switch(view.getId()){
            case(R.id.im_masteriesEdit):
                startFormString(R.id.tv_masteries,REQUEST_CODE_FORM_USERINPUT_MASTERIES,intent);
                break;
            case(R.id.im_languagesEdit):
                startFormString(R.id.tv_language,REQUEST_CODE_FORM_USERINPUT_LANGUAGES,intent);
                break;
            case(R.id.im_characterNameEdit):
                startFormString(R.id.tv_characterName,REQUEST_CODE_FORM_USERINPUT_CHARACTER_NAME,intent);
                break;
            case(R.id.im_characterClassEdit):
                startFormString(R.id.tv_classe,REQUEST_CODE_FORM_USERINPUT_CHARACTER_CLASS,intent);
                break;
            case(R.id.im_characterRaceEdit):
                startFormString(R.id.tv_race,REQUEST_CODE_FORM_USERINPUT_CHARACTER_RACE,intent);
                break;

        }

    }

    private void startFormString(int idElement,int requestCode,Intent intent){
        //Ajout d'un extra permettant d'auto-compléter l'editbox avec les élément déjà présent
        String extra = ((TextView)findViewById(idElement)).getText().toString();
        intent.putExtra(AUTO_COMPLETE,extra);
        startActivityForResult(intent,requestCode);
    }


    private void startFormNumeric(int idElement,int requestCode,Intent intent){
        //Ajout d'un extra permettant d'auto-compléter l'editbox avec les élément déjà présent
        float extra = Float.parseFloat(((TextView)findViewById(idElement)).getText().toString());
        intent.putExtra(AUTO_COMPLETE,extra);
        startActivityForResult(intent,requestCode);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            final float reponseNum = data.getFloatExtra(FormUserInputNumericActivity.NUM_VALUE,(float)0);
            Log.i("Reponse","Retrieve userInput from FormUserInputActivity" + reponseNum);
            final String reponseStr = data.getStringExtra(FormUserInputStringActivity.STR_VALUE);
            Log.i("Reponse","Retrieve userInput from FormUserInputActivity" + reponseStr);

                switch (requestCode){
                    case REQUEST_CODE_FORM_USERINPUT_FORCE:
                        fiche.getCaracteristics().setForce((int)reponseNum);
                        initRoughtStat();
                        initModifier();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_DEXT:
                        fiche.getCaracteristics().setDexterite((int)reponseNum);
                        initRoughtStat();
                        initModifier();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_CONST:
                        fiche.getCaracteristics().setConstitution((int)reponseNum);
                        initRoughtStat();
                        initModifier();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_INTEL:
                        fiche.getCaracteristics().setIntelligence((int)reponseNum);
                        initRoughtStat();
                        initModifier();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_WISD:
                        fiche.getCaracteristics().setSagesse((int)reponseNum);
                        initRoughtStat();
                        initModifier();
                        //Recalcul de la PP
                        tvPP.setText(String.valueOf(ModifierCalculator.passivePerceptionCalculator(fiche.getBasicInfo().getNiveau(),fiche.getCaracteristics().getSagesse())));
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_CHAR:
                        fiche.getCaracteristics().setCharisme((int)reponseNum);
                        initRoughtStat();
                        initModifier();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_MASTERIES:
                        fiche.getMasteries().setMaitrises(reponseStr);
                        initLanguageAndMasteries();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_LANGUAGES:
                        fiche.getMasteries().setLangues(reponseStr);
                        initLanguageAndMasteries();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_CHARACTER_NAME:
                        fiche.getBasicInfo().setNomPersonnage(reponseStr);
                        initBasicInfo();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_CHARACTER_CLASS:
                        fiche.getBasicInfo().setClasse(reponseStr);
                        initBasicInfo();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_CHARACTER_RACE:
                        fiche.getBasicInfo().setRace(reponseStr);
                        initBasicInfo();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_CHARACTER_LEVEL:
                        fiche.getBasicInfo().setNiveau((int)reponseNum);
                        initBasicInfo();
                        tvBonusMaitrise.setText(String.valueOf(ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau())));
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_CHARACTER_EXP:
                        fiche.getBasicInfo().setExperience((int)reponseNum);
                        initBasicInfo();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_CA:
                        fiche.getStatus().setClasseArmure((int)(reponseNum));
                        initStatus();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_INITIATIVE:
                        fiche.getStatus().setInitiative((int)(reponseNum));
                        initStatus();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_SPEED:
                        fiche.getStatus().setVitesse(reponseNum);
                        initStatus();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_HPMAX:
                        fiche.getHpManager().setMaxHp((int)reponseNum);
                        initHp();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_HPCURRENT:
                        fiche.getHpManager().setCurrentHp(((int)reponseNum));
                        initHp();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_HPTEMP:
                        fiche.getHpManager().setTemporaryHp((int)reponseNum);
                        initHp();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_COPPER:
                        fiche.getWallet().setCuivre((int)reponseNum);
                        initWallet();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_SILVER:
                        fiche.getWallet().setArgent((int)reponseNum);
                        initWallet();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_ELECTRUM:
                        fiche.getWallet().setElectrum((int)reponseNum);
                        initWallet();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_GOLD:
                        fiche.getWallet().setOr((int)reponseNum);
                        initWallet();
                        break;
                    case REQUEST_CODE_FORM_USERINPUT_PLATINE:
                        fiche.getWallet().setPlatine((int)reponseNum);
                        initWallet();
                        break;
                    case REQUEST_CODE_FORM_ATTAQUE_ADD:
                        fiche.getAttaques().add((Attaque)data.getSerializableExtra("ATT_VALUE"));
                        initArrayAdapter();
                        break;
                    case REQUEST_CODE_FICHE:
                        fiche = (Fiche) data.getSerializableExtra("ficheObject");
                }

        }
        else{
            Toast.makeText(this,"Les modifications n'ont pas été enregistrées",Toast.LENGTH_LONG).show();
        }
    }


    public void submitToBd(View view) {
        FicheRepository ficheRepository = new FicheRepository();
        //Enregistres les élements variable en fin de session
        //Enregistrer l'état de l'inspiration
        fiche.setInspiration(cbInspiration.isChecked());

        saveSkill();
        saveSaveRoll();
        saveDeathRoll();

        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("User", MODE_APPEND);
        String token = sh.getString("token", "");

        ficheRepository.update(fiche,token);
    }


    public void saveSaveRoll(){
        SaveRollManager srm = new SaveRollManager(
        cbForceSave.isChecked(),
        cbDextSave.isChecked(),
        cbConstSave.isChecked(),
        cbIntelSave.isChecked(),
        cbWisdSave.isChecked(),
        cbCharSave.isChecked()
        );

        //fiche.setSaveRolls(srm);

    }

    public void saveSkill(){
        List<Competence> comp = new ArrayList<>();
        for(int i = 1;i<=18;i++){
            if(competences[i].isChecked()){
                comp.add(new Competence(i));
            }
        }
        fiche.setCompetences(comp);
    }

    public void saveDeathRoll(){
        int succes = 0;
        int fail = 0;

        for(int i =0;i<3;i++){
            if(cbSucces[i].isChecked()){
                succes++;
            }
            if (cbFail[i].isChecked()) {
                fail++;
            }

        }

        DeathRollManager drm = new DeathRollManager(
                succes,
                fail
        );
        fiche.setDeathRolls(drm);
    }


    public void addAttack(View view) {
        Intent intent = new Intent(BasicSheet.this, FormAttaque.class);
        startActivityForResult(intent,REQUEST_CODE_FORM_ATTAQUE_ADD);
    }

    public void rollForce(View view) {
        int diceResult = DiceRoller.roll(20);
        int modifier = Integer.parseInt((tvForce_mod.getText().toString()));
        Toast.makeText(this,"Résultat : "+diceResult+" + "+ modifier + " = "+(diceResult+modifier),Toast.LENGTH_LONG).show();
    }

    public void rollDext(View view) {
        int diceResult = DiceRoller.roll(20);
        int modifier = Integer.parseInt((tvDext_mod.getText().toString()));
        Toast.makeText(this,"Résultat : "+diceResult+" + "+ modifier + " = "+(diceResult+modifier),Toast.LENGTH_LONG).show();
    }

    public void rollConst(View view) {
        int diceResult = DiceRoller.roll(20);
        int modifier = Integer.parseInt((tvConst_mod.getText().toString()));
        Toast.makeText(this,"Résultat : "+diceResult+" + "+ modifier + " = "+(diceResult+modifier),Toast.LENGTH_LONG).show();
    }

    public void rollIntel(View view) {
        int diceResult = DiceRoller.roll(20);
        int modifier = Integer.parseInt((tvIntel_mod.getText().toString()));
        Toast.makeText(this,"Résultat : "+diceResult+" + "+ modifier + " = "+(diceResult+modifier),Toast.LENGTH_LONG).show();
    }

    public void rollWisd(View view) {
        int diceResult = DiceRoller.roll(20);
        int modifier = Integer.parseInt((tvWisd_mod.getText().toString()));
        Toast.makeText(this,"Résultat : "+diceResult+" + "+ modifier + " = "+(diceResult+modifier),Toast.LENGTH_LONG).show();
    }

    public void rollChar(View view) {
        int diceResult = DiceRoller.roll(20);
        int modifier = Integer.parseInt((tvChar_mod.getText().toString()));
        Toast.makeText(this,"Résultat : "+diceResult+" + "+ modifier + " = "+(diceResult+modifier),Toast.LENGTH_LONG).show();
    }

    public void rollSaveRoll(View view) {
        int diceResult;
        int modifier;
        int masteryBonus = 0;
        int total;


        switch (view.getId()){

            case R.id.im_rollSaveForce:
                if(cbForceSave.isChecked()){
                    diceResult = DiceRoller.roll(20);
                    modifier = Integer.parseInt((tvForce_mod.getText().toString()));
                    masteryBonus = ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau());
                    total = diceResult + modifier + masteryBonus;
                    Toast.makeText(this,"Résultat : "+diceResult+" + "+modifier+" + "+masteryBonus+" = "+total, Toast.LENGTH_SHORT).show();
                }
                else{
                    rollForce(view);
                }
                break;
            case R.id.im_rollSaveDext:
                if(cbDextSave.isChecked()){
                    diceResult = DiceRoller.roll(20);
                    modifier = Integer.parseInt((tvDext_mod.getText().toString()));
                    masteryBonus = ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau());
                    total = diceResult + modifier + masteryBonus;
                    Toast.makeText(this,"Résultat : "+diceResult+" + "+modifier+" + "+masteryBonus+" = "+total, Toast.LENGTH_SHORT).show();
                }
                else{
                    rollDext(view);
                }
                break;
            case R.id.im_rollSaveConst:
                if(cbConstSave.isChecked()){
                    diceResult = DiceRoller.roll(20);
                    modifier = Integer.parseInt((tvConst_mod.getText().toString()));
                    masteryBonus = ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau());
                    total = diceResult + modifier + masteryBonus;
                    Toast.makeText(this,"Résultat : "+diceResult+" + "+modifier+" + "+masteryBonus+" = "+total, Toast.LENGTH_SHORT).show();
                }
                else{
                    rollConst(view);
                }
                break;
            case R.id.im_rollSaveIntel:
                if(cbIntelSave.isChecked()){
                    diceResult = DiceRoller.roll(20);
                    modifier = Integer.parseInt((tvIntel_mod.getText().toString()));
                    masteryBonus = ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau());
                    total = diceResult + modifier + masteryBonus;
                    Toast.makeText(this,"Résultat : "+diceResult+" + "+modifier+" + "+masteryBonus+" = "+total, Toast.LENGTH_SHORT).show();
                }
                else{
                    rollIntel(view);
                }
                break;
            case R.id.im_rollSaveWisd:
                if(cbWisdSave.isChecked()){
                    diceResult = DiceRoller.roll(20);
                    modifier = Integer.parseInt((tvWisd_mod.getText().toString()));
                    masteryBonus = ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau());
                    total = diceResult + modifier + masteryBonus;
                    Toast.makeText(this,"Résultat : "+diceResult+" + "+modifier+" + "+masteryBonus+" = "+total, Toast.LENGTH_SHORT).show();
                }
                else{
                    rollWisd(view);
                }
                break;
            case R.id.im_rollSaveChar:
                if(cbCharSave.isChecked()){
                    diceResult = DiceRoller.roll(20);
                    modifier = Integer.parseInt((tvChar_mod.getText().toString()));
                    masteryBonus = ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau());
                    total = diceResult + modifier + masteryBonus;
                    Toast.makeText(this,"Résultat : "+diceResult+" + "+modifier+" + "+masteryBonus+" = "+total, Toast.LENGTH_SHORT).show();
                }
                else{
                    rollChar(view);
                }
                break;
        }
    }


    public void skillRoll(View view) {
        int diceResult = 0;
        int modifier = 0;
        int masteryBonus = ModifierCalculator.getMasteryBonus(fiche.getBasicInfo().getNiveau());
        int displayedMasteryBonus = 0;
        int total = 0;


        switch (view.getId()){

            case R.id.im_acrobatie:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getDexterite());
                total = diceResult + modifier;
                if(competences[1].isChecked()) {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_arcanes:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getIntelligence());
                total = diceResult + modifier;
                if(competences[2].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_athletisme:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getForce());
                total = diceResult + modifier;
                if(competences[3].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_discretion:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getDexterite());
                total = diceResult + modifier;
                if(competences[4].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_dressage:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getSagesse());
                total = diceResult + modifier;
                if(competences[5].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_escamatage:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getDexterite());
                total = diceResult + modifier;
                if(competences[6].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_histoire:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getIntelligence());
                total = diceResult + modifier;
                if(competences[7].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_intimidation:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getCharisme());
                total = diceResult + modifier;
                if(competences[8].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_investigation:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getIntelligence());
                total = diceResult + modifier;
                if(competences[9].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_medecine:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getIntelligence());
                total = diceResult + modifier;
                if(competences[10].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_nature:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getSagesse());
                total = diceResult + modifier;
                if(competences[11].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_perception:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getSagesse());
                total = diceResult + modifier;
                if(competences[12].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_perspicacite:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getSagesse());
                total = diceResult + modifier;
                if(competences[13].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_persuasion:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getCharisme());
                total = diceResult + modifier;
                if(competences[14].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_religion:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getIntelligence());
                total = diceResult + modifier;
                if(competences[15].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_representation:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getCharisme());
                total = diceResult + modifier;
                if(competences[16].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_survie:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getSagesse());
                total = diceResult + modifier;
                if(competences[17].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;

            case R.id.im_tromperie:
                diceResult = DiceRoller.roll(20);
                modifier = ModifierCalculator.getModifier(fiche.getCaracteristics().getCharisme());
                total = diceResult + modifier;
                if(competences[18].isChecked())
                {
                    total += masteryBonus;
                    displayedMasteryBonus = masteryBonus;
                }
                break;
        }
        Toast.makeText(this,"Résultat : "+diceResult+" + "+modifier+" + "+displayedMasteryBonus+" = "+total, Toast.LENGTH_SHORT).show();
    }

    public void rollLifeDice(View view) {
        Toast.makeText(this,String.valueOf(DiceRoller.roll(fiche.getHpManager().getHpDice())),Toast.LENGTH_LONG).show();
    }

    public void goToDetails(View view) {
        Intent intent = new Intent(this,DetailsSheet.class);
        intent.putExtra("ficheObject",fiche);
        startActivityForResult(intent,REQUEST_CODE_FICHE);

    }
}