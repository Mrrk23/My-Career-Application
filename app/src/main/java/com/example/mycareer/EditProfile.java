package com.example.mycareer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    EditText editname,editemail,editphone;
    Button saveinfo;
    ImageView editprofile;

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent data = getIntent();
        String fullname = data.getStringExtra("fullname");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("phone");

        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        user=auth.getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference();

        editemail=(EditText)findViewById(R.id.editprofileEmailAddress);
        editname=(EditText)findViewById(R.id.editprofileFullName);
        editphone=(EditText)findViewById(R.id.editprofilePhoneNo);
        saveinfo=(Button)findViewById(R.id.saveProfileInfo);
        editprofile=(ImageView)findViewById(R.id.editprofileImageView);
        editemail.setText(email);
        editname.setText(fullname);
        editphone.setText(phone);

        StorageReference profileRef = storageReference.child("users/"+user.getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(editprofile);
            }
        });

        changeprofileimage();
        savenewinfo();
    }

    public void changeprofileimage(){
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            if(resultCode== Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profileimage.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        //Upload image to firebase storage
        final StorageReference fileRef = storageReference.child("users/"+user.getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(EditProfile.this, "Profile photo updated", Toast.LENGTH_SHORT).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(editprofile);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfile.this, "Error, please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void savenewinfo(){
        saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editname.getText().toString().isEmpty() || editemail.getText().toString().isEmpty() || editphone.getText().toString().isEmpty())
                {
                    Toast.makeText(EditProfile.this, "One or many fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String email=editemail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = firebaseFirestore.collection("users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("User_Email",email);
                        edited.put("User_Name",editname.getText().toString());
                        edited.put("User_Phone",editphone.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(EditProfile.this,MyProfile.class));
                                finish();
                            }
                        });
                        Toast.makeText(EditProfile.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}