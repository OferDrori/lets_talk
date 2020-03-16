package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.lets_talk.Keys.KEY_USER_PROFILE;
import static com.example.lets_talk.Keys.connectedName;

public class RegitrationActivity extends AppCompatActivity {
    private Button create;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText location;
    private EditText password;
    private EditText description;
    private User user;
    private Gson gson;
    private TextView info;
    private static long idCounter = 0;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private MySharedPreferences msp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_regitration);
        findView();
        create.setOnClickListener(makeAuser);
        msp=new MySharedPreferences(this);
    }

    private void findView() {
        create = findViewById(R.id.create_btn_activity_regitration);
        firstName=findViewById(R.id.firstName_edittext_registration_activity);
        lastName=findViewById(R.id.lastName_edittext_registration_activity);
        email=findViewById(R.id.email_registration_edt);
        location=findViewById(R.id.location_edit_text_registration_activity);
        password=findViewById(R.id.editText_password_activity_regitration);
        description=findViewById(R.id.editText_description_activity_regitration);
        info=findViewById(R.id.regitration_info_textView);
    }

    View.OnClickListener makeAuser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(isValidEmail(email.getText().toString())) {
                user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), location.getText().toString(), createID(), password.getText().toString(), description.getText().toString());
                myRef.child("Users").child("Israel").push().setValue(user);
                Gson gson = new Gson();
                msp.putString(KEY_USER_PROFILE, gson.toJson(user));
                Intent next = new Intent(getApplicationContext(), ChooseLanguages.class);
                connectedName = user.getFirstName();
                startActivity(next);
                finish();
            }
        }
    };
    public static long createID()
    {
        return idCounter++;
    }

    public boolean isValidEmail(CharSequence target) {
        info.setVisibility(View.VISIBLE);
        info.setText("Email is not valid");
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }
}
