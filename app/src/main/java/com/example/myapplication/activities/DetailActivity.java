package com.example.myapplication.activities;
import com.bumptech.glide.Glide;
import com.example.myapplication.Domains.BlogDomain;
import com.example.myapplication.R;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt,descriptionTxt;
    private BlogDomain item;
    private ImageView picImg,backBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        initView();
        setVariable();
    }
    private void setVariable(){
        item =(BlogDomain) getIntent().getSerializableExtra("object");
        titleTxt.setText(item.getTitle());
        descriptionTxt.setText(item.getDescription());
        int drawableResId=getResources().getIdentifier(item.getPic(),"drawable",getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);
        backBtn.setOnClickListener(v -> finish());
    }
    private void initView(){
        titleTxt=findViewById(R.id.titleTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);

        picImg=findViewById(R.id.picImg);

        backBtn=findViewById(R.id.backBtn);
    }

}
