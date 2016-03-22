package ar.edu.unsa.exa.todosunsa.inicio.recycler;

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
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.itemViewHolder> implements View.OnClickListener
{
    private ArrayList<ItemCardView> list;
    private View.OnClickListener clickListener;

    public RecyclerAdapter(String publicidad, String noticia)
    {
        list = new ArrayList<ItemCardView>();
        list.add(new ItemCardView("Lista Evolución",R.drawable.levolucion,publicidad));
        list.add(new ItemCardView("UNSa Noticias",R.drawable.logounsa,noticia));
    }

    @Override
    public RecyclerAdapter.itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_inicio,parent,false);
        view.setOnClickListener(this);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.itemViewHolder holder, int position) {
        holder.titulo.setText(list.get(position).getTitulo());
        holder.imagen.setImageResource(list.get(position).getImagenAppbar());
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

    static class itemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titulo;
        private ImageView imagen;

        public itemViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.cw_inicio_titulo);
            imagen = (ImageView) itemView.findViewById(R.id.cw_inicio_imagen);
        }
    }

    public ItemCardView getItemPosition(int position)
    {
        return list.get(position);
    }
}
