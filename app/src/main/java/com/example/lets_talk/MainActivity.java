package com.example.lets_talk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import java.util.ArrayList;
import static com.example.lets_talk.Keys.KEY_CONVERSATION_TOPIC;
import static com.example.lets_talk.Keys.KEY_USER_PROFILE;
public class MainActivity extends AppCompatActivity {
    private TextView register;
    private TextView timeCountTextView;
    private EditText pass;
    private EditText userName;
    private Button login;
    private MySharedPreferences msp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        findViews();
        mAuth = FirebaseAuth.getInstance();
        msp = new MySharedPreferences(this);
        msp.removeKey(KEY_USER_PROFILE);
        ArrayList<ConversationTopic> conversationTopics = new ArrayList<>();
        addTopics(conversationTopics);
        Gson gson = new Gson();
        msp.putString(KEY_CONVERSATION_TOPIC, gson.toJson(conversationTopics));
        login.setOnClickListener(goToChooseLanguagesScreen);
        register.setOnClickListener(goToRegisterActivity);
    }
    private void addTopics(ArrayList<ConversationTopic> conversationTopics) {
        ConversationTopic topic = new ConversationTopic("science", "global warming");
        conversationTopics.add(topic);
        topic.setTopic("Animals");
        conversationTopics.add(topic);
        topic.setTopic("physics");
        conversationTopics.add(topic);
        topic.setSubject("World");
        topic.setTopic("Travels");
        conversationTopics.add(topic);
        topic.setSubject("World");
        topic.setTopic("Africa");
        conversationTopics.add(topic);

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

    View.OnClickListener goToChooseLanguagesScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            loginFunc(view);

        }
    };

    public void loginFunc(View view)
    {
        mAuth.signInWithEmailAndPassword(userName.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            goToNextActivity(ChooseLanguages.class);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "login failed.", Toast.LENGTH_SHORT).show();

                                Context context = getApplicationContext();
                                CharSequence text = "try again!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                login.setEnabled(false);
                                timeCountTextView.setVisibility(View.VISIBLE);
                                new CountDownTimer(5000, 1000) {
                                    public void onTick(long millisUntilFinished) {
                                        timeCountTextView.setText("seconds remaining: " + millisUntilFinished / 1000);
                                    }

                                    public void onFinish() {
                                        timeCountTextView.setText("done!");
                                        timeCountTextView.setVisibility(View.INVISIBLE);
                                        login.setEnabled(true);
                                    }
                                }.start();
                            }
                        }
                        // ...

                });
    }
    private void goToNextActivity(Class activity)
    {
        Intent intent = new Intent(this , activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
            private void findViews () {
                pass = findViewById(R.id.pass_main_editText);
                userName = findViewById(R.id.name_main_editText);
                register = findViewById(R.id.joinActivity_main_activity_text_view);
                login = findViewById(R.id.login_btn);
                timeCountTextView=findViewById(R.id.main_textView_timeCount);
            }

        }

