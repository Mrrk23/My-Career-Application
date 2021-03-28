package com.example.mycareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CareerDetails extends AppCompatActivity {

    private static final String FIRE_LOG = "Fire_log";
    TextView careerHead, careerText;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_details);
        careerHead = (TextView) findViewById(R.id.CareerTVHeading);
        careerText = (TextView) findViewById(R.id.CareerTVText);
        firebaseFirestore = FirebaseFirestore.getInstance();

        String CE = "Computer engineering";
        String EE = "Electrical engineering";
        String Civil = "Civil engineering";
        String ME = "Mechanical engineering";
        String BBA = "Bachelor of Business Administration";
        String BM = "Bachelor of Medicine, Bachelor of Surgery";
        String BPh = "Bachelor of Pharmacy";
        String BSc = "Bachelor of Science";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            careerHead.setText(bundle.getString("Name"));
            Toast.makeText(CareerDetails.this, bundle.getString("Name"), Toast.LENGTH_SHORT).show();
            if(CE.equals(careerHead.getText().toString()))
            {
                String clg="CE";
                databasefetch2(clg);
            }
            else if (EE.equals(careerHead.getText().toString()))
            {
                String clg="EE";
                databasefetch2(clg);
            }
            else if (Civil.equals(careerHead.getText().toString()))
            {
                String clg="Civil";
                databasefetch2(clg);
            }
            else if (ME.equals(careerHead.getText().toString()))
            {
                String clg="ME";
                databasefetch2(clg);
            }
            else if (BBA.equals(careerHead.getText().toString()))
            {
                String clg="BBA";
                databasefetch2(clg);
            }
            else if (BM.equals(careerHead.getText().toString()))
            {
                String clg="BM";
                databasefetch2(clg);
            }
            else if (BPh.equals(careerHead.getText().toString()))
            {
                String clg="BPh";
                databasefetch2(clg);
            }
            else if (BSc.equals(careerHead.getText().toString()))
            {
                String clg="BSc";
                databasefetch2(clg);
            }
        }
    }

    public void databasefetch2(String string)
    {
        firebaseFirestore.collection("career").document(string).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String text = documentSnapshot.getString("Text");
                    careerText.setText(text);
                }
                else {
                    Log.d(FIRE_LOG,"Error : "+ task.getException().getMessage());
                }
            }
        });
    }
}