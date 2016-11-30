package com.fb.andru.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        //Este código se inserta en el método onCreate() de la actividad.
        Intent intent = this.getIntent();

        if (intent == null){
            Log.d("Tag", "La actividad no se ha llamado mediante un intent.");
        }
    }

}
