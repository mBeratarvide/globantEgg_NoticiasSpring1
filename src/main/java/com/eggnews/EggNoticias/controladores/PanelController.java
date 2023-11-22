package com.eggnews.EggNoticias.controladores;

import com.eggnews.EggNoticias.entidades.Noticia;
import com.eggnews.EggNoticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/panel")
public class PanelController {
    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping({"/", ""})
    public String panel(ModelMap mm) {
        mm.addAttribute("noticias", noticiaServicio.obtenerTodasLasNoticias());
        return "panel";
    }

    @GetMapping("/noticia")
    public String formNoticia(ModelMap mm) {
        return "form_noticia";
    }

    @PostMapping("/guardar")
    public String guardarNoticia(
            @RequestParam(required = false) Integer id,
            @RequestParam String titulo,
            @RequestParam String cuerpo,
            @RequestParam String fechaPublicacion,
            @RequestParam(required = false) boolean activo,
            ModelMap mm) {
        try {
            if(id == null) {
                //System.out.println("-----> "+ titulo +" * "+ cuerpo +" * "+ fechaPublicacion);
                noticiaServicio.nuevaNoticia(titulo, cuerpo, fechaPublicacion);
                mm.addAttribute("exito", "La noticia fue asimilada :| ");
            } else {
                noticiaServicio.modificarNoticia(id, titulo, cuerpo, fechaPublicacion, activo);
                mm.addAttribute("exito", "La noticia fue editada correctamente.");
            }
        }
        catch(Exception e) {
            mm.addAttribute("error", e.getMessage());
            return "form_noticia";
        }
        return "redirect:/";
    }

    @PostMapping("/desActivar/{id}")
    public String toggleActivo(@PathVariable int id, ModelMap mm) {
        System.out.println("---> toggle activo -> id : " + id);
        noticiaServicio.alternarEstadoActivo(id);
        return "redirect:/panel";
    }

    @GetMapping("/noticia/{id}")
    public String editarNoticia(@PathVariable int id, ModelMap mm) {
        mm.addAttribute("noticia", noticiaServicio.obtenerNoticiaPorId(id));
        mm.addAttribute("id", id);
        return "form_noticia";
    }
}
