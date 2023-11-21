package com.eggnews.EggNoticias.repositorios;

import com.eggnews.EggNoticias.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Integer> {
    @Query("SELECT n FROM Noticia n WHERE n.titulo like :titulo and n.activa = true")
    public List<Noticia> getNoticiaPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT n FROM Noticia n WHERE n.activa = true ORDER BY n.fechaPublicacion DESC")
    public List<Noticia> getNoticiasActivas();

    @Query("SELECT n FROM Noticia n WHERE n.idNoticia = :id AND n.activa = true")
    public Optional<Noticia> findByIdAndActivaTrue(@Param("id") int id);
}
