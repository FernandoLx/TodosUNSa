package ar.edu.unsa.exa.todosunsa.datos.recycler;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ar.edu.unsa.exa.todosunsa.R;
import ar.edu.unsa.exa.todosunsa.json.JsonDecoder;

public class Notificaciones extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener
{

    private int TAMANO_PAGINA;
    private int IMAGEN_APPBAR;
    //private int IMAGEN_ICONO;
    private String TIPO;
    private String EXAURL;
    private int PAGINA;
    private int totalItemCount;
    //private int visibleItemCount;
    private int lastVisibleItem;
    private boolean FIN;
    private RecyclerView datos;
    private SwipeRefreshLayout srl;
    private NotificacionAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private asyncTask asynctask;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notificaciones);
        setSupportActionBar(toolbar);

        this.IMAGEN_APPBAR = getIntent().getIntExtra("IMAGEN_APPBAR",R.drawable.levolucion);
        //this.IMAGEN_ICONO = getIntent().getIntExtra("IMAGEN_ICONO", R.drawable.noticias);
        this.TIPO = getIntent().getStringExtra("TIPO");
        this.EXAURL = getIntent().getStringExtra("EXAURL");
        this.TAMANO_PAGINA = 10;
        this.PAGINA = 0;
        this.FIN = false;
        adapter = null;

        ((ImageView)findViewById(R.id.imagen_appbar_notificacion)).setImageResource(IMAGEN_APPBAR);

        datos = (RecyclerView) findViewById(R.id.recycler_notificaciones);

        srl = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        srl.setColorSchemeColors(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        srl.setOnRefreshListener(this);

        (Toast.makeText(getApplicationContext(), "Cargando", Toast.LENGTH_SHORT)).show();
        asynctask = new asyncTask();
        asynctask.execute();
    }

    private void setScrollListener(){
           datos.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0)
                {
                    //Log.i("desplazamient","dy " + dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    //visibleItemCount = linearLayoutManager.getChildCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    //Log.i("cargar"," total - ultimo " + totalItemCount +" "+ lastVisibleItem);

                    if(!FIN && (totalItemCount < lastVisibleItem + 2))
                    {
                        //Log.i("cargar"," total - ultimo " + totalItemCount +" "+ lastVisibleItem);
                        PAGINA += TAMANO_PAGINA;
                       //Log.i("PAGINA "," "+ PAGINA);
                        executeTask();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Notificacion notificacion = adapter.getItemPosition(datos.getChildAdapterPosition(v));
        iniciarActividad(notificacion.titulo(), notificacion.descripcion());
    }

    private void iniciarActividad(String titulo, String descripcion)
    {
        Intent intent = new Intent()
                .setClass(Notificaciones.this, ItemDescripcion.class);
        intent.putExtra("TITULO",titulo);
        intent.putExtra("DESCRIPCION",descripcion);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onRefresh() {
        if(adapter != null)
        {
            adapter.clear();
        }
        FIN = false;
        this.PAGINA = 0;
        executeTask();
    }

    private void executeTask()
    {
        if(taskStatus())
        {
            (Toast.makeText(getApplicationContext(), "Cargando", Toast.LENGTH_SHORT)).show();
            asynctask = new asyncTask();
            asynctask.execute();
        }

    }

    private boolean taskStatus()
    {
        if(asynctask.getStatus()== AsyncTask.Status.FINISHED)
            return true;
        return false;
    }

    private class asyncTask extends AsyncTask<Void,Void,ArrayList<Notificacion>>
    {

        @Override
        protected ArrayList<Notificacion> doInBackground(Void... params) {

            return consultaRest();
        }

        @Override
        public void onPostExecute(ArrayList<Notificacion> arrayList){
            super.onPostExecute(arrayList);
            if(arrayList != null && arrayList.size() > 0)
            {
                if(adapter != null) {
                    for (Notificacion n: arrayList) {
                        adapter.add(n);
                    }
                }
                else
                {
                    adapter = new NotificacionAdapter(arrayList);
                    setOnClickListener();
                    datos.setAdapter(adapter);
                    datos.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    setScrollListener();
                    linearLayoutManager = (LinearLayoutManager)datos.getLayoutManager();
                }
                if (arrayList.size() < TAMANO_PAGINA)
                {
                    FIN = true;
                }
            }
            else
            {
                (Toast.makeText(getApplicationContext(), "No hay items", Toast.LENGTH_SHORT)).show();
                FIN = true;
            }
            srl.setRefreshing(false);
          }
    }

    private ArrayList consultaRest()
    {
        ArrayList arrayList = null;
        try {
            URL Url = new URL(EXAURL + TIPO + "/" + PAGINA);
            //Log.i("url"," "+Url.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) Url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if(httpURLConnection.getResponseCode() == 200)
            {
                //Log.i("code"," 200");
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                if(inputStream != null)
                {
                    JsonDecoder jsonDecoder = new JsonDecoder();
                    arrayList = (ArrayList) jsonDecoder.notificaciones(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private void setOnClickListener()
    {
        adapter.setOnClickListener(this);
    }
}
