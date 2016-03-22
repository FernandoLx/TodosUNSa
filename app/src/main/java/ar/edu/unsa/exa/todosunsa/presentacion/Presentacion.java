package ar.edu.unsa.exa.todosunsa.presentacion;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import ar.edu.unsa.exa.todosunsa.Inicio;
import ar.edu.unsa.exa.todosunsa.R;
import ar.edu.unsa.exa.todosunsa.conexion.Conexion;
import ar.edu.unsa.exa.todosunsa.conexion.Internet;

public class Presentacion extends AppCompatActivity {

    private static final long TIEMPO=3000;
    private Internet internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        internet = new Internet(getApplicationContext());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_presentacion);
        animacion();
    }

    private void animacion()
    {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(internet.conectado())
                {Intent intent = new Intent()
                        .setClass(Presentacion.this, Inicio.class);
                    startActivity(intent);}
                else
                {Intent intent = new Intent()
                        .setClass(Presentacion.this, Conexion.class);
                     startActivity(intent);}
                internet = null;
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, TIEMPO);
    }

}
