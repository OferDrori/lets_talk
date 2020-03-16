package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.lets_talk.Keys.ENGLISH;
import static com.example.lets_talk.Keys.FRANCE;
import static com.example.lets_talk.Keys.HEBREW;
import static com.example.lets_talk.Keys.KEY_LANGUAGE;
import static com.example.lets_talk.Keys.KEY_LEVEL;

public class ChooseLanguages extends AppCompatActivity {


    private Button btnEnglish;
    private Button btnHebrew;
    private Button btnFrance;
    private MySharedPreferences msp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_languages);
        findView();
        btnEnglish.setOnClickListener(continueToLevelScreenEnglish);
        btnHebrew.setOnClickListener(continueToLevelScreenbtnHebrew);
        btnFrance.setOnClickListener(continueToLevelScreenFrance);
        msp=new MySharedPreferences(this);
    }
    View.OnClickListener continueToLevelScreenEnglish = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                msp.putString(KEY_LANGUAGE, ENGLISH);
                Intent next = new Intent(getApplicationContext(), ChooseLevel.class);
                startActivity(next);


            }

    };
    View.OnClickListener continueToLevelScreenbtnHebrew = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msp.putString(KEY_LANGUAGE, HEBREW);
            Intent next = new Intent(getApplicationContext(), ChooseLevel.class);
            startActivity(next);
            finish();

        }

    };
    View.OnClickListener continueToLevelScreenFrance = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msp.putString(KEY_LANGUAGE, FRANCE);
            Intent next = new Intent(getApplicationContext(), ChooseLevel.class);
            startActivity(next);
            finish();

        }

    };
    private void findView() {
        btnEnglish=findViewById(R.id.chooseLanguages_btn_english);
        btnHebrew=findViewById(R.id.chooseLanguages_btn_hebrew);
        btnFrance=findViewById(R.id.chooseLanguages_btn_france);
    }
}
