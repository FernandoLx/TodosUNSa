package ar.edu.unsa.exa.todosunsa.datos.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ar.edu.unsa.exa.todosunsa.R;

public class ItemDescripcion extends AppCompatActivity {

    private TextView titulo;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_descripcion);
        titulo = (TextView) findViewById(R.id.item_titulo);
        descripcion = (TextView) findViewById(R.id.item_descripcion);
        titulo.setText(getIntent().getStringExtra("TITULO"));
        descripcion.setText(getIntent().getStringExtra("DESCRIPCION"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
