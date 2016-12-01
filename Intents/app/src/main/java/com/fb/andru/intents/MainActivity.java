package com.fb.andru.intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //invocarSegundaActividad();
                //call();
                //invokeWebBrowser();
                //dial();
                //showMapAtLatLong();
                enviarMsgApp();
            }
        });
    }

    public  void invocarSegundaActividad(){
        String name = "mi.segunda.actividad";
        Intent intent = new Intent(name);
        startActivity(intent);
    }
    //Abrimos ul navegador con lo link
    public  void invokeWebBrowser(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.facebook.com/"));
        startActivity(intent);
    }


    public  void dial(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }
    //Metodo para empezar el Intent de llamada telefonica
    public  void call(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:5535085008"));
        //Validamos si hay permisos para llamadas, esto es para android API 23.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //pedimos permiso
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    10);
            return;
        }else {     //Si nos da permiso
            try{
                startActivity(intent);  //empezar el activity para llamada
            }
            catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
            }
        }
    }
    //Muestra Maps, solo le debeos de mandar las coordenadas
    public  void showMapAtLatLong(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
                                //aqui van la latitud y altitud
        intent.setData(Uri.parse("geo:0,0?z=4&q;=restaurantes"));
        startActivity(intent);
    }
    //metodo para enviar mensaje por whatsapp
    public void enviarMsgApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent .setType("text/plain");
        String texto = "Que onda";
        intent .setPackage("com.whatsapp");// Para facebook es com.facebook.katana
        if (intent != null) {
            intent .putExtra(Intent.EXTRA_TEXT, texto);
            startActivity(Intent.createChooser(intent, texto));
        } else {
            Toast.makeText(this,"Nooo whatsapp, whatsapp man",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
