package com.example.lets_talk;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.UUID;
public class RegitrationActivity extends AppCompatActivity {
    private Button create;
    private EditText fullName;
    private EditText email;
    private EditText location;
    private EditText password;
    private EditText description;
    private Button btn_selectImage;
    private User user;
    private Gson gson;
    private TextView info;
    private static long idCounter = 0;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    private ImageView showImage;
    FirebaseStorage storage;
    StorageReference storageReference;
    String urlPath = UUID.randomUUID().toString();
    private MySharedPreferences msp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_regitration);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00BCD4"));
        actionBar.setBackgroundDrawable(colorDrawable);
        findView();

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        create.setOnClickListener(makeAuser);
        btn_selectImage.setOnClickListener(selectImage);

        msp = new MySharedPreferences(this);
    }


    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                showImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }


    public void regFunc(View view) {

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            User user = new User(fullName.getText().toString(), email.getText().toString(), location.getText().toString(),0, password.getText().toString(), description.getText().toString(), urlPath);
                            FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();
                            myRef.child("Israel").child((userUID.getUid())).setValue(user);
                            Toast.makeText(RegitrationActivity.this, "REG ok.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegitrationActivity.this, ChooseLanguages.class);
                            RegitrationActivity.this.startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegitrationActivity.this, "REG failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    // UploadImage method
    private void uploadImage() {
        if (filePath != null) {
            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref = storageReference.child("images/").child(urlPath);
            Log.i("123123132", " " + ref);
            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Log.i("111111111", "succes");

                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error, Image not uploaded
                            Log.i("111111111", "fail");

                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                    Log.i("111111111", "uploading");
                                }
                            });
        }
    }

    private void findView() {
        create = findViewById(R.id.create_btn_activity_regitration);
        fullName = findViewById(R.id.fullName_edittext_registration_activity);
        email = findViewById(R.id.email_registration_edt);
        location = findViewById(R.id.location_edit_text_registration_activity);
        password = findViewById(R.id.editText_password_activity_regitration);
        description = findViewById(R.id.editText_description_activity_regitration);
        info = findViewById(R.id.regitration_info_textView);
        btn_selectImage = findViewById(R.id.uploadImg_btn_activity_regitration);
        showImage = findViewById(R.id.profile_img_regitration);
    }

    private void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }


    View.OnClickListener selectImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SelectImage();
        }
    };


    View.OnClickListener makeAuser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            uploadImage();
            regFunc(v);
        }


    };
}
