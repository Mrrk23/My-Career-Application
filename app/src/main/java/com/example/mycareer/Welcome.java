package com.example.mycareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    ImageView iv;
    TextView welcome;
    Animation toptobotton,bottomtotop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        iv=(ImageView)findViewById(R.id.imageView);
        welcome=(TextView)findViewById(R.id.Welcome);
        toptobotton= AnimationUtils.loadAnimation(Welcome.this,R.anim.toptobottom);
        bottomtotop= AnimationUtils.loadAnimation(Welcome.this,R.anim.bottomtotop);
        iv.setAnimation(toptobotton);
        welcome.setAnimation(bottomtotop);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }
}
