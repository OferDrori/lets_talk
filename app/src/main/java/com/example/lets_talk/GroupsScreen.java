package com.example.lets_talk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.lets_talk.Keys.ENGLISH;
import static com.example.lets_talk.Keys.FRANCE;
import static com.example.lets_talk.Keys.HEBREW;
import static com.example.lets_talk.Keys.KEY_GROUP_NAME;
import static com.example.lets_talk.Keys.KEY_LANGUAGE;
import static com.example.lets_talk.Keys.KEY_LANGUAGE_ALL;
import static com.example.lets_talk.Keys.KEY_LEVEL;

public class GroupsScreen extends AppCompatActivity {
    private TextView welcome;
    private TextView levelTextView;
    private ListView groupsListView;
    private ImageView flag;
    private TextView addNewGroup;
    private ArrayList<GruopOfMassages> arrayListOfGruops = new ArrayList<>();
    private AdapterForGroupList adapter;
    private MySharedPreferences msp;
    private User user;
    private String languageName;
    private int level;
    ArrayList<LanguageGroups> languageGroups=new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_groups_screen);
        findView();
        adapter = new AdapterForGroupList(this, arrayListOfGruops);
        msp=new MySharedPreferences(this);
        languageName=msp.getString(KEY_LANGUAGE,"");
        level=msp.getInt(KEY_LEVEL,-1);
        groupsListView.setAdapter(adapter);
        makeWelomeInfo();
        Gson gson = new Gson();
        languageGroups = gson.fromJson(msp.getString(KEY_LANGUAGE_ALL, ""), new TypeToken<ArrayList<LanguageGroups>>() {
        }.getType());
        groupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Toast.makeText(getApplicationContext(), "Hello "+i, Toast.LENGTH_LONG).show();
                msp.putString(KEY_GROUP_NAME,arrayListOfGruops.get(i).getName());
                Intent intent = new Intent(GroupsScreen.this, MassagesScreen.class);
                startActivity(intent);

            }
        });


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tripsRef = rootRef.child("language").child(languageName).child(level+"");
        tripsRef.addListenerForSingleValueEvent(valueEventListener);

        addNewGroup.setOnClickListener(goToCreateNewGroup);


    }

    private void findView() {
        welcome=findViewById(R.id.groupsScreen_welcome_txt);
        flag=findViewById(R.id.groupsScreen_flag);
        addNewGroup=findViewById(R.id.groupsScreen_addNewGroup_txt);
        groupsListView=findViewById(R.id.listView_groupScreen);
        levelTextView=findViewById(R.id.groupsScreen_level);

    }




    View.OnClickListener goToCreateNewGroup = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(GroupsScreen.this, CreateNewGroup.class);
            startActivity(intent);


            }


    };

    private void makeWelomeInfo() {
        if(languageName.equals(HEBREW))
        {
            welcome.setText("Let’s Speak Hebrew ");
            flag.setImageResource(R.drawable.israel_flag);
        }
        if(languageName.equals(ENGLISH))
        {
            welcome.setText("Let’s Speak English ");
            flag.setImageResource(R.drawable.usa_flag);
        }
        if(languageName.equals(FRANCE))
        {
            welcome.setText("Let’s Speak France ");

            flag.setImageResource(R.drawable.france_flag);
        }
        levelTextView.setText("Level "+level);

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ArrayList<GruopOfMassages> list = new ArrayList<>();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                GruopOfMassages arrival = ds.getValue(GruopOfMassages.class);
                Log.d("pttt", "B - Number of mesages " + arrival.getName());
                list.add(arrival);
            }
            refreshListOfMessages(list);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };

        void refreshListOfMessages(ArrayList<GruopOfMassages> messages) {

            for (GruopOfMassages var : messages) {
                arrayListOfGruops.add(var);
                groupsListView.setAdapter(adapter);
            }
        }
}
