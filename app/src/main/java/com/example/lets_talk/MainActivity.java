package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;

import static com.example.lets_talk.Keys.KEY_CONVERSATION_TOPIC;
import static com.example.lets_talk.Keys.KEY_USER_PROFILE;
import static com.example.lets_talk.Keys.connectedName;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> temp = new ArrayList<>();
    private TextView register;
    private EditText pass;
    private EditText userName;
    private Button login;
    private MySharedPreferences msp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        findViews();
        msp=new MySharedPreferences(this);
        msp.removeKey(KEY_USER_PROFILE);
        ArrayList<ConversationTopic> conversationTopics=new ArrayList<>();
        addTopics(conversationTopics);
        Gson gson=new Gson();
        msp.putString(KEY_CONVERSATION_TOPIC,gson.toJson(conversationTopics));
        MyFirebase.getUsers(new CallBack_UsersReady() {
            @Override
            public void usersReady(ArrayList<User> users) {
                refreshListUsers(users);

            }

            @Override
            public void error() {
                // TODO: 2020-01-08 handle errors
            }

        });


    }

    private void addTopics(ArrayList<ConversationTopic> conversationTopics) {
        ConversationTopic topic=new ConversationTopic("science","global warming");
        conversationTopics.add(topic);
        topic.setTopic("Animals");
        conversationTopics.add(topic);
        topic.setTopic("physics");
        conversationTopics.add(topic);
        topic.setSubject("World");
        topic.setTopic("Travels");
        conversationTopics.add(topic);
        topic.setSubject("World");
        topic.setTopic("Afrika");
        conversationTopics.add(topic);

    }


    private void refreshListUsers(ArrayList<User> users) {

        for (User var : users) {
            temp.add(var);

        }
        // TODO: 12/01/2020 do refresh List
    }

    View.OnClickListener goToRegisterActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goToRegisterActivity();
        }
    };


    private void goToRegisterActivity() {
        Intent intent = new Intent(MainActivity.this, RegitrationActivity.class);
        startActivity(intent);

    }
    View.OnClickListener goToChooseLanguagesScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (checkUser()) {
                Context context = getApplicationContext();
                CharSequence text = "good!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent intent = new Intent(MainActivity.this, ChooseLanguages.class);
                startActivity(intent);
            } else {
                Context context = getApplicationContext();
                CharSequence text = "try again!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }
    };

    boolean checkUser() {
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getEmail().equals(userName.getText().toString())&&temp.get(i).getPassword().equals(pass.getText().toString())) {
                Gson gson = new Gson();
                connectedName=temp.get(i).getFirstName();
                msp.putString(KEY_USER_PROFILE, gson.toJson(temp.get(i)));
                return true;
            }

        }
        return false;

    }


    private void findViews() {
        pass = findViewById(R.id.pass_main_editText);
        userName = findViewById(R.id.name_main_editText);
        register = findViewById(R.id.joinActivity_main_activity_text_view);
        login = findViewById(R.id.login_btn);
        login.setOnClickListener(goToChooseLanguagesScreen);
        register.setOnClickListener(goToRegisterActivity);

    }

}

