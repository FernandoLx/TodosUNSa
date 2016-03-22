package ar.edu.unsa.exa.todosunsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ar.edu.unsa.exa.todosunsa.creditos.Creditos;
import ar.edu.unsa.exa.todosunsa.datos.recycler.Notificaciones;
import ar.edu.unsa.exa.todosunsa.inicio.recycler.ItemCardView;
import ar.edu.unsa.exa.todosunsa.inicio.recycler.RecyclerAdapter;

public class Inicio extends AppCompatActivity implements View.OnClickListener
{
    private final String EXAURL ="http://170.210.201.141/notificaciones/";
    private final String PUBLICIDAD = "PUBLICIDAD";
    private final String NOTICIA = "NOTICIA";
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_inicio);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(PUBLICIDAD,NOTICIA);
        recyclerAdapter.setOnClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.informacion) {
            iniciarActividad();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        ItemCardView item = recyclerAdapter.getItemPosition(recyclerView.getChildAdapterPosition(v));
        iniciarActividad(item.getImagenAppbar(),item.getTipo(),EXAURL);
    }

    private void iniciarActividad(int imagen_appbar, String tipo, String url)
    {
        Intent intent = new Intent()
                .setClass(Inicio.this, Notificaciones.class);
        intent.putExtra("IMAGEN_APPBAR",imagen_appbar);
        intent.putExtra("TIPO",tipo);
        intent.putExtra("EXAURL",url);
        startActivity(intent);
    }

    private void iniciarActividad()
    {
        Intent intent = new Intent()
                .setClass(Inicio.this, Creditos.class);
        startActivity(intent);
    }
}
