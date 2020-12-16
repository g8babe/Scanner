package com.example.j_zxing;

import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.*;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.google.zxing.BarcodeFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button B1;
    //private Button B2;
    private TextView TV;
    //private EditText ET;
    private Activity context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TV=(TextView)findViewById(R.id.TV);
        B1=(Button)findViewById(R.id.button);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(context);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("掃描發票");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });


        /*ET=(EditText)findViewById(R.id.ET);
        B2=(Button)findViewById(R.id.button2);
        //以下產生QR語法
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BarcodeEncoder BE = new BarcodeEncoder();
                        Bitmap bitmap = BE.encodeBitmap(ET.getText().toString(), BarcodeFormat.QR_CODE, 400, 400);
                        ImageView IV = (ImageView)findViewById(R.id.imageView);
                        IV.setImageBitmap(bitmap);

                }catch (Exception e){

                }

            }
        });*/
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult SR = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if(SR != null){
            if (SR.getContents() != null){
                String SC=SR.getContents();
                if (!SC.equals("")){
                    TV.setText(SC.toString());
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, intent);
            TV.setText("產生錯誤");
        }
    }
}