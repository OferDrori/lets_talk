package com.example.lets_talk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.lets_talk.Keys.KEY_USER_PROFILE;

public class MassagesScreen extends AppCompatActivity {
    ListView listView;
    TextView welcome;
    ImageView profile;
    ArrayList<Message> arrayList = new ArrayList<>();
    MyAdapter adapter;
    private MySharedPreferences msp;
    private User user;
    private Button send;
    private EditText massage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massages_screen);
        listView = findViewById(R.id.listView_massages);
        welcome = findViewById(R.id.welcome_txt_massages_screen);
        send = findViewById(R.id.send_btn_massagesScreen);
        massage = findViewById(R.id.massage_edit_text);
        arrayList.add(new Message(System.currentTimeMillis(), "Hi Tomer4!", 11, 22, 1, "Yosi"));
        arrayList.add(new Message(System.currentTimeMillis(), "Hi Yosi!", 11, 22, 1, "Tomer"));
        adapter = new MyAdapter(this, arrayList);
        listView.setAdapter(adapter);
        msp = new MySharedPreferences(this);
        profile = findViewById(R.id.gotoProfile_imageView_massagesScreen);
        profile.setOnClickListener(goToProfile);
        send.setOnClickListener(sendMassage);
        Gson gson = new Gson();
        user = gson.fromJson(msp.getString(KEY_USER_PROFILE, ""), new TypeToken<User>() {
        }.getType());
        welcome.setText("welcome  " + user.getFirstName());
        Log.d("ggg", user.getEmail());


    }

    View.OnClickListener goToProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent next = new Intent(getApplicationContext(), Profile.class);
            startActivity(next);
            finish();
        }
    };

    View.OnClickListener sendMassage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!massage.getText().toString().equals("")) {
                arrayList.add(new Message(System.currentTimeMillis(), massage.getText().toString(),user.getId(), 22, 1, user.getFirstName()));
                listView.setAdapter(adapter);
            }

        }
    };
}
