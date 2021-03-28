package com.example.mycareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText lemail,lpass;
    Button login;
    Button linktosignup,forgotpass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        lemail=(EditText)findViewById(R.id.LEmail);
        lpass=(EditText)findViewById(R.id.LPass);
        login=(Button)findViewById(R.id.LogInButton);
        linktosignup=(Button) findViewById(R.id.LinkToSignUp);
        forgotpass=(Button)findViewById(R.id.ForgetPassword);
        auth=FirebaseAuth.getInstance();
        clicklogin();
        clicktosignup();
        clicktoforgotpass();
    }
    public void clicklogin(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=lemail.getText().toString();
                String pass=lpass.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                    Toast.makeText(MainActivity.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this,Home.class));
                                    }
                                    else
                                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
    public void clicktosignup(){
        linktosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    public void clicktoforgotpass(){
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetmail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter your registered email address");
                passwordResetDialog.setView(resetmail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Extract the email and set reset link
                        String mail = resetmail.getText().toString();
                        auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error occurs, please try again later" + e.getMessage(), Toast.LENGTH_SHORT).show();
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