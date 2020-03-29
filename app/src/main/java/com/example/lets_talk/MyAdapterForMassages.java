package com.example.lets_talk;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.lets_talk.Keys.connectedName;

public class MyAdapterForMassages extends BaseAdapter {
    private Context context;
    private ArrayList<Message> arrayList;
    private TextView content, name;
    private ImageView imgprofile;
    private StorageReference mStorageRef;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;


    // private MySharedPreferences msp;
    public MyAdapterForMassages(Context context, ArrayList<Message> arrayList) {
        mStorageRef = FirebaseStorage.getInstance().getReference();
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        filePath = Uri.fromFile(new File("images/"));
        Message message = arrayList.get(position);
        if (message.getSenderName().equals(connectedName)) {
            convertView = LayoutInflater.from(context).inflate(R.layout.my_message, parent, false);
            content = convertView.findViewById(R.id.message_body);
            content.setText(" " + arrayList.get(position).getContent());
            // name.setText(arrayList.get(position).getSenderName());
            return convertView;
        }
        else {
            convertView = LayoutInflater.from(context).inflate(R.layout.their_message, parent, false);
            content = convertView.findViewById(R.id.message_body);
            name = convertView.findViewById(R.id.name_text_view);
            imgprofile = convertView.findViewById(R.id.avatar);



            content.setText(" " + arrayList.get(position).getContent());
            name.setText(arrayList.get(position).getSenderName());

            StorageReference riversRef = mStorageRef.child("images/").child(message.getPhotoPath());
            try {
                final File localFile = File.createTempFile("Images", "jpeg");
                riversRef.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                // Setting image on image view using Bitmap
                                imgprofile.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failed download
                        // ...
                    }
                });
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }

        }
        return convertView;


    }
}





