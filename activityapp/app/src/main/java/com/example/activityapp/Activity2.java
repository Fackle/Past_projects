package com.example.activityapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Activity2 extends AppCompatActivity {

    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent =getIntent();

        //przypisanie obiektow do zmiennych
        imageView=(ImageView)findViewById(R.id.image_view);
        button=(Button)findViewById(R.id.button);

        //pozwolenie o dostep do aparatu
        if(ContextCompat.checkSelfPermission(Activity2.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Activity2.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //otworz aparat
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity2.RESULT_OK && requestCode == 100 ) {
            //pobierz zdjecie
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //wyswietl zdjecie w ImageView
            imageView.setImageBitmap(captureImage);

        }
    }
}