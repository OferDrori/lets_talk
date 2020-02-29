package com.example.lets_talk;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class MyFirebase {
    public static void getUsers(final CallBack_UsersReady callBack_usersReady) {
        final ArrayList<User> users2 = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("Users").child("Israel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null)
                    callBack_usersReady.error();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    users2.add(user);
                }


                callBack_usersReady.usersReady(users2);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callBack_usersReady.error();
            }
        });
    }

    public static void getLanguageTalk(final CallBack_LanguageReady callBack_LanguageReady) {
        final ArrayList<LanguageGroups> languageGroups2 = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("language");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null)
                    callBack_LanguageReady.error();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LanguageGroups languageGroup = ds.getValue(LanguageGroups.class);
                    languageGroups2.add(languageGroup);
                }


                callBack_LanguageReady.languadeReady(languageGroups2);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callBack_LanguageReady.error();
            }
        });
    }



    }

