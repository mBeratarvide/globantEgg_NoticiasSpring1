package com.eggnews.EggNoticias.repositorios;

import com.eggnews.EggNoticias.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Integer> {
    @Query("SELECT n FROM Noticia n WHERE n.titulo like :titulo")
    public Noticia getNoticiaPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT n FROM Noticia n WHERE activa = true")
    public List<Noticia> getNoticiasActivas();
}
