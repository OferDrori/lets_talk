package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.File;

import static com.example.lets_talk.Keys.ADVANCED_LEVEL;
import static com.example.lets_talk.Keys.BEGINING_LEVEL;
import static com.example.lets_talk.Keys.ENGLISH;
import static com.example.lets_talk.Keys.FRANCE;
import static com.example.lets_talk.Keys.HEBREW;
import static com.example.lets_talk.Keys.KEY_LANGUAGE;
import static com.example.lets_talk.Keys.KEY_LEVEL;
import static com.example.lets_talk.Keys.KEY_USER_PROFILE;
import static com.example.lets_talk.Keys.MEDIUM_LEVEL;
import static com.example.lets_talk.Keys.connectedName;

public class ChooseLevel extends AppCompatActivity {


    private Button btnBeginner;
    private Button btnIntermediate;
    private Button btnAdvanced;
    private MySharedPreferences msp;
    private User userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_level);
        findView();
        btnBeginner.setOnClickListener(continueToGoupScreenBeginner);
        btnIntermediate.setOnClickListener(continueToGoupScreenIntermediate);
        btnAdvanced.setOnClickListener(continueToGoupScreenAdvanced);
        msp=new MySharedPreferences(this);

        //add user info into share pref

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("User").child("Israel").child(user.getUid());
        rootRef.addListenerForSingleValueEvent(valueEventListener);



    }
    ValueEventListener valueEventListener = new ValueEventListener()
    {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot)
        {
            userProfile = dataSnapshot.getValue(User.class);
            Gson gson=new Gson();
            msp.putString(KEY_USER_PROFILE,gson.toJson(userProfile));
            connectedName=userProfile.getfullName();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };



    View.OnClickListener continueToGoupScreenBeginner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msp.putInt(KEY_LEVEL, BEGINING_LEVEL);
            Intent next = new Intent(getApplicationContext(), GroupsScreen.class);
            startActivity(next);
            finish();

        }

    };
    View.OnClickListener continueToGoupScreenAdvanced = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msp.putInt(KEY_LEVEL, ADVANCED_LEVEL);
            Intent next = new Intent(getApplicationContext(), GroupsScreen.class);
            startActivity(next);


        }

    };
    View.OnClickListener continueToGoupScreenIntermediate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msp.putInt(KEY_LEVEL, MEDIUM_LEVEL);
            Intent next = new Intent(getApplicationContext(), GroupsScreen.class);
            startActivity(next);
            finish();

        }

    };
    private void findView() {
        btnBeginner =findViewById(R.id.chooseLevel_btn_Beginner);
        btnIntermediate =findViewById(R.id.chooseLevel_btn_Intermediate);
        btnAdvanced =findViewById(R.id.chooseLevel_btn_Advanced);
    }
}