package com.fb.andru.clase31_handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
        Button button;
        ProgressDialog progressDoalog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            button = (Button) findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDoalog = new ProgressDialog(MainActivity.this);
                    progressDoalog.setMax(100);
                    progressDoalog.setMessage("Cargando....");
                    progressDoalog.setTitle("Ejemplo de Handler");
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDoalog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (progressDoalog.getProgress() <= progressDoalog
                                        .getMax()) {

                                    Thread.sleep(200);
                                    Thread.currentThread().getName();

                                    handle.sendMessage(handle.obtainMessage());

                                    if (progressDoalog.getProgress() == progressDoalog
                                            .getMax()) {
                                        progressDoalog.dismiss();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }



                Handler handle = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        progressDoalog.incrementProgressBy(1);
                    }
                };
            });
        }
    }
