package ar.edu.unsa.exa.todosunsa.creditos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.edu.unsa.exa.todosunsa.R;

public class Creditos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
