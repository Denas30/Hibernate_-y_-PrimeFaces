package d.pojos;
// Generated 22-feb-2016 15:36:18 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Comentarios generated by hbm2java
 */
public class Comentarios  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private String comentario;
     private Date fecha;
     private int idtema;

    public Comentarios() {
    }

    public Comentarios(String nombre, String comentario, Date fecha, int idtema) {
       this.nombre = nombre;
       this.comentario = comentario;
       this.fecha = fecha;
       this.idtema = idtema;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getIdtema() {
        return this.idtema;
    }
    
    public void setIdtema(int idtema) {
        this.idtema = idtema;
    }




}


