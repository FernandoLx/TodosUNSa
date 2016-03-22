package ar.edu.unsa.exa.todosunsa.json;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ar.edu.unsa.exa.todosunsa.datos.recycler.Notificacion;

/**
 * Created by Fernando Vargas on 10/03/2016.
 */
public class JsonDecoder
{
    private JsonReader jsonReader;

    public ArrayList<Notificacion> notificaciones (InputStream in) throws IOException
    {
        try {
            jsonReader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            return leerArrayJson(jsonReader);
        }
        finally {
            jsonReader.close();
        }
    }

    private ArrayList leerArrayJson(JsonReader json) throws IOException
    {
        ArrayList notifications = new ArrayList();
        json.beginArray();
        while (json.hasNext())
        {
            notifications.add(leerObjetoJson(json));
        }
        json.endArray();
        return notifications;
    }

    private Notificacion leerObjetoJson(JsonReader json) throws IOException
    {
        String titulo = null;
        String descripcion = null;
        String categoria = null;
        String publicado = null;

        json.beginObject();
        while (json.hasNext())
        {
            String clave = json.nextName();
            switch(clave)
            {
                case "titulo":
                    titulo=json.nextString();
                    break;
                case "descripcion":
                    descripcion = json.nextString();
                    break;
                case "categoria":
                    categoria = json.nextString();
                    break;
                case "fecha_publicacion":
                    publicado = json.nextString();
                    break;
                default:
                    json.skipValue();
            }
        }
        json.endObject();
        return new Notificacion(titulo,descripcion,categoria,publicado);
    }

}
