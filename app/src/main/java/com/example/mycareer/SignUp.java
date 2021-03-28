package com.example.mycareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText rname,remail,rphone,rpass,rrpass;
    Button signup;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        rname=(EditText)findViewById(R.id.RName);
        remail=(EditText)findViewById(R.id.REmail);
        rphone=(EditText)findViewById(R.id.RPhone);
        rpass=(EditText)findViewById(R.id.RPass);
        rrpass=(EditText)findViewById(R.id.RRPass);
        signup=(Button)findViewById(R.id.SignUpButton);
        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        clicksignup();
    }
    public void clicksignup(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=remail.getText().toString();
                final String name=rname.getText().toString();
                final String phone=rphone.getText().toString();
                String pass=rpass.getText().toString();
                String rpass=rrpass.getText().toString();
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(rpass)){
                    Toast.makeText(SignUp.this,"Please Enter ALL Fields",Toast.LENGTH_SHORT).show();
                }
                else if(!pass.equals(rpass)){
                    Toast.makeText(SignUp.this,"Password doesn't match",Toast.LENGTH_SHORT).show();
                }
                else
                    auth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp.this,"Successfull",Toast.LENGTH_SHORT).show();
                                        userID=auth.getCurrentUser().getUid();
                                        Map<String,String> map = new HashMap<>();
                                        map.put("User_Name",name);
                                        map.put("User_Email",email);
                                        map.put("User_Phone",phone);
                                        firebaseFirestore.collection("users").document(userID).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(SignUp.this,"User successfully added",Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                String error=e.getMessage();
                                                Toast.makeText(SignUp.this,"Error msg: "+error,Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        finish();
                                    }
                                    else
                                        Toast.makeText(SignUp.this,"Error Occurs",Toast.LENGTH_SHORT).show();
                                }
                            });
            }
        });
    }
}