package ar.edu.unsa.exa.todosunsa.datos.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import ar.edu.unsa.exa.todosunsa.R;

/**
 * Created by Fernando Vargas on 10/03/2016.
 */
public class NotificacionAdapter extends RecyclerView.Adapter<NotificacionAdapter.itemNotificacion> implements View.OnClickListener
{
    private final int IMAGEN_NOTICIA;
    private final int IMAGEN_CONCURSO;
    private final int IMAGEN_DEFUALT;
    private final int IMAGEN_LISTA;
    private ArrayList<Notificacion> list;
    private View.OnClickListener clickListener;
    //private int imagen;

    public NotificacionAdapter(ArrayList<Notificacion> list)
    {
        this.list=list;
        this.clickListener = null;
        IMAGEN_NOTICIA = R.drawable.noticia;
        IMAGEN_CONCURSO = R.drawable.concurso;
        IMAGEN_DEFUALT = R.drawable.notificacion;
        IMAGEN_LISTA = R.drawable.lista2;
        //this.imagen = imagen;
    }

    @Override
    public NotificacionAdapter.itemNotificacion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_notificaciones,parent,false);
        view.setOnClickListener(this);
        return new itemNotificacion(view);
    }

    @Override
    public void onBindViewHolder(NotificacionAdapter.itemNotificacion holder, int position) {
        holder.titulo.setText(list.get(position).titulo());
        holder.cabecera.setText(list.get(position).categoria());
        String publicado = list.get(position).publicado();
        String[] fecha = publicado.split(" ");
        holder.publicado.setText("publicado " + fecha[0].toString());
        switch (list.get(position).categoria())
        {
            case "Noticia": holder.icono.setImageResource(IMAGEN_NOTICIA);
                break;
            case "Concurso": holder.icono.setImageResource(IMAGEN_CONCURSO);
                break;
            case "Propuesta": holder.icono.setImageResource(IMAGEN_LISTA);
                break;
            case "Lista Evolución": holder.icono.setImageResource(IMAGEN_LISTA);
                break;
            case "Lista Evolucion": holder.icono.setImageResource(IMAGEN_LISTA);
                break;
            default:
                holder.icono.setImageResource(IMAGEN_DEFUALT);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener clickListener)
    {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        if(clickListener != null)
        {
            clickListener.onClick(v);
        }
    }

    static class itemNotificacion extends RecyclerView.ViewHolder {

        private TextView cabecera;
        private TextView titulo;
        private TextView publicado;
        private ImageView icono;
        public itemNotificacion(View itemView) {
            super(itemView);
            cabecera = (TextView)itemView.findViewById(R.id.cw_notificacion_cabecera);
            titulo = (TextView)itemView.findViewById(R.id.cw_notificacion_titulo);
            publicado = (TextView)itemView.findViewById(R.id.cw_notificacion_fecha);
            icono = (ImageView)itemView.findViewById(R.id.cw_notificacion_imagen);
        }
    }

    public void add(Notificacion n)
    {
        list.add(n);
        notifyDataSetChanged();
    }

    public void clear()
    {
        list.clear();
        notifyDataSetChanged();
    }

    public Notificacion getItemPosition(int position)
    {
        return list.get(position);
    }
}
