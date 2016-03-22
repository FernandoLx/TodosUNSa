package ar.edu.unsa.exa.todosunsa.conexion;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Fernando Vargas on 10/03/2016.
 */
public class Internet
{
    private Context context;

    public Internet(Context context){
        this.context = context;
    }

    public boolean conectado() {
        boolean estado = false;
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            estado = true;
        }
        return estado;
    }
}
