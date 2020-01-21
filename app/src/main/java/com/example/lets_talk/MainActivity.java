package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        Log.d("pttt", "A - Number of users: " + users.size());

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
        User user = new User("Gadi", 111111666, false);
        myRef.child("Users").child("Yeman").child("Petah-Tikva").child("" + user.getId()).setValue(user);


        User user2 = new User("Tomer", 111111777, false);
        myRef.child("Users").child("Russia").child("Ramat-Gan").child("" + user2.getId()).setValue(user2);

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
}
