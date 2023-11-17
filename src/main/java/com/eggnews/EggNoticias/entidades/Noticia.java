package com.eggnews.EggNoticias.entidades;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNoticia;
    private String titulo;
    @Column(length = 2048)
    private String cuerpo;
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    private boolean activa;

    public Noticia() {
        this.fechaCreacion = new Date();
        this.activa = true;
    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "idNoticia=" + idNoticia +
                ", titulo='" + titulo + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", fechaCreacion=" + fechaCreacion +
                ", activa=" + activa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return idNoticia == noticia.idNoticia && activa == noticia.activa && Objects.equals(titulo, noticia.titulo) && Objects.equals(cuerpo, noticia.cuerpo) && Objects.equals(fechaPublicacion, noticia.fechaPublicacion) && Objects.equals(fechaCreacion, noticia.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNoticia, titulo, cuerpo, fechaPublicacion, fechaCreacion, activa);
    }
}
