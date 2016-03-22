package ar.edu.unsa.exa.todosunsa.datos.recycler;

/**
 * Created by Fernando Vargas on 10/03/2016.
 */
public class Notificacion
{
    private String titulo;
    private String descripcion;
    private String categoria;
    private String fecha_publicacion;

    public Notificacion(String titulo, String descripcion, String categoria, String fecha_publicacion)
    {
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.categoria=categoria;
        this.fecha_publicacion=fecha_publicacion;
    }

    public String titulo(){return this.titulo;}

    public String descripcion() { return this.descripcion; }

    public String categoria(){return this.categoria;}

    public String publicado(){return this.fecha_publicacion;}
}
