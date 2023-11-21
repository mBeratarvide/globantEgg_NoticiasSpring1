package com.eggnews.EggNoticias.servicios;

import com.eggnews.EggNoticias.entidades.Noticia;
import com.eggnews.EggNoticias.repositorios.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServicio {
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @Transactional
    public void nuevaNoticia(String titulo, String cuerpo, String fecha) {
        Noticia n = new Noticia();

        n.setTitulo(titulo);
        n.setCuerpo(cuerpo);
        n.setFechaPublicacion(convertirFecha(fecha));

        noticiaRepositorio.save(n);
    }

    public Noticia obtenerNoticiaPorId(int id) {
        Optional<Noticia> hayNoticia = noticiaRepositorio.findByIdAndActivaTrue(id);
        return hayNoticia.orElse(null);
    }

    public List<Noticia> obtenerNoticiaPorTitulo(String titulo) {
        return noticiaRepositorio.getNoticiaPorTitulo(titulo);
    }

    public List<Noticia> obtenerTodasLasNoticias() {
        return noticiaRepositorio.findAll();
    }

    public List<Noticia> obtenerNoticiasActivas() {
        return noticiaRepositorio.getNoticiasActivas();
    }

    @Transactional
    public void modificarNoticia(int id, String titulo, String cuerpo, String fecha, boolean activo) {
        Optional<Noticia> hayNoticia = noticiaRepositorio.findById(id);

        if(hayNoticia.isPresent()) {
            Noticia n = hayNoticia.get();

            n.setTitulo(titulo);
            n.setCuerpo(cuerpo);
            n.setFechaPublicacion(convertirFecha(fecha));
            n.setActiva(activo);

            noticiaRepositorio.save(n);
        }
    }

    @Transactional
    public void alternarEstadoActivo(int id) {
        Optional<Noticia> hayNoticia = noticiaRepositorio.findById(id);

        if(hayNoticia.isPresent()) {
            Noticia n = hayNoticia.get();
            n.setActiva(!n.isActiva());
            noticiaRepositorio.save(n);
        }
    }

    private Date convertirFecha(String fechaString) {
        Date fecha = new Date();

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            fecha = formatoFecha.parse(fechaString);
        }
        catch (ParseException e) {
            //throw new e.getMessage(); // Manejo de la excepción en caso de un formato de fecha incorrecto
        }

        return fecha;
    }
}
