package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> users = new ArrayList<>();
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        Log.d("pttt", "A - Number of users: " + users.size());
        register=findViewById(R.id.joinActivity_main_activity_text_view);
        register.setOnClickListener(goToRegisterActivity);
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
        User user = new User("Gadi", "choen", "gadi@gmail.com","Petah-Tikva",2);
        myRef.child("Users").child("Israel").child("Petah-Tikva").child("" + user.getId()).setValue(user);


        User user2 = new User("Tomer", "choen", "tomer@gmail.com","Petah-Tikva",3);
        myRef.child("Users").child("Israel").child("Ramat-Gan").child("" + user2.getId()).setValue(user2);

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
        // TODO: 12/01/2020 do refresh List
    }


    View.OnClickListener goToRegisterActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            goToRegisterActivity();
        }
    };


    private  void goToRegisterActivity()
    {
        Intent intent = new Intent(MainActivity.this, RegitrationActivity.class);
        startActivity(intent);

    }
}

