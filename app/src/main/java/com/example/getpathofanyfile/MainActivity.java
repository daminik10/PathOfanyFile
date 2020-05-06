package com.example.getpathofanyfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_SOMETHING = 1;
    private TextView tv;
    private ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        imgbtn = findViewById(R.id.img_btn);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getContent();

            }
        });

    }

    private void getContent() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("*/*");
        startActivityForResult(i, PICK_SOMETHING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_SOMETHING:
                if (resultCode == RESULT_OK) {

                    if (requestCode == PICK_SOMETHING) {
                        Log.d("PICK SOMETHING", data.getData().toString());
                        String realPath = GetRealPath.getRealPathFromAPI19(this, data.getData());
                        Log.i("realPath", realPath + "");
                        if (realPath == null) {
                            realPath = GetRealPath.getRealPathFromAPI11_to18(this, data.getData());
                        }
                        Log.i("realPath", realPath + "");
                        if (realPath == null) {
                            realPath = GetRealPath.getRealPathFromSD_CARD(data.getData());
                        }
                        Log.i("realPath", realPath + "");

                        //  textURI.setText(data.getData().toString());
                        tv.setText(realPath);


                    }
                }


                break;
        }
    }
}