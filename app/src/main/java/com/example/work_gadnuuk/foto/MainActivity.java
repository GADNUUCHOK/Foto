package com.example.work_gadnuuk.foto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button btnZoomIn, btnZoomOut;
    private static final int CAMERA_REQUEST =123;
    private static final int SELECT_PICTURE = 1;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Button01).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,
                                "Select Picture"), SELECT_PICTURE);
                    }
                });
        btn = (Button) findViewById(R.id.idbtn);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnZoomIn = (Button) findViewById(R.id.idbtnZoomIn);
        btnZoomIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView) findViewById(R.id.imageView);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_in);
                image.startAnimation(animation);
            }
        });
        btnZoomOut = (Button) findViewById(R.id.idbtnZoomOut);
        btnZoomOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView) findViewById(R.id.imageView);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.zoom_out);
                image.startAnimation(animation);
            }
        });
    }

    public void btnClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);

        }
    }
}


