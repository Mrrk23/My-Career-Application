package com.example.mycareer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MyProfile extends AppCompatActivity {

    private static final String FIRE_LOG = "Fire_log";

    Button editprofilelocal,resetpasswordlocal;
    ImageView profileimage;
    TextView Name,Email,Phone;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    FirebaseUser user;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Name=(TextView)findViewById(R.id.profileName);
        Email=(TextView)findViewById(R.id.profileEmail);
        Phone=(TextView)findViewById(R.id.profilePhone);
        profileimage=(ImageView)findViewById(R.id.profileImage);
        editprofilelocal=(Button)findViewById(R.id.changeProfile);
        resetpasswordlocal=(Button)findViewById(R.id.resetPassword);

        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user=auth.getCurrentUser();
        userID=auth.getCurrentUser().getUid();
        StorageReference profileRef = storageReference.child("users/"+userID+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileimage);
            }
        });

        firebaseFirestore.collection("users").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String phone=documentSnapshot.getString("User_Phone");
                    Phone.setText(phone);
                    String email=documentSnapshot.getString("User_Email");
                    Email.setText(email);
                    String name=documentSnapshot.getString("User_Name");
                    Name.setText(name);
                }
                else {
                    Log.d(FIRE_LOG,"Error : "+ task.getException().getMessage());
                }
            }
        });

        editprofile();
        resetpassword();
    }

    public void editprofile(){
        editprofilelocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfile.this, EditProfile.class);
                intent.putExtra("fullname",Name.getText().toString());
                intent.putExtra("email",Email.getText().toString());
                intent.putExtra("phone",Phone.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void resetpassword(){
        //Reset password inside my profile section
        resetpasswordlocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText resetpass = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter the new password");
                passwordResetDialog.setView(resetpass);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Extract the email and set reset link
                        String newpass = resetpass.getText().toString();
                        user.updatePassword(newpass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MyProfile.this, "Password reset successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MyProfile.this, "Password reset failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Close the dialog box
                        dialogInterface.cancel();
                    }
                });
                passwordResetDialog.create().show();

            }
        });
    }
}