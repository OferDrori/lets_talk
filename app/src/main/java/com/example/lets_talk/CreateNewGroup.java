package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.lets_talk.Keys.ENGLISH;
import static com.example.lets_talk.Keys.FRANCE;
import static com.example.lets_talk.Keys.HEBREW;
import static com.example.lets_talk.Keys.KEY_LANGUAGE;
import static com.example.lets_talk.Keys.KEY_LANGUAGE_ALL;
import static com.example.lets_talk.Keys.KEY_LEVEL;

public class CreateNewGroup extends AppCompatActivity {
    private TextView languageTextView;
    private TextView levelTextView;
    private ImageView flag;
    private Button makeNewGroup;
    private MySharedPreferences msp;
    private EditText groupName;
    private EditText description;
    private String languageName;
    private int level;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_new_group);
        findView();
        msp=new MySharedPreferences(this);
        languageName=msp.getString(KEY_LANGUAGE,"");
        level=msp.getInt(KEY_LEVEL,-1);
        makeWelomeInfo();
        makeNewGroup.setOnClickListener(makeNewGroupOnFireBace);



    }

    private void makeWelomeInfo() {
        if(languageName.equals(HEBREW))
        {
            languageTextView.setText("Hebrew");
            flag.setImageResource(R.drawable.israel_flag);
        }
        if(languageName.equals(ENGLISH))
        {
            languageTextView.setText("English");
            flag.setImageResource(R.drawable.usa_flag);
        }
        if(languageName.equals(FRANCE))
        {
            languageTextView.setText("France");

            flag.setImageResource(R.drawable.france_flag);
        }
        levelTextView.setText("Level "+level);

    }

    View.OnClickListener makeNewGroupOnFireBace = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!(groupName.getText().toString().equals("")||description.getText().toString().equals(""))) {
                GruopOfMassages temp= new GruopOfMassages(groupName.getText().toString(),description.getText().toString());
                myRef.child("language").child(languageName).child(level+"").child(temp.getName()).setValue(temp);
                Intent intent = new Intent(CreateNewGroup.this, GroupsScreen.class);
                startActivity(intent);
            }

        }
    };



    private void findView() {
        languageTextView=findViewById(R.id.createNewGroup_Language_textView);
        levelTextView=findViewById(R.id.createNewGroup_level_textView);
        flag=findViewById(R.id.createNewGroup_img_flag);
        makeNewGroup=findViewById(R.id.createNewGroup_btn_create);
        groupName=findViewById(R.id.createNewGroup_GroupName_editText);
        description=findViewById(R.id.createNewGroup_GroupInfo_editText);

    }


}
