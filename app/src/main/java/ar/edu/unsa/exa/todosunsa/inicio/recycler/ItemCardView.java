package ar.edu.unsa.exa.todosunsa.inicio.recycler;

/**
 * Created by Fernando Vargas on 10/03/2016.
 */
public class ItemCardView
{
    private final int imagen_appbar;
    private final String titulo;
    private final String tipo;

    public ItemCardView(String titulo, int imagen_appbar,String tipo)
    {
        this.titulo = titulo;
        this.imagen_appbar = imagen_appbar;
        this.tipo = tipo;
    }

    public int getImagenAppbar() {
        return imagen_appbar;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo()
    {
        return tipo;
    }
}
