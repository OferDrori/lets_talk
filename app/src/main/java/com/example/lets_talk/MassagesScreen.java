package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.lets_talk.Keys.KEY_GROUP_NAME;
import static com.example.lets_talk.Keys.KEY_LANGUAGE;
import static com.example.lets_talk.Keys.KEY_LEVEL;
import static com.example.lets_talk.Keys.KEY_USER_PROFILE;

public class MassagesScreen extends AppCompatActivity {
    ListView listView;
    TextView welcome;
    TextView groupNameTextView;
    ImageView profile;
    ArrayList<Message> arrayList = new ArrayList<>();
    ArrayList<Message> tempArrayForNewMessages = new ArrayList<>();
    MyAdapter adapter;
    private MySharedPreferences msp;
    private User user;
    private Button send;
    private EditText massage;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private String groupName;
    private String languageName;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massages_screen);
        listView = findViewById(R.id.listView_massages);
        welcome = findViewById(R.id.welcome_txt_massages_screen);
        groupNameTextView = findViewById(R.id.group_name_text_view_massages_screen);
        send = findViewById(R.id.send_btn_massagesScreen);
        massage = findViewById(R.id.massage_edit_text);
        adapter = new MyAdapter(this, arrayList);
        listView.setAdapter(adapter);
        msp = new MySharedPreferences(this);
        profile = findViewById(R.id.gotoProfile_imageView_massagesScreen);
        profile.setOnClickListener(goToProfile);
        send.setOnClickListener(sendMassage);
        Gson gson = new Gson();
        user = gson.fromJson(msp.getString(KEY_USER_PROFILE, ""), new TypeToken<User>() {
        }.getType());
        welcome.setText("welcome  " + user.getFirstName());
        Log.d("ggg", user.getEmail());
        groupNameTextView.setText(msp.getString(KEY_GROUP_NAME, ""));


        groupName = msp.getString(KEY_GROUP_NAME, "");
        level = msp.getInt(KEY_LEVEL, 0) + "";
        languageName = msp.getString(KEY_LANGUAGE, "");



        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tripsRef = rootRef.child("language").child(languageName).child(level).child(groupName).child("messages");
        tripsRef.addListenerForSingleValueEvent(valueEventListener);



    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ArrayList<Message> list = new ArrayList<>();
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                Message arrival = ds.getValue(Message.class);
                Log.d("pttt", "B - Number of mesages " + arrival.getSenderName());
                list.add(arrival);
            }
             refreshListOfMessages(list);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };







    View.OnClickListener goToProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent next = new Intent(getApplicationContext(), Profile.class);
            startActivity(next);
            finish();
        }
    };

    View.OnClickListener sendMassage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!massage.getText().toString().equals("")) {
//                listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                Message temp=new Message(System.currentTimeMillis(), massage.getText().toString(),user.getId(), 22, 1, user.getFirstName());
                arrayList.add(temp);
                saveMassageOnFirebace(temp);
                listView.setAdapter(adapter);
            }

        }
    };

    void saveMassageOnFirebace(Message temp){
        groupName=msp.getString(KEY_GROUP_NAME,"");
        level=msp.getInt(KEY_LEVEL,0)+"";
        languageName=msp.getString(KEY_LANGUAGE,"");
        myRef.child("language").child(languageName).child(level).child(groupName).child("messages").push().setValue(temp);
    }

    void refreshListOfMessages(ArrayList<Message> messages) {

        for (Message var : messages) {
            arrayList.add(var);
            Log.d("pttt", "B - Number of mesages " + var.getSenderName());
//            Log.d("pttt", "B - Number of users: " + var.getEmail());
//            Log.d("pttt", "B - Number of users: " + var.getPassword());
            listView.setAdapter(adapter);
        }
    }
}

