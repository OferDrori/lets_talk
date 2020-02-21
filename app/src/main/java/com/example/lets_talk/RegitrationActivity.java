package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.gson.Gson;

public class RegitrationActivity extends AppCompatActivity {
    private Button create;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText location;
    private User user;
    private Gson gson;
    private static long idCounter = 0;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitration);
        create = findViewById(R.id.create_btn_activity_regitration);
        firstName=findViewById(R.id.firstName_edittext_registration_activity);
        lastName=findViewById(R.id.lastName_edittext_registration_activity);
        email=findViewById(R.id.email_registration_edt);
        location=findViewById(R.id.location_edit_text_registration_activity);
        create.setOnClickListener(makeAuser);
    }
    View.OnClickListener makeAuser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            user=new User(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),location.getText().toString(),createID());

             myRef.child("Users").child("Israel").child(user.getLocation()).child("" + user.getId()).setValue(user);
//            Intent next = new Intent(getApplicationContext(), MainActivity.class);
//            next.putExtra("speed", speed);
//            startActivity(next);
        }
    };
    public static long createID()
    {
        return idCounter++;
    }
}
