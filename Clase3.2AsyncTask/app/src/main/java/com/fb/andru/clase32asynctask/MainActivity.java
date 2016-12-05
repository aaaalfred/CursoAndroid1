package com.fb.andru.clase32asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDoalog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando....");
        progressDoalog.setTitle("Ejemplo de AsyncTask");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        new Progress().execute();
    }

    private class Progress extends AsyncTask<String , String , String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDoalog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            int cont = 0;
            while (cont < 100){
                try {
                    Thread.sleep(600);
                    progressDoalog.incrementProgressBy(1);
                    cont ++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDoalog.dismiss();
        }
    }
}
