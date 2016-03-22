package ar.edu.unsa.exa.todosunsa.conexion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ar.edu.unsa.exa.todosunsa.Inicio;
import ar.edu.unsa.exa.todosunsa.R;

public class Conexion extends AppCompatActivity {

    private Internet internet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);
        internet = new Internet(getApplicationContext());
    }

    public void reintentar(View view) {
        if(internet.conectado())
        {
            {Intent intent = new Intent()
                    .setClass(Conexion.this, Inicio.class);
                startActivity(intent);}
            finish();
        }
    }
}
