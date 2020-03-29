package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Random;
import static com.example.lets_talk.Keys.KEY_CONVERSATION_TOPIC;
import static com.example.lets_talk.Keys.KEY_GROUP_NAME;
import static com.example.lets_talk.Keys.KEY_LANGUAGE;
import static com.example.lets_talk.Keys.KEY_LEVEL;
import static com.example.lets_talk.Keys.KEY_USER_PROFILE;
public class MassagesScreen extends AppCompatActivity {
    private ListView listView;
    private TextView subject;
    private  TextView groupNameTextView;
    ArrayList<Message> arrayListOfMassages = new ArrayList<>();
    MyAdapterForMassages MassageAdapter;
    private MySharedPreferences msp;
    private User user;
    private ImageView send;
    private ImageView backTogroupScreen;
    private EditText massageEditText;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private String groupName;
    private String languageName;
    private String level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_massages_screen);
        findViews();
        MassageAdapter=new MyAdapterForMassages(this,arrayListOfMassages);
        listView.setAdapter(MassageAdapter);
        msp = new MySharedPreferences(this);
        send.setOnClickListener(sendMassage);
        Gson gson = new Gson();
        ArrayList<ConversationTopic> conversationTopicsgson=gson.fromJson(msp.getString(KEY_CONVERSATION_TOPIC, ""), new TypeToken<ArrayList<ConversationTopic>>() {
        }.getType());
        randomASubject(conversationTopicsgson);
        user = gson.fromJson(msp.getString(KEY_USER_PROFILE, ""), new TypeToken<User>() {
        }.getType());
        groupName = msp.getString(KEY_GROUP_NAME, "");
        groupNameTextView.setText(groupName);
        level = msp.getInt(KEY_LEVEL, 0) + "";
        languageName = msp.getString(KEY_LANGUAGE, "");
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tripsRef = rootRef.child("language").child(languageName).child(level).child(groupName).child("messages");
        tripsRef.addValueEventListener(valueEventListener);
        backTogroupScreen.setOnClickListener(goBackToGruopScreen);
    }

    private void randomASubject(ArrayList<ConversationTopic> conversationTopicsgson) {
        int index = new Random().nextInt(conversationTopicsgson.size());
        subject.setText(conversationTopicsgson.get(index).getTopic());
    }

    private void findViews() {
        subject=findViewById(R.id.massagesScreen_textView_TodayTopic);
        listView = findViewById(R.id.massagesScreen_Messages_ListView);
        groupNameTextView = findViewById(R.id.massagesScreen_textView_groupName);
        send = findViewById(R.id.massagesScreen_img_sent);
        massageEditText = findViewById(R.id.massagesScreen_addNewMassage_EditText);
        backTogroupScreen=findViewById(R.id.massagesScreen_img_back);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ArrayList<Message> list = new ArrayList<>();

            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                Message arrival = ds.getValue(Message.class);
                list.add(arrival);
            }
             refreshListOfMessages(list);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };
    View.OnClickListener sendMassage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!massageEditText.getText().toString().equals("")) {
                Message temp=new Message(System.currentTimeMillis(), massageEditText.getText().toString(),user.getId(), 22, 1, user.getfullName(),user.getPhotoPath());
                saveMassageOnFirebace(temp);
                massageEditText.setText("");
            }

        }
    };


    View.OnClickListener goBackToGruopScreen= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MassagesScreen.this, GroupsScreen.class);
            startActivity(intent);
            finish();
        }
    };


    void saveMassageOnFirebace(Message temp){
        groupName=msp.getString(KEY_GROUP_NAME,"");
        level=msp.getInt(KEY_LEVEL,0)+"";
        languageName=msp.getString(KEY_LANGUAGE,"");
        myRef.child("language").child(languageName).child(level).child(groupName).child("messages").push().setValue(temp);
    }

    void refreshListOfMessages(ArrayList<Message> messages) {
        arrayListOfMassages.clear();
        for (Message var : messages) {
            arrayListOfMassages.add(var);
            listView.setAdapter(MassageAdapter);
        }
    }

}

