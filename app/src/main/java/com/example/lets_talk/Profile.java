package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.example.lets_talk.Keys.KEY_USER_PROFILE;

public class Profile extends AppCompatActivity {
    private MySharedPreferences msp;
    private User user;
    private TextView fullName;
    private TextView location;
    private TextView score;
    private TextView description;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        msp=new MySharedPreferences(this);
        findView();
        back.setOnClickListener(goToMassagesScreen);

        Gson gson = new Gson();
         user= gson.fromJson(msp.getString(KEY_USER_PROFILE, ""), new TypeToken<User>() {
       }.getType());
         Log.d("pppt",user.toString());
        fullName.setText("name:"+user.getFirstName()+" "+user.getLastName());
        location.setText("location: "+user.getLocation());
        score.setText("score: "+user.getScore());
        description.setText(user.getDescription());



    }

    private void findView() {

        fullName=findViewById(R.id.fullName_textView_profile);
        location=findViewById(R.id.location_textView_profile);
        score=findViewById(R.id.score_textView_profile);
        description=findViewById(R.id.description_textView_profile);
        back=findViewById(R.id.back_profile_imageView);
    }

    View.OnClickListener goToMassagesScreen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent next = new Intent(getApplicationContext(),MassagesScreen.class);
            startActivity(next);
            finish();
        }
    };

}

