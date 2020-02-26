package com.example.lets_talk;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabace;
    private DatabaseReference mRaferencelanguageGroup;
    private List<LanguageGroups> languageGroups= new ArrayList<>();

    public FirebaseDatabaseHelper() {
        mDatabace=FirebaseDatabase.getInstance();
        mRaferencelanguageGroup=mDatabace.getReference("language");

    }

public void readLanguageGroup(){
        mRaferencelanguageGroup.child("english").child("groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                languageGroups.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot KeyNode :dataSnapshot.getChildren()){
                    keys.add(KeyNode.getKey());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}
}
