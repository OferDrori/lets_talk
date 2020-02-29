package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import static com.example.lets_talk.Keys.ADVANCED_LEVEL;
import static com.example.lets_talk.Keys.BEGINING_LEVEL;
import static com.example.lets_talk.Keys.ENGLISH;
import static com.example.lets_talk.Keys.FRANCE;
import static com.example.lets_talk.Keys.HEBREW;
import static com.example.lets_talk.Keys.KEY_LANGUAGE;
import static com.example.lets_talk.Keys.KEY_LEVEL;
import static com.example.lets_talk.Keys.MEDIUM_LEVEL;


public class ChooseLanguage extends AppCompatActivity {
    private ImageView exsit;
    private Button btnStart;
    private RadioGroup menu_RDG_lunguge, menu_RDG_level;
    private MySharedPreferences msp;
    private String language=HEBREW;
    private int level=BEGINING_LEVEL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        btnStart = findViewById(R.id.chooseLanguage_btn_start);
        menu_RDG_lunguge = findViewById(R.id.chooseLanguage_RDG_language);
        menu_RDG_level = findViewById(R.id.menu_RDG_level);
        btnStart.setOnClickListener(continueToGroupScreen);
        msp=new MySharedPreferences(this);
        menu_RDG_lunguge.check(R.id.chooseLanguage_RDB_hebrew);
        menu_RDG_level.check(R.id.chooseLanguage_RDB_beginners);
        exsit=findViewById(R.id.exsit);
        exsit.setOnClickListener(goBack);


        //RadioGruou listener
        menu_RDG_lunguge.setOnCheckedChangeListener(lungugeConfiguration);
        menu_RDG_level.setOnCheckedChangeListener(levelConfiguration);
    }

    RadioGroup.OnCheckedChangeListener lungugeConfiguration = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.chooseLanguage_RDB_hebrew:
                    language =HEBREW;
                    // do operations specific to this selection
                    break;
                case R.id.chooseLanguage_RDB_france:
                    language =FRANCE;
                    // do operations specific to this selection
                    break;
                case R.id.chooseLanguage_RDB_english:
                    language = ENGLISH;
                    // do operations specific to this selection
                    break;
            }
        }
    };


    RadioGroup.OnCheckedChangeListener levelConfiguration = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.chooseLanguage_RDB_beginners:
                   level=BEGINING_LEVEL;
                    // do operations specific to this selection
                    break;
                case R.id.chooseLanguage_RDB_mdiumLavel:
                    level=MEDIUM_LEVEL;
                    break;
                case R.id.chooseLanguage_RDB_Advanced:
                    level=ADVANCED_LEVEL;
                    break;
            }
        }
    };
    View.OnClickListener continueToGroupScreen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if((level!=0)&&(!language.equals(" "))) {
                msp.putString(KEY_LANGUAGE, language);
                msp.putInt(KEY_LEVEL, level);
                Intent next = new Intent(getApplicationContext(), GroupScreen.class);
                startActivity(next);
                finish();
            }
        }
    };
    View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                finish();
            }

    };



    }

