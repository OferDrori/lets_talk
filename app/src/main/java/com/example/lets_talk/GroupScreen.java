package com.example.lets_talk;

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

import java.security.acl.Group;
import java.util.ArrayList;

import static com.example.lets_talk.Keys.ENGLISH;
import static com.example.lets_talk.Keys.FRANCE;
import static com.example.lets_talk.Keys.HEBREW;
import static com.example.lets_talk.Keys.KEY_GROUP_NAME;
import static com.example.lets_talk.Keys.KEY_LANGUAGE;
import static com.example.lets_talk.Keys.KEY_LANGUAGE_ALL;
import static com.example.lets_talk.Keys.KEY_LEVEL;
import static com.example.lets_talk.Keys.KEY_USER_PROFILE;

public class GroupScreen extends AppCompatActivity {
    ListView listView;
    TextView welcome;
    ImageView flag;
   private ArrayList<GruopOfMassages> arrayListOfGruops = new ArrayList<>();
    AdapterForGroupList adapter;
    private MySharedPreferences msp;
    private User user;
    private Button makeNewGroup;
    private EditText groupName;
    private String languageName;
    private int level;
    ArrayList<LanguageGroups> languageGroups=new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_screen);
        flag=findViewById(R.id.flag_img_groupScreen);
        groupName=findViewById(R.id.groupName_edit_text_groupScreen);
        makeNewGroup=findViewById(R.id.makeNewGroup_btn_groupScreen);
        makeNewGroup.setOnClickListener(makeNewGroupInlist);
        listView = findViewById(R.id.listView_groupScreen);
        welcome=findViewById(R.id.language_textView_groupScreen);
        adapter = new AdapterForGroupList(this, arrayListOfGruops);
        msp=new MySharedPreferences(this);
        languageName=msp.getString(KEY_LANGUAGE,"");
        level=msp.getInt(KEY_LEVEL,-1);
        listView.setAdapter(adapter);
        makeFlagPic();
        Gson gson = new Gson();
        languageGroups = gson.fromJson(msp.getString(KEY_LANGUAGE_ALL, ""), new TypeToken<ArrayList<LanguageGroups>>() {
        }.getType());
//        Log.d("pttt", languageGroups.get(0).getLanguage());
        //makeGroupList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "Hello "+i, Toast.LENGTH_LONG).show();
                msp.putString(KEY_GROUP_NAME,arrayListOfGruops.get(i).getName());
                Intent intent = new Intent(GroupScreen.this, MassagesScreen.class);
                startActivity(intent);
                finish();
            }
        });


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tripsRef = rootRef.child("language").child(languageName).child(level+"");
        tripsRef.addListenerForSingleValueEvent(valueEventListener);



    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ArrayList<GruopOfMassages> list = new ArrayList<>();
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                GruopOfMassages arrival = ds.getValue(GruopOfMassages.class);
                Log.d("pttt", "B - Number of mesages " + arrival.getName());
                list.add(arrival);
            }
            refreshListOfMessages(list);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };



    private void makeGroupList() {
        for (int i = 0; i < languageGroups.size(); i++) {
            Log.d("pttt", languageGroups.get(i).getLanguage());
            Log.d("pttt", languageGroups.get(i).getLevel() + "");
            Log.d("pttt", languageName + " ch");
            Log.d("pttt", level + "ch");
            if ((languageGroups.get(i).getLanguage().equals(languageName)) && (languageGroups.get(i).getLevel() == level)) {
                Log.d("pttt", "good");
                for (int j = 0; j < languageGroups.get(i).getGruopOfMassages().size(); j++) {
                    Log.d("pttt", "yes");
                    GruopOfMassages temp = languageGroups.get(i).getGruopOfMassages().get(j);
                    arrayListOfGruops.add(temp);
                    listView.setAdapter(adapter);
                }
            }
        }
    }


    private void makeFlagPic() {
        if(languageName.equals(HEBREW))
        {
            welcome.setText("Speak in Hebrew in level "+msp.getInt(KEY_LEVEL,-1));
            flag.setImageResource(R.drawable.israel_flag);
        }
        if(languageName.equals(ENGLISH))
        {
            welcome.setText("Speak in English in level "+msp.getInt(KEY_LEVEL,-1));
            flag.setImageResource(R.drawable.usa_flag);
        }
        if(languageName.equals(FRANCE))
        {
            welcome.setText("Speak in France in level "+msp.getInt(KEY_LEVEL,-1));
            flag.setImageResource(R.drawable.france_flag);
        }

    }


    View.OnClickListener makeNewGroupInlist = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!groupName.getText().toString().equals("")) {
                GruopOfMassages temp= new GruopOfMassages(groupName.getText().toString(),5);
                arrayListOfGruops.add(temp);
                myRef.child("language").child(languageName).child(level+"").child(temp.getName()).setValue(temp);
                listView.setAdapter(adapter);

            }

        }
    };

    void refreshListOfMessages(ArrayList<GruopOfMassages> messages) {

        for (GruopOfMassages var : messages) {
            arrayListOfGruops.add(var);
            Log.d("pttt", "B - Number of mesages " + var.getName());
//            Log.d("pttt", "B - Number of users: " + var.getEmail());
//            Log.d("pttt", "B - Number of users: " + var.getPassword());
            listView.setAdapter(adapter);
        }
    }

}
