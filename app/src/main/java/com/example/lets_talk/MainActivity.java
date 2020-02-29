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

import static com.example.lets_talk.Keys.KEY_LANGUAGE_ALL;
import static com.example.lets_talk.Keys.KEY_USER_PROFILE;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<User> temp = new ArrayList<>();
    ArrayList<LanguageGroups> languageGroupsw=new ArrayList<>();
    ArrayList<LanguageGroups> languageGroupstemp = new ArrayList<>();

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
        DatabaseReference myRef = database.getReference();

       // Log.d("pttt", "A - Number of users: " + users.size());
        Log.d("pttt", "A - Number of languageGroups: " + languageGroupsw.size());
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
                refreshListUsers(users);
                //  Log.d("pttt", "C - Number of users: " + users.size());


            }

            @Override
            public void error() {
                // TODO: 2020-01-08 handle errors
            }

        });

//        MyFirebase.getLanguageTalk(new CallBack_LanguageReady() {
//            @Override
//            public void languadeReady(ArrayList<LanguageGroups> languageGroups) {
//                refreshListOflanguage(languageGroups);
////                Log.d("pttt", "C - Number of languageGroups: " + languageGroups.size());
//
//            }
//
//            @Override
//            public void error() {
//
//            }
//        });
//
//        Log.d("pttt", "B - Number of users: " + users.size());
//        Log.d("pttt", "B - Number of users: " + languageGroupsw.size());
//
//        User temp = new User();
//        User user = new User("Gadi", "choen", "gadi", "Petah-Tikva", 2, "112233","student");
//        myRef.child("Users").child("Israel").child("" + user.getId()).setValue(user);
//
//
//        User user2 = new User("Tomer", "choen", "tomer@gmail.com", "Petah-Tikva", 3, "1122","student of math");
//        myRef.child("Users").child("Israel").child("" + user2.getId()).setValue(user2);
//        Message message = new Message();
//
//        message = new Message()
//                .setTimeStamp(System.currentTimeMillis())
//                .setStatus(0)
//                .setSender(user.getId())
//                .setReceiver(user2.getId())
//                .setContent("Hi Tomer4!");
//        ArrayList<Message> massagestemp=new ArrayList<>();
//        massagestemp.add(message);
//        massagestemp.add(message);
//        massagestemp.add(message);
//        massagestemp.add(message);
//        ArrayList<GruopOfMassages> gruopstemp =new ArrayList<>();
//        GruopOfMassages gruopOfMassages =new GruopOfMassages("the best",4, massagestemp);
//        gruopstemp.add(gruopOfMassages);
//        gruopstemp.add(gruopOfMassages);
//        gruopstemp.add(gruopOfMassages);
//        gruopstemp.add(gruopOfMassages);
//        LanguageGroups lantemp=new LanguageGroups( "ENGLISH", 1, 11, 4, gruopstemp);
//        myRef.child("language").child("ENGLISH").setValue(lantemp);
//        myRef.child("language").child("HEBREW").setValue(lantemp);
//
//        myRef.child("Chats").child(user.getId() + "-" + user2.getId()).push().setValue(message);

    }

    private void refreshListUsers(ArrayList<User> users) {

        for (User var : users) {
//            Log.d("pttt", "B - Number of users: " + var.toString());
//            Log.d("pttt", "B - Number of users: " + var.getEmail());
//            Log.d("pttt", "B - Number of users: " + var.getPassword());
            temp.add(var);

        }
        // TODO: 12/01/2020 do refresh List
    }
//    private void refreshListOflanguage(ArrayList<LanguageGroups> languageGroups) {
//        languageGroupstemp.clear();
//        for (LanguageGroups var : languageGroups) {
//            languageGroupstemp.add(var);
//            Log.d("pttt", var.getLanguage());
//            Log.d("pttt", var.getGruopOfMassages().get(0).getName());
//
//        }
//        Gson gson=new Gson();
//        msp.putString(KEY_LANGUAGE_ALL,gson.toJson(languageGroupstemp));
//
//    }


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
            Intent intent = new Intent(MainActivity.this, ChooseLanguage.class);
            startActivity(intent);

        }
    };

    boolean checkUser() {

        for (User var : temp) {
      //      Log.d("pttt", "B -s: " + var.toString());
        //    Log.d("pttt", "B -: " + var.getEmail());
         //   Log.d("pttt", "B - : " + var.getPassword());
        }
        Log.d("pttt", "check");
        for (int i = 0; i < temp.size(); i++) {
         //   Log.d("pttt", temp.get(i).getEmail());
          //  Log.d("pttt", temp.get(i).getPassword());
          //  Log.d("pttt", userName.getText().toString());
            if (temp.get(i).getEmail().equals(userName.getText().toString())&&temp.get(i).getPassword().equals(pass.getText().toString())) {
                Gson gson = new Gson();
                msp.putString(KEY_USER_PROFILE, gson.toJson(temp.get(i)));
                return true;
            }

        }
        return false;

    }

}

