package com.example.bijan.implicitintentex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;
    public static final int REQ_CD1 = 1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.dial);
        button2 = (Button) findViewById(R.id.open);
        button3 = (Button) findViewById(R.id.opengl);
        imageView = (ImageView) findViewById(R.id.images);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                Uri myUri = Uri.parse("tel:9742446985");
                intent.setData(myUri);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://porn.com");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQ_CD1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CD1 && resultCode == RESULT_OK){
//            Bundle bundle = data.getExtras();
//            String imagepath = bundle.getString("data");
//            Bitmap bitmap = BitmapFactory.decodeFile(imagepath);
//            imageView.setImageBitmap(bitmap)
            Uri imagepath = data.getData();
            Bitmap image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), imagepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
