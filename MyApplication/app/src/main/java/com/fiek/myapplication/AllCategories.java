package com.fiek.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AllCategories extends AppCompatActivity {

    Animation topAnimation, bottomAnimation;
    ImageView image;
    TextView text;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_categories);

      backbutton = findViewById(R.id.back_icon);

      backbutton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            AllCategories.super.onBackPressed();
                                        }});

        topAnimation= AnimationUtils.loadAnimation(this, R.anim.animation_top);
        bottomAnimation= AnimationUtils.loadAnimation(this, R.anim.animation_bottom);

        image = findViewById(R.id.images1);
        text = findViewById(R.id.texts1);
        image.setAnimation(topAnimation);
        text.setAnimation(bottomAnimation);

        image = findViewById(R.id.images2);
        text = findViewById(R.id.texts2);
        image.setAnimation(topAnimation);
        text.setAnimation(bottomAnimation);

        image = findViewById(R.id.images3);
        text = findViewById(R.id.texts3);
        image.setAnimation(topAnimation);
        text.setAnimation(bottomAnimation);

        image = findViewById(R.id.images4);
        text = findViewById(R.id.texts4);
        image.setAnimation(topAnimation);
        text.setAnimation(bottomAnimation);

        image = findViewById(R.id.images5);
        text = findViewById(R.id.texts5);
        image.setAnimation(topAnimation);
        text.setAnimation(bottomAnimation);

    }
}
