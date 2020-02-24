package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.lets_talk.Keys.KEY_USER_PROFILE;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<User> temp = new ArrayList<>();
    private TextView register;
    private EditText pass;
    private EditText userName;
    private Button login;
    private MySharedPreferences msp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        Log.d("pttt", "A - Number of users: " + users.size());
        pass = findViewById(R.id.pass_main_editText);
        userName = findViewById(R.id.name_main_editText);
        register = findViewById(R.id.joinActivity_main_activity_text_view);
        login = findViewById(R.id.login_btn);
        login.setOnClickListener(goToMassegesScreen);
        register.setOnClickListener(goToRegisterActivity);
        msp=new MySharedPreferences(this);
        msp.removeKey(KEY_USER_PROFILE);

        MyFirebase.getUsers(new CallBack_UsersReady() {
            @Override
            public void usersReady(ArrayList<User> users) {
                refreshList(users);
                Log.d("pttt", "C - Number of users: " + users.size());


            }

            @Override
            public void error() {
                // TODO: 2020-01-08 handle errors
            }

        });

        Log.d("pttt", "B - Number of users: " + users.size());

        User temp = new User();
        User user = new User("Gadi", "choen", "gadi", "Petah-Tikva", 2, "112233","student");
        myRef.child("Users").child("Israel").child("" + user.getId()).setValue(user);


        User user2 = new User("Tomer", "choen", "tomer@gmail.com", "Petah-Tikva", 3, "1122","student of math");
        myRef.child("Users").child("Israel").child("" + user2.getId()).setValue(user2);

        Message message = new Message();

        message = new Message()
                .setTimeStamp(System.currentTimeMillis())
                .setStatus(0)
                .setSender(user.getId())
                .setReceiver(user2.getId())
                .setContent("Hi Tomer4!");


        myRef.child("Chats").child(user.getId() + "-" + user2.getId()).push().setValue(message);

    }

    private void refreshList(ArrayList<User> users) {

        for (User var : users) {
            Log.d("pttt", "B - Number of users: " + var.toString());
            Log.d("pttt", "B - Number of users: " + var.getEmail());
            Log.d("pttt", "B - Number of users: " + var.getPassword());
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

    View.OnClickListener goToMassegesScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("pttt", "check1");
            if (checkUser() == true) {

                Context context = getApplicationContext();
                CharSequence text = "good!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(MainActivity.this, MassagesScreen.class);
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

        for (User var : temp) {
            Log.d("pttt", "B -s: " + var.toString());
            Log.d("pttt", "B -: " + var.getEmail());
            Log.d("pttt", "B - : " + var.getPassword());
        }
        Log.d("pttt", "check");
        for (int i = 0; i < temp.size(); i++) {
            Log.d("pttt", temp.get(i).getEmail());
            Log.d("pttt", temp.get(i).getPassword());
            Log.d("pttt", userName.getText().toString());
            if (temp.get(i).getEmail().equals(userName.getText().toString())&&temp.get(i).getPassword().equals(pass.getText().toString())) {
                Gson gson = new Gson();
                msp.putString(KEY_USER_PROFILE, gson.toJson(temp.get(i)));
                return true;
            }

        }
        return false;

    }

}

