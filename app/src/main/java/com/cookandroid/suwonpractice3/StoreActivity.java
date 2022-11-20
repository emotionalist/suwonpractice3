package com.cookandroid.suwonpractice3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {
    ImageButton back;
    Button starscore;
    ToggleButton exp, wish;
    RatingBar ratingBar;
    String resultstr;
    View dlgView;
    TextView name;
    ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storemain);
        this.getSupportActionBar().hide();

        back = findViewById(R.id.back);
        exp = findViewById(R.id.exp);
        wish = findViewById(R.id.wish);
        name = findViewById(R.id.name);
        logo = findViewById(R.id.logo);

        Intent Inintent = getIntent();
        name.setText(Inintent.getStringExtra("storeName"));
        logo.setImageResource(Inintent.getIntExtra("storePicture", 0));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        starscore = findViewById(R.id.starscore);

        starscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlgView = (View) View.inflate(StoreActivity.this, R.layout.stardialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(StoreActivity.this);
                dlg.setCancelable(false);
                dlg.setTitle("별점 주기");
                dlg.setView(dlgView);
                ratingBar = dlgView.findViewById(R.id.ratingBar);

                //사용자가 임의로 별점을 바꿀 수 있도록 함
                ratingBar.setIsIndicator(false);
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        resultstr = ""+v;
                    }
                });

                dlg.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        starscore.setText(resultstr + "점");
                    }
                });

                dlg.setNegativeButton("취소", null);

                dlg.show();
            }
        });
    }
}
