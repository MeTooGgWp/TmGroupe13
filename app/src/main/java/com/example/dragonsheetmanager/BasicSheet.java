package com.example.dragonsheetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import model.Attaque;
import model.Fiche;
import model.utilities.DiceRoller;
import model.utilities.ModifierCalculator;

public class BasicSheet extends AppCompatActivity {

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
    private TextView tvDv,tvNbDv;


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
        //Pour le nom du personnage:
        tvNomPersonnage = (TextView) findViewById(R.id.tv_characterName);
        tvNomPersonnage.setText(fiche.getBasicInfo().getNomPersonnage());
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
        tvNbDv = (TextView) findViewById(R.id.tv_nbDv);

        tvDv.setText("Type dé : d"+String.valueOf(fiche.getHpManager().getHpDice()));
        tvNbDv.setText("Restant : "+String.valueOf(fiche.getBasicInfo().getNiveau()));
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
                final Attaque attaque = attaques.get(position);
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
        tvClasse = (TextView) findViewById(R.id.tv_classe);
        tvNiveau = (TextView) findViewById(R.id.tv_niveau);
        tvRace = (TextView) findViewById(R.id.tv_race);
        tvExp = (TextView) findViewById(R.id.tv_exp);

        tvClasse.setText(fiche.getBasicInfo().getClasse());
        tvNiveau.setText(String.valueOf(fiche.getBasicInfo().getNiveau()));
        tvRace.setText(fiche.getBasicInfo().getRace());
        tvExp.setText(String.valueOf(fiche.getBasicInfo().getExperience()));
    }


}